package com.pt.ptmanor.service;

import com.pt.ptmanor.mapper.SaleAmountRepository;
import com.pt.ptmanor.pojo.Product;
import com.pt.ptmanor.pojo.SaleAmount;
import com.pt.ptmanor.pojo.product.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class SaleAmountServiceImpl implements  SaleAmountService {

    @Autowired
    SaleAmountRepository saleAmountRepository;

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
//        Page<Product> Page = saleAmountRepository.findAll(spec,pageable);
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"date"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);

        Page<Product> Page = saleAmountRepository.findAll(spec,pageable);

        return Page ;
    }

    @Override
    public Page findByMany(String crops, Date stime, Date etime, Integer pageNum, Integer pageRow) {
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
                if (!StringUtils.isEmpty(crops)){
                    Path crops1 = root.get("crops");
                    predicates.add(criteriaBuilder.like(crops1.as(String.class),"%"+crops+"%"));
                }
                if (!StringUtils.isEmpty(root.get("isDeleted"))){
                    Path isDeleted1 = root.get("isDeleted");
                    predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));
                }

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow);
        Page<Invoice> Page = saleAmountRepository.findAll(spec,pageable);

        return Page;
    }


    @Override
    public Map chart(String year) {
        List<SaleAmount> byIsDeleted = saleAmountRepository.findByIsDeletedAndYear(0,year);
        List<Integer> money = new ArrayList<>();
        int i;
        for (i=0;i<12;i++){
            Integer all = 0;
            for (SaleAmount saleAmount:byIsDeleted){

                Calendar ca = Calendar.getInstance();
                ca.setTime(saleAmount.getDate());

                int month = ca.get(Calendar.MONTH);//第几个月
                if (month==i){
                    all = all+saleAmount.getAmount();
                }
            }
            money.add(all);
        }
        Map map= new HashMap();
        map.put("list",money);
        return map;
    }


    @Override
    public Map findChart(String year, String crops, String buyerName) {

        List<SaleAmount> list = new ArrayList<>();

        if (!crops.isEmpty()){
            if (!buyerName.isEmpty()){
                 list = saleAmountRepository.findByIsDeletedAndYearAndCropsAndBuyerName(0, year, crops, buyerName);
            }else {
              list = saleAmountRepository.findByIsDeletedAndYearAndCrops(0, year, crops);
            }
        }else {
            if (!buyerName.isEmpty()){
                list = saleAmountRepository.findByIsDeletedAndYearAndBuyerName(0, year, buyerName);
            }else {
                list = saleAmountRepository.findByIsDeletedAndYear(0,year);
            }
        }

        List<Integer> money = new ArrayList<>();
        int i;
        for (i=0;i<12;i++){
            Integer all = 0;
            for (SaleAmount saleAmount:list){

                Calendar ca = Calendar.getInstance();
                ca.setTime(saleAmount.getDate());

                int month = ca.get(Calendar.MONTH);//第几个月
                if (month==i){
                    all = all+saleAmount.getAmount();
                }
            }
            money.add(all);
        }
        Map map= new HashMap();
        map.put("list",money);
        return map;

    }


}
