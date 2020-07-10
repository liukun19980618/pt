package com.pt.ptmanor.service.painting;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pt.ptmanor.mapper.painting.ProductionRepository;
import com.pt.ptmanor.pojo.painting.Production;
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
import java.util.*;
import java.util.function.Function;

@Service
public class ProductionServiceImpl  implements ProductionService {

//    extends ServiceImpl<ChartsMapper, Charts> implements  ChartsService

    @Autowired
    ProductionRepository productionRepository;


//    @Autowired
//    private ProductionMapper productionMapper;



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
//        Page<Product> Page = productionRepository.findAll(spec,pageable);

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"date"));
        Sort sort = Sort.by(orders);
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow,sort);

        Page<Product> Page = productionRepository.findAll(spec,pageable);

        return Page ;
    }

//    @Override
//    public Map getList() {
//
//        List<Production> aList = productionMapper.getListBycrops("苹果");
//
//        List<Production> tList = productionMapper .getListBycrops("桃子");
//
//        List<Integer> aListForCount = new ArrayList<>();
//
//        List<Integer> tListForCount = new ArrayList<>();
//
//        List<Integer> sumListForCount = new ArrayList<>();
//
//        for (Production achart:aList){
//            for (Production tchart:tList){
//                if (achart.getDate().equals(tchart.getDate())){
//                    aListForCount.add(achart.getAmount());
//                    tListForCount.add(tchart.getAmount());
//                    sumListForCount.add(achart.getAmount()+tchart.getAmount());
//                }
//            }
//        }
//        Map map = new HashMap();
//        map.put("aListForCount",aListForCount);
//        map.put("tListForCount",tListForCount);
//        map.put("sumListForCount",sumListForCount);
//
//        return map;
//    }

    @Override
    public Page productList(int pageNum, int pageRow) {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                //1.1 获取比较的属性
                Path<Object> isDeleted =root.get("isDeleted");
                //1.2构造查询条件
                Predicate predicate = criteriaBuilder.equal(isDeleted, 0);
                predicates.add(predicate);

                Path<Object> type = root.get("type");
                Predicate predicate1 = criteriaBuilder.equal(type, "采摘");

                predicates.add(predicate1);
                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        //2. 查询符合条件的数据并返回前端
        //2.1 设置根据修改时间倒叙排序
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow);
        Page<Product> Page = productionRepository.findAll(spec,pageable);

        return Page ;
    }

    @Override
    public Page saleList(int pageNum, int pageRow) {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                //1.1 获取比较的属性
                Path<Object> isDeleted =root.get("isDeleted");
                //1.2构造查询条件
                Predicate predicate = criteriaBuilder.equal(isDeleted, 0);
                predicates.add(predicate);

                Path<Object> type = root.get("type");
                Predicate predicate1 = criteriaBuilder.equal(type, "销售");

                predicates.add(predicate1);
                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        //2. 查询符合条件的数据并返回前端
        //2.1 设置根据修改时间倒叙排序
        Pageable pageable  = PageRequest.of(pageNum-1,pageRow);
        Page<Product> Page = productionRepository.findAll(spec,pageable);

        return Page ;
    }

    @Override
    public Map chart(String year) {

        List<Production> byIsDeleted = productionRepository.findByIsDeletedAndYear(0,year);
        List<Integer> amount = new ArrayList<>();
        int i;
        for (i=0;i<12;i++){
          Integer all = 0;
            for (Production production:byIsDeleted){

                Calendar ca = Calendar.getInstance();
                ca.setTime(production.getDate());

                int month = ca.get(Calendar.MONTH);//第几个月
                if (month==i){
                    all = all+production.getAmount();
                }
            }
            amount.add(all);
        }
        Map map= new HashMap();
        map.put("list",amount);
        return map;
    }


    @Override
    public Map findChart(String year, String crops) {
        List<Production> byIsDeletedAndYearAndCrops = productionRepository.findByIsDeletedAndYearAndCrops(0, year, crops);
        List<Integer> amount = new ArrayList<>();
        int i;
        for (i=0;i<12;i++){
            Integer all = 0;
            for (Production production:byIsDeletedAndYearAndCrops){

                Calendar ca = Calendar.getInstance();
                ca.setTime(production.getDate());

                int month = ca.get(Calendar.MONTH);//第几个月
                if (month==i){
                    all = all+production.getAmount();
                }
            }
            amount.add(all);
        }
        Map map= new HashMap();
        map.put("list",amount);
        return map;
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
        Page<Invoice> Page = productionRepository.findAll(spec,pageable);

        return Page;
    }

    @Override
    public boolean saveBatch(Collection<Production> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Production> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Production> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Production entity) {
        return false;
    }

    @Override
    public Production getOne(Wrapper<Production> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Production> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Production> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Production> getBaseMapper() {
        return null;
    }


//    @Over
//
//        for (Charts wchart:wList) {
//            for (Charts mchart:mList) {
//                if(wchart.getTime().equals(mchart.getTime())){
//                    wListForCount.add(wchart.getCount());
//                    mListForCount.add(mchart.getCount());
//                    sumListForCount.add(wchart.getCount()+mchart.getCount());
//                }
//            }
//
//        }
//        Map map = new HashMap();
//        map.put("wListForCount",wListForCount);
//        map.put("mListForCount",mListForCount);
//        map.put("sumListForCount",sumListForCount);
//
//        return map;
//    }





















}
