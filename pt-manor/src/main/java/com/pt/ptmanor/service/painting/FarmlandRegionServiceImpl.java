package com.pt.ptmanor.service.painting;



import com.pt.ptmanor.mapper.painting.FarmlandRegionRepository;
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
public class FarmlandRegionServiceImpl implements FarmlandRegionService {

    @Autowired
    FarmlandRegionRepository farmlandRegionRepository;

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
        Page<Product> Page = farmlandRegionRepository.findAll(spec,pageable);

        return Page ;
    }

    @Override
    public Page findByMany(String farmlandRegionId, String farmlandRegionPrincipal, String farmlandRegionType, String farmlandRegionExplain, String farmlandLocationName, Integer pageNum, Integer pageRow) {

        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (!StringUtils.isEmpty(farmlandRegionId)){
                    Path farmlandRegionId1 = root.get("farmlandRegionId");
                    predicates.add(criteriaBuilder.like(farmlandRegionId1.as(String.class),"%"+farmlandRegionId+"%"));
                }
                if (!StringUtils.isEmpty(farmlandRegionPrincipal)){
                    Path farmlandRegionPrincipal1 = root.get("farmlandRegionPrincipal");
                    predicates.add(criteriaBuilder.like(farmlandRegionPrincipal1.as(String.class),"%"+farmlandRegionPrincipal+"%"));
                }
                if (!StringUtils.isEmpty(farmlandRegionType)){
                    Path farmlandRegionType1 = root.get("farmlandRegionType");
                    predicates.add(criteriaBuilder.like(farmlandRegionType1.as(String.class),"%"+farmlandRegionType+"%"));
                }
                if (!StringUtils.isEmpty(farmlandRegionExplain)){
                    Path farmlandRegionExplain1 = root.get("farmlandRegionExplain");
                    predicates.add(criteriaBuilder.like(farmlandRegionExplain1.as(String.class),"%"+farmlandRegionExplain+"%"));
                }

                if (!StringUtils.isEmpty(farmlandLocationName)){
                    Path farmlandLocationName1 = root.get("farmlandLocationName");
                    predicates.add(criteriaBuilder.equal(farmlandLocationName1.as(String.class),farmlandLocationName));
                }
                if (!StringUtils.isEmpty(root.get("isDeleted"))){
                    Path isDeleted1 = root.get("isDeleted");
                    predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));
                }


                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow);
        Page<Invoice> Page = farmlandRegionRepository.findAll(spec,pageable);


        return Page;
    }
}
