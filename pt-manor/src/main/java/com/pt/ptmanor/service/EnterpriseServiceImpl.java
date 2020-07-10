package com.pt.ptmanor.service;


import com.pt.ptmanor.mapper.EnterprisesRepository;
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
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    EnterprisesRepository enterprisesRepository;

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
        //2.1 设置根据修改时间倒叙排序
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow);
        Page<Product> Page = enterprisesRepository.findAll(spec,pageable);

        return Page ;
    }

    @Override
    public Page findByMany(String enterpriseName, Integer pageNum, Integer pageRow) {

        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (!StringUtils.isEmpty(enterpriseName)){
                    Path enterpriseName1 = root.get("enterpriseName");
                    predicates.add(criteriaBuilder.like(enterpriseName1.as(String.class),"%"+enterpriseName+"%"));
                }
                if (!StringUtils.isEmpty(root.get("isDeleted"))){
                    Path isDeleted1 = root.get("isDeleted");
                    predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));
                }


                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow);
        Page<Invoice> Page = enterprisesRepository.findAll(spec,pageable);

        return Page;
    }


//    @Override
//    public List<Enterprise> all() {
//
//        List<Enterprise> all = enterprisesRepository.findAll();
//
//        return all;
//    }
}
