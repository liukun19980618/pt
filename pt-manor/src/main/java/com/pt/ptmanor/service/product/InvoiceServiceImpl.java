package com.pt.ptmanor.service.product;

import com.pt.ptmanor.mapper.product.InvoiceRepository;
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

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

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
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"createTime"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);

        Page<Product> Page = invoiceRepository.findAll(spec,pageable);

        return Page ;
    }

    @Override
    public Page findByMany(Date stime, Date etime,Integer status, String buyerName, String productName, String orderId,Integer pageNum,Integer pageRow) {

        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (stime != null){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createTime"),stime));
                }
                if (etime != null){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createTime"),etime));
                }
                if (!StringUtils.isEmpty(buyerName)){
                    Path buyerName1 = root.get("buyerName");
                    predicates.add(criteriaBuilder.like(buyerName1.as(String.class),"%"+buyerName+"%"));
                }
                if (!StringUtils.isEmpty(productName)){
                    Path productName1 = root.get("productName");
                    predicates.add(criteriaBuilder.like(productName1.as(String.class),"%"+productName+"%"));
                }

                if (!StringUtils.isEmpty(orderId)){
                    Path orderId1 = root.get("orderId");
                    predicates.add(criteriaBuilder.equal(orderId1.as(String.class),orderId));
                }
                if (!StringUtils.isEmpty(status)){
                    Path status1 = root.get("status");
                    predicates.add(criteriaBuilder.equal(status1.as(Integer.class),status));
                }
                if (!StringUtils.isEmpty(root.get("isDeleted"))){
                    Path isDeleted1 = root.get("isDeleted");
                    predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));
                }

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"createTime"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);

        Page<Product> Page = invoiceRepository.findAll(spec,pageable);

        return Page;

    }


    //审查的表单
    @Override
    public Page checkList(int pageNum, int pageRow, String userName) {

        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                //1.1 获取比较的属性
                Path<Object> isDeleted =root.get("isDeleted");
                //1.2构造查询条件
                Predicate predicate = criteriaBuilder.equal(isDeleted, 0);
                Path<Object> reviewerId =root.get("reviewerId");
                Predicate predicate1= criteriaBuilder.equal(reviewerId, userName);

                Path<Object> checkFlag =root.get("checkFlag");

                Predicate predicate2= criteriaBuilder.equal(checkFlag, 1);

                predicates.add(predicate);
                predicates.add(predicate1);
                predicates.add(predicate2);

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        //2. 查询符合条件的数据并返回前端
        //2.1 设置根据修改时间倒叙排序
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"createTime"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);
        Page<Product> Page = invoiceRepository.findAll(spec,pageable);
        return Page ;
    }



    @Override
    public Page myList(int pageNum, int pageRow,String userName) {

        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();


                //1.1 获取比较的属性
                Path<Object> isDeleted =root.get("isDeleted");
                //1.2构造查询条件
                Predicate predicate = criteriaBuilder.equal(isDeleted, 0);

                Path<Object> orderCreator =root.get("orderCreator");
                Predicate predicate1= criteriaBuilder.equal(orderCreator, userName);

                predicates.add(predicate);
                predicates.add(predicate1);

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        //2. 查询符合条件的数据并返回前端
        //2.1 设置根据创建时间倒叙排序
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"createTime"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);
        Page<Product> Page = invoiceRepository.findAll(spec,pageable);

        return Page ;
    }

    @Override
    public Page finalList(int pageNum, int pageRow) {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();


                //1.1 获取比较的属性
                Path<Object> isDeleted =root.get("isDeleted");
                //1.2构造查询条件
                Predicate predicate = criteriaBuilder.equal(isDeleted, 0);
                Path<Object> checkFlag =root.get("checkFlag");


                Predicate predicate2= criteriaBuilder.equal(checkFlag, 2);
                Predicate predicate3= criteriaBuilder.equal(checkFlag, 3);
                Predicate predicate5= criteriaBuilder.equal(checkFlag, 4);
                Predicate predicate4= criteriaBuilder.or(predicate2,predicate3);
                Predicate predicate1 = criteriaBuilder.or(predicate4,predicate5);

                predicates.add(predicate1);
                predicates.add(predicate);
                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        //2. 查询符合条件的数据并返回前端
        //2.1 设置根据修改时间倒叙排序
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"createTime"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);
        Page<Product> Page = invoiceRepository.findAll(spec,pageable);
        return Page ;
    }



    @Override
    public Page myInvoiceFindByMany(String userName, Date stime, Date etime, Integer checkFlag, String buyerName, String productName, String orderId, Integer pageNum, Integer pageRow) {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (stime != null){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createTime"),stime));
                }
                if (etime != null){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createTime"),etime));
                }
                if (!StringUtils.isEmpty(buyerName)){
                    Path buyerName1 = root.get("buyerName");
                    predicates.add(criteriaBuilder.like(buyerName1.as(String.class),"%"+buyerName+"%"));
                }
                if (!StringUtils.isEmpty(productName)){
                    Path productName1 = root.get("productName");
                    predicates.add(criteriaBuilder.like(productName1.as(String.class),"%"+productName+"%"));
                }

                if (!StringUtils.isEmpty(orderId)){
                    Path orderId1 = root.get("orderId");
                    predicates.add(criteriaBuilder.equal(orderId1.as(String.class),orderId));
                }
                if (!StringUtils.isEmpty(checkFlag)){
                    Path checkFlag1 = root.get("checkFlag");
                    predicates.add(criteriaBuilder.equal(checkFlag1.as(Integer.class),checkFlag));
                }
                if (!StringUtils.isEmpty(root.get("isDeleted"))){
                    Path isDeleted1 = root.get("isDeleted");
                    predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));
                }
                if (!StringUtils.isEmpty(root.get("orderCreator"))){
                    Path orderCreator1 = root.get("orderCreator");
                    predicates.add(criteriaBuilder.equal(orderCreator1.as(String.class),userName));
                }

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"createTime"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);

        Page<Product> Page = invoiceRepository.findAll(spec,pageable);

        return Page;
    }

    @Override
    public Page finalFindByMany(Date stime, Date etime, Integer checkFlag, String buyerName, String productName, String orderId, Integer pageNum, Integer pageRow) {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (stime != null){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createTime"),stime));
                }
                if (etime != null){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createTime"),etime));
                }
                if (!StringUtils.isEmpty(buyerName)){
                    Path buyerName1 = root.get("buyerName");
                    predicates.add(criteriaBuilder.like(buyerName1.as(String.class),"%"+buyerName+"%"));
                }
                if (!StringUtils.isEmpty(productName)){
                    Path productName1 = root.get("productName");
                    predicates.add(criteriaBuilder.like(productName1.as(String.class),"%"+productName+"%"));
                }

                if (!StringUtils.isEmpty(orderId)){
                    Path orderId1 = root.get("orderId");
                    predicates.add(criteriaBuilder.equal(orderId1.as(String.class),orderId));
                }

                if (!StringUtils.isEmpty(checkFlag)){
                    if (checkFlag==2){
                        Path checkFlag1 = root.get("checkFlag");
                        Predicate predicate = criteriaBuilder.equal(checkFlag1,2);
                        Predicate predicate1 = criteriaBuilder.equal(checkFlag1,4);
                        Predicate predicate2 =criteriaBuilder.or(predicate1,predicate);

                        predicates.add(predicate2);
                    }else {
                        Path checkFlag1 = root.get("checkFlag");
                        predicates.add(criteriaBuilder.equal(checkFlag1.as(Integer.class),checkFlag));
                    }

                }
                if (!StringUtils.isEmpty(root.get("isDeleted"))){
                    Path isDeleted1 = root.get("isDeleted");
                    predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));
                }

                if (!StringUtils.isEmpty(root.get("checkFlag"))){
                    Path checkFlag1 = root.get("checkFlag");
                    Predicate predicate = criteriaBuilder.equal(checkFlag1,2);
                    Predicate predicate1 = criteriaBuilder.equal(checkFlag1,4);
                    Predicate predicate3 = criteriaBuilder.equal(checkFlag1,3);
                    Predicate predicate2 =criteriaBuilder.or(predicate1,predicate);
                    Predicate predicate4 =criteriaBuilder.or(predicate3,predicate2);
                    predicates.add(predicate4);

                }



                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"createTime"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);

        Page<Product> Page = invoiceRepository.findAll(spec,pageable);

        return Page;
    }


    @Override
    public Page checkInvoiceFindByMany(String userName, Date stime, Date etime, String staff, String buyerName, String productName, String orderId, Integer pageNum, Integer pageRow) {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (stime != null){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createTime"),stime));
                }
                if (etime != null){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createTime"),etime));
                }
                if (!StringUtils.isEmpty(buyerName)){
                    Path buyerName1 = root.get("buyerName");
                    predicates.add(criteriaBuilder.like(buyerName1.as(String.class),"%"+buyerName+"%"));
                }
                if (!StringUtils.isEmpty(productName)){
                    Path productName1 = root.get("productName");
                    predicates.add(criteriaBuilder.like(productName1.as(String.class),"%"+productName+"%"));
                }

                if (!StringUtils.isEmpty(orderId)){
                    Path orderId1 = root.get("orderId");
                    predicates.add(criteriaBuilder.equal(orderId1.as(String.class),orderId));
                }
                if (!StringUtils.isEmpty(staff)){
                    Path orderCreator1 = root.get("orderCreator");
                    predicates.add(criteriaBuilder.equal(orderCreator1.as(String.class),staff));
                }
                if (!StringUtils.isEmpty(root.get("isDeleted"))){
                    Path isDeleted1 = root.get("isDeleted");
                    predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));
                }
                if (!StringUtils.isEmpty(root.get("checkFlag"))){
                    Path checkFlag1 = root.get("checkFlag");
                    predicates.add(criteriaBuilder.equal(checkFlag1.as(Integer.class),1));
                }
                if (!StringUtils.isEmpty(root.get("reviewerId"))){
                    Path reviewerId1 = root.get("reviewerId");
                    predicates.add(criteriaBuilder.equal(reviewerId1.as(String.class),userName));
                }

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"createTime"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);

        Page<Product> Page = invoiceRepository.findAll(spec,pageable);

        return Page;
    }
}
