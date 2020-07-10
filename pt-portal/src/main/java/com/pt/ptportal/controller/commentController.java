package com.pt.ptportal.controller;




import com.pt.ptportal.entity.News;
import com.pt.ptportal.entity.comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class commentController {
    @Autowired
    com.pt.ptportal.dao.commentDao commentDao;

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
        return commentDao.findAll(spec,request);
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
        return commentDao.findAll(spec,request);
    }
    //查询，返回的数组类型
    @GetMapping("/findAllById/filter={id}")
    public List<comment> findAllById(@PathVariable("id") Integer id){
        return commentDao.findAllById(id);
    }

    //返回对象类型
    @GetMapping("/findById/{id}")
    public comment findById(@PathVariable("id") Integer id){
        return commentDao.findById(id).get();
    }



    @PostMapping("/addOrUpdate")
    public String addOrUpdate(@RequestBody comment comment ){
        com.pt.ptportal.entity.comment result = commentDao.save(comment);
        if(result !=null){
            return "success";
        }
        else {
            return "error";
        }
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id){
        comment comment = commentDao.findById(id).get();
        comment.setStatus(0);
        commentDao.save(comment);

    }

}
