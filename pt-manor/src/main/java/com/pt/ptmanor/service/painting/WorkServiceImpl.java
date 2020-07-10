package com.pt.ptmanor.service.painting;


import com.pt.ptmanor.mapper.painting.WorkRepository;
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

@Service
public class WorkServiceImpl  implements WorkService {

    @Autowired
    WorkRepository workRepository;


    @Override
    public Page list(int pageNum, int pageRow) {

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
        Page<Product> Page = workRepository.findAll(spec,pageable);

        return Page ;

    }

    @Override
    public Page materialList(int pageNum, int pageRow) {

        //1.自定义查询条件 （idDelete == 0）
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();

                Path work1 = root.get("work");
//                Path materialName = root.get("materialName");
                Predicate predicate = criteriaBuilder.equal(work1,"施肥" );
                Predicate predicate1 = criteriaBuilder.equal(work1,"栽苗" );
                Predicate predicate2 = criteriaBuilder.or(predicate,predicate1);
//                predicates.add(criteriaBuilder.isNotNull(materialName));

                predicates.add(predicate2);
                Path isDeleted1 = root.get("isDeleted");
                predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        //2. 查询符合条件的数据并返回前端
        //2.1 设置根据修改时间倒叙排序
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow);
        Page<Product> Page = workRepository.findAll(spec,pageable);

        return Page ;
    }

    @Override
    public Page findByMany(Date stime, Date etime, String work, String crops, String staff, String id, String findFarmlandRegionId, Integer pageNum, Integer pageRow) {


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
                if (!StringUtils.isEmpty(staff)){
                    Path staff1 = root.get("staff");
                    predicates.add(criteriaBuilder.like(staff1.as(String.class),"%"+staff+"%"));
                }
                if (!StringUtils.isEmpty(findFarmlandRegionId)){
                    Path findFarmlandRegionId1 = root.get("findFarmlandRegionId");
                    predicates.add(criteriaBuilder.like(findFarmlandRegionId1.as(String.class),"%"+findFarmlandRegionId+"%"));
                }
                if (!StringUtils.isEmpty(crops)){
                    Path crops1 = root.get("crops");
                    predicates.add(criteriaBuilder.like(crops1.as(String.class),"%"+crops+"%"));
                }
                if (!StringUtils.isEmpty(work)){
                    Path work1 = root.get("work");
                    predicates.add(criteriaBuilder.like(work1.as(String.class),"%"+work+"%"));
                }
                if (!StringUtils.isEmpty(id)){
                    Path id1 = root.get("id");
                    predicates.add(criteriaBuilder.equal(id1.as(String.class),id));
                }
                if (!StringUtils.isEmpty(root.get("isDeleted"))){
                    Path isDeleted1 = root.get("isDeleted");
                    predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));
                }

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow);
        Page<Invoice> Page = workRepository.findAll(spec,pageable);

        return Page;
    }


    @Override
    public Page findByManyMaterial(Date stime, Date etime, String work, String crops, String staff, String id, String field, Integer pageNum, Integer pageRow) {


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
                if (!StringUtils.isEmpty(staff)){
                    Path staff1 = root.get("staff");
                    predicates.add(criteriaBuilder.like(staff1.as(String.class),"%"+staff+"%"));
                }
                if (!StringUtils.isEmpty(field)){
                    Path field1 = root.get("field");
                    predicates.add(criteriaBuilder.like(field1.as(String.class),"%"+field+"%"));
                }
                if (!StringUtils.isEmpty(crops)){
                    Path crops1 = root.get("crops");
                    predicates.add(criteriaBuilder.like(crops1.as(String.class),"%"+crops+"%"));
                }
                if (!StringUtils.isEmpty(work)){
                    Path work1 = root.get("work");
                    predicates.add(criteriaBuilder.like(work1.as(String.class),"%"+work+"%"));
                }
                if (!StringUtils.isEmpty(id)){
                    Path id1 = root.get("id");
                    predicates.add(criteriaBuilder.equal(id1.as(String.class),id));
                }
                if (!StringUtils.isEmpty(root.get("isDeleted"))){
                    Path isDeleted1 = root.get("isDeleted");
                    predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));
                }


                Path materialName = root.get("materialName");

                predicates.add(criteriaBuilder.isNotNull(materialName));
                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow);
        Page<Invoice> Page = workRepository.findAll(spec,pageable);

        return Page;
    }


    @Override
    public Page myList(int pageNum, int pageRow, String userName) {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();


                //1.1 获取比较的属性
                Path<Object> isDeleted =root.get("isDeleted");
                //1.2构造查询条件
                Predicate predicate = criteriaBuilder.equal(isDeleted, 0);
                Path<Object> staff =root.get("staff");
                Predicate predicate1= criteriaBuilder.equal(staff, userName);
                predicates.add(predicate);
                predicates.add(predicate1);

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        //2. 查询符合条件的数据并返回前端
        //2.1 设置根据修改时间倒叙排序
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"updateDate"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);
        Page<Product> Page = workRepository.findAll(spec,pageable);

        return Page ;
    }

    @Override
    public Page toCheckList(int pageNum, int pageRow, String userName) {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                //1.1 获取比较的属性
                Path<Object> isDeleted =root.get("isDeleted");
                //1.2构造查询条件
                Predicate predicate = criteriaBuilder.equal(isDeleted, 0);
                Path<Object> checkUser =root.get("checkUser");
                Predicate predicate1= criteriaBuilder.equal(checkUser, userName);
                Path<Object> checkNumber =root.get("checkNumber");

                Predicate predicate2= criteriaBuilder.equal(checkNumber, 1);

                predicates.add(predicate);
                predicates.add(predicate1);
                predicates.add(predicate2);

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        //2. 查询符合条件的数据并返回前端
        //2.1 设置根据修改时间倒叙排序
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"updateDate"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);
        Page<Product> Page = workRepository.findAll(spec,pageable);
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
//                Path<Object> checkUser =root.get("checkUser");
//                Predicate predicate1= criteriaBuilder.equal(checkUser, userName);
                Path<Object> checkNumber =root.get("checkNumber");


                Predicate predicate2= criteriaBuilder.equal(checkNumber, 2);
                Predicate predicate3= criteriaBuilder.equal(checkNumber, 3);
                Predicate predicate4= criteriaBuilder.or(predicate2,predicate3);

                predicates.add(predicate);
                predicates.add(predicate4);
                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        //2. 查询符合条件的数据并返回前端
        //2.1 设置根据修改时间倒叙排序
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"updateDate"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);
        Page<Product> Page = workRepository.findAll(spec,pageable);
        return Page ;
    }


    @Override
    public Page myWorkFindByMany(String userName, Date stime, Date etime, String work, String crops, Integer checkNumber, String id, String findFarmlandRegionId, Integer pageNum, Integer pageRow) {
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
                if (!StringUtils.isEmpty(checkNumber)){
                    Path checkNumber1 = root.get("checkNumber");
                    predicates.add(criteriaBuilder.like(checkNumber1.as(String.class),"%"+checkNumber+"%"));
                }
                if (!StringUtils.isEmpty(findFarmlandRegionId)){
                    Path findFarmlandRegionId1 = root.get("findFarmlandRegionId");
                    predicates.add(criteriaBuilder.like(findFarmlandRegionId1.as(String.class),"%"+findFarmlandRegionId+"%"));
                }
                if (!StringUtils.isEmpty(crops)){
                    Path crops1 = root.get("crops");
                    predicates.add(criteriaBuilder.like(crops1.as(String.class),"%"+crops+"%"));
                }
                if (!StringUtils.isEmpty(work)){
                    Path work1 = root.get("work");
                    predicates.add(criteriaBuilder.like(work1.as(String.class),"%"+work+"%"));
                }
                if (!StringUtils.isEmpty(id)){
                    Path id1 = root.get("id");
                    predicates.add(criteriaBuilder.equal(id1.as(String.class),id));
                }
                if (!StringUtils.isEmpty(root.get("isDeleted"))){
                    Path isDeleted1 = root.get("isDeleted");
                    predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));
                }
                if (!StringUtils.isEmpty(root.get("staff"))){
                    Path staff1 = root.get("staff");
                    predicates.add(criteriaBuilder.equal(staff1.as(String.class),userName));
                }

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"updateDate"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);
        Page<Invoice> Page = workRepository.findAll(spec,pageable);

        return Page;
    }


    @Override
    public Page toCheckWorkFindByMany(String userName,Date stime, Date etime, String work, String crops, String staff, String id, String findFarmlandRegionId, Integer pageNum, Integer pageRow) {
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
                if (!StringUtils.isEmpty(staff)){
                    Path staff1 = root.get("staff");
                    predicates.add(criteriaBuilder.like(staff1.as(String.class),"%"+staff+"%"));
                }
                if (!StringUtils.isEmpty(findFarmlandRegionId)){
                    Path findFarmlandRegionId1 = root.get("findFarmlandRegionId");
                    predicates.add(criteriaBuilder.like(findFarmlandRegionId1.as(String.class),"%"+findFarmlandRegionId+"%"));
                }
                if (!StringUtils.isEmpty(crops)){
                    Path crops1 = root.get("crops");
                    predicates.add(criteriaBuilder.like(crops1.as(String.class),"%"+crops+"%"));
                }
                if (!StringUtils.isEmpty(work)){
                    Path work1 = root.get("work");
                    predicates.add(criteriaBuilder.like(work1.as(String.class),"%"+work+"%"));
                }
                if (!StringUtils.isEmpty(id)){
                    Path id1 = root.get("id");
                    predicates.add(criteriaBuilder.equal(id1.as(String.class),id));
                }
                if (!StringUtils.isEmpty(root.get("isDeleted"))){
                    Path isDeleted1 = root.get("isDeleted");
                    predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));
                }
                if (!StringUtils.isEmpty(root.get("checkUser"))){
                    Path checkUser1 = root.get("checkUser");
                    predicates.add(criteriaBuilder.equal(checkUser1.as(String.class),userName));
                }
                if (!StringUtils.isEmpty(root.get("checkNumber"))){
                    Path checkNumber1 = root.get("checkNumber");
                    predicates.add(criteriaBuilder.equal(checkNumber1.as(Integer.class),1));
                }

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"updateDate"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);
        Page<Invoice> Page = workRepository.findAll(spec,pageable);

        return Page;
    }

    @Override
    public Page finalWorkFindByMany(Date stime, Date etime, String staff, String id, String findFarmlandRegionId, Integer checkNumber, Integer pageNum, Integer pageRow) {
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
                if (!StringUtils.isEmpty(staff)){
                    Path staff1 = root.get("staff");
                    predicates.add(criteriaBuilder.like(staff1.as(String.class),"%"+staff+"%"));
                }
                if (!StringUtils.isEmpty(findFarmlandRegionId)){
                    Path findFarmlandRegionId1 = root.get("findFarmlandRegionId");
                    predicates.add(criteriaBuilder.like(findFarmlandRegionId1.as(String.class),"%"+findFarmlandRegionId+"%"));
                }
                if (!StringUtils.isEmpty(id)){
                    Path id1 = root.get("id");
                    predicates.add(criteriaBuilder.equal(id1.as(String.class),id));
                }
                if (!StringUtils.isEmpty(root.get("isDeleted"))){
                    Path isDeleted1 = root.get("isDeleted");
                    predicates.add(criteriaBuilder.equal(isDeleted1.as(Integer.class),0));
                }

                if (!StringUtils.isEmpty(root.get("checkNumber"))){
                    if ((!StringUtils.isEmpty(checkNumber)&&(checkNumber != null))){
                        Path checkNumber1 = root.get("checkNumber");
                        predicates.add(criteriaBuilder.equal(checkNumber1.as(Integer.class),checkNumber));
                    }else {
                        Path checkNumber1 = root.get("checkNumber");
                        Predicate predicate = criteriaBuilder.equal(checkNumber1.as(Integer.class),2);
                        Predicate predicate1 = criteriaBuilder.equal(checkNumber1.as(Integer.class),3);
                        Predicate predicate2 = criteriaBuilder.or(predicate,predicate1);
                        predicates.add(predicate2);
                    }
                }

                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"updateDate"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);
        Page<Invoice> Page = workRepository.findAll(spec,pageable);

        return Page;
    }


}
