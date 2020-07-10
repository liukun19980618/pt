package com.pt.ptmanor.service;


import com.pt.ptmanor.mapper.TraceRepository;
import com.pt.ptmanor.pojo.FinancialForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;

@Service
public class TraceServiceImpl  implements TraceService {

    @Autowired
    TraceRepository traceRepository;


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
        Page<FinancialForm> Page = traceRepository.findAll(spec,pageable);

        return Page ;
    }
}
