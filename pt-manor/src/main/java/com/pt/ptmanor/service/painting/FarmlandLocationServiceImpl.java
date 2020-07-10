package com.pt.ptmanor.service.painting;

import com.pt.ptmanor.mapper.painting.FarmlandLocationRepository;
import com.pt.ptmanor.pojo.product.Invoice;
import com.pt.ptmanor.pojo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FarmlandLocationServiceImpl implements FarmlandLocationService{

    @Autowired
    FarmlandLocationRepository farmlandLocationRepository;

    @Override
    public Page list(int pageNum, int pageRow) {

        //1.自定义查询条件 （idDelete == 0）
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //1.1 获取比较的属性
                Path<Object> isDeleted =root.get("isDeleted");
                //1.2构造查询条件
                Predicate predicate = criteriaBuilder.equal(isDeleted, 0);
                return predicate;
            }
        };
        //2. 查询符合条件的数据并返回前端
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow);
        Page<Product> Page = farmlandLocationRepository.findAll(spec,pageable);

        return Page ;

    }

    @Override
    public Page findByMany(String farmlandLocationId, String farmlandLocationName, String farmlandPrincipal, String farmlandArea, String farmlandExplain, Integer pageNum, Integer pageRow) {

        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (!StringUtils.isEmpty(farmlandLocationId)){
                    Path farmlandLocationId1 = root.get("farmlandLocationId");
                    predicates.add(criteriaBuilder.like(farmlandLocationId1.as(String.class),"%"+farmlandLocationId+"%"));
                }
                if (!StringUtils.isEmpty(farmlandLocationName)){
                    Path farmlandLocationName1 = root.get("farmlandLocationName");
                    predicates.add(criteriaBuilder.like(farmlandLocationName1.as(String.class),"%"+farmlandLocationName+"%"));
                }
                if (!StringUtils.isEmpty(farmlandPrincipal)){
                    Path farmlandPrincipal1 = root.get("farmlandPrincipal");
                    predicates.add(criteriaBuilder.like(farmlandPrincipal1.as(String.class),"%"+farmlandPrincipal+"%"));
                }
                if (!StringUtils.isEmpty(farmlandArea)){
                    Path farmlandArea1 = root.get("farmlandArea");
                    predicates.add(criteriaBuilder.like(farmlandArea1.as(String.class),"%"+farmlandArea+"%"));
                }

                if (!StringUtils.isEmpty(farmlandExplain)){
                    Path farmlandExplain1 = root.get("farmlandExplain");
                    predicates.add(criteriaBuilder.equal(farmlandExplain1.as(String.class),farmlandExplain));
                }
                if (!StringUtils.isEmpty(root.get("isDeleted"))){
                    Path isDeleted1 = root.get("isDeleted");
                    predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));
                }

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow);
        Page<Invoice> Page = farmlandLocationRepository.findAll(spec,pageable);
        return Page;
    }
}
