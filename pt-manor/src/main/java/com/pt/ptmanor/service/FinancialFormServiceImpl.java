package com.pt.ptmanor.service;



import com.pt.ptmanor.mapper.FinancialFormRepository;
import com.pt.ptmanor.pojo.product.Invoice;
import com.pt.ptmanor.pojo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FinancialFormServiceImpl implements FinancialFormService {


    @Autowired
    FinancialFormRepository financialFormRepository;

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
//        Pageable pageable  = PageRequest.of(pageNum-1,pageRow);
//        Page<FinancialForm> Page = financialFormRepository.findAll(spec,pageable);

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"date"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);

        Page<Product> Page = financialFormRepository.findAll(spec,pageable);

        return Page ;

    }

    @Override
    public Map getList() {
        return null;
    }

    @Override
    public Page findByMany(String productName,String invoiceId, String buyerName, Date etime, Date stime ,Integer pageNum,Integer pageRow) {


        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();


                if (stime != null){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("date"),stime));
                }
                if (etime != null){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get("date"),etime));
                }
                if (!StringUtils.isEmpty(invoiceId)){
                    Path invoiceId1 = root.get("invoiceId");
                    predicates.add(criteriaBuilder.like(invoiceId1.as(String.class),"%"+invoiceId+"%"));
                }
                if (!StringUtils.isEmpty(buyerName)){
                    Path buyerName1 = root.get("buyerName");
                    predicates.add(criteriaBuilder.like(buyerName1.as(String.class),"%"+buyerName+"%"));
                }
                if (!StringUtils.isEmpty(productName)){
                    Path productName1 = root.get("productName");
                    predicates.add(criteriaBuilder.like(productName1.as(String.class),"%"+productName+"%"));
                }

                if (!StringUtils.isEmpty(root.get("isDeleted"))){
                    Path isDeleted1 = root.get("isDeleted");
                    predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));
                }

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow);
        Page<Invoice> Page = financialFormRepository.findAll(spec,pageable);

        return Page;
    }


}
