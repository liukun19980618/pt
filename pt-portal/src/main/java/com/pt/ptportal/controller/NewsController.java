package com.pt.ptportal.controller;


import com.pt.ptportal.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.List;

@RestController
@RequestMapping("/News")
public class NewsController {
    @Autowired
    com.pt.ptportal.dao.newsDao newsDao;
    @GetMapping("/findAll/{page}/{size}")
    public Page<News> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //1.1 获取比较的属性
                Path<Object> status =root.get("status");
                //1.2构造查询条件
                Predicate predicate = criteriaBuilder.equal(status, 1);
                return predicate;
            }
        };
        PageRequest request = PageRequest.of(page,size);
        return newsDao.findAll(spec,request);
    }
    //降序
    @GetMapping("/findAllDesc/{page}/{size}")
    public Page<News> findAllDesc(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //1.1 获取比较的属性
                Path<Object> status =root.get("status");
                //1.2构造查询条件
                Predicate predicate = criteriaBuilder.equal(status, 1);
                return predicate;
            }
        };
        PageRequest request = PageRequest.of(page,size, Sort.Direction.DESC,"id");
        return newsDao.findAll(spec,request);
    }
    //查询，返回的数组类型
    @GetMapping("/findAllById/filter={id}")
    public List<News> findAllById(@PathVariable("id") Integer id){
            return newsDao.findAllById(id);
    }

    //返回对象类型
    @GetMapping("/findById/{id}")
    public News findById(@PathVariable("id") Integer id){
       return newsDao.findById(id).get();
    }

    @PostMapping("/addOrUpdate")
    public String addOrUpdate(@RequestBody News news){
        News result = newsDao.save(news);
        if(result !=null){
            return "success";
        }
        else {
            return "error";
        }
    }
    //逻辑删除，将1变为0
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id){
       News news = newsDao.findById(id).get();
       news.setStatus(0);
       newsDao.save(news);
    }
}
