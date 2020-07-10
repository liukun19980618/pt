package com.pt.ptportal.controller;
import com.pt.ptportal.dao.BlogDao;
import com.pt.ptportal.entity.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {
    private final BlogDao blogDao;
    @Autowired
    public BlogController(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    @PostMapping("/addOrUpdate")
    public String addOrUpdate(@RequestBody Blog blog){
        Blog result = blogDao.save(blog);
        if(result !=null){
            return "success";
        }
        else {
            return "error";
        }
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id){
        Blog blog = blogDao.findByBlogId(id);
        blog.setState(false);
        blogDao.save(blog);
    }
    @GetMapping("/get/all")
    public Iterable<Blog> getAllBlog() {
        return blogDao.findAll();
    }
    //降序
    @GetMapping("/findAllDesc/{page}/{size}")
    public Page<Blog> findAllDesc(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //1.1 获取比较的属性
                Path<Object> state =root.get("state");
                //1.2构造查询条件
                Predicate predicate = criteriaBuilder.equal(state, true);
                return predicate;
            }
        };
        PageRequest request = PageRequest.of(page,size, Sort.Direction.DESC,"BlogId");
        return blogDao.findAll(spec,request);
    }

    @GetMapping("/get/available")
    public List<Blog> getAvailable(){
        return blogDao.findAllByStateIsTrue();
    }

    @GetMapping("/get/by-user/{id}")
    public Iterable<Blog> getBlogByUser(@PathVariable("id") String id) {
        return blogDao.findAllByUserId(id);
    }
    @GetMapping("/get/by-BlogId/{id}")
    public List<Blog> getBlogByBlogId(@PathVariable("id") int id) {
        return blogDao.findAllByBlogId(id);
    }


}
