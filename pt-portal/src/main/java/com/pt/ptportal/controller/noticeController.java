package com.pt.ptportal.controller;
import com.pt.ptportal.entity.notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.List;

@RestController
@RequestMapping("/notice")
public class noticeController {

    @Autowired
    com.pt.ptportal.dao.noticeDao noticeDao;
    @GetMapping("/findAll/{name}")
    public List<notice> findAllbyName(@PathVariable("name") String name){
        return noticeDao.findAllByNameAndStatus(name,1);
    }
    @GetMapping("/findAll")
    public List<notice> findAll(){
        return noticeDao.findAllByStatusAndIsCheck(1,2);
    }
    @PostMapping("/tocheck")
    public notice tocheck( @RequestBody notice notice){
        notice.setIsCheck(1);
        noticeDao.save(notice);
        return notice;
    }
    @PostMapping("/backCheck")
    public notice backCheck( @RequestBody notice notice){
        notice.setIsCheck(0);
        noticeDao.save(notice);
        return notice;
    }
    @PostMapping("/backCheck3")
    public notice backCheck3( @RequestBody notice notice){
        notice.setIsCheck(3);
        noticeDao.save(notice);
        return notice;
    }
    @PostMapping("/backCheck2")
    public notice backCheck2( @RequestBody notice notice){
        notice.setIsCheck(2);
        noticeDao.save(notice);
        return notice;
    }

    @GetMapping("/findAll/{page}/{size}")
    public Page<notice> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //1.1 获取比较的属性
                Path<Object> status =root.get("status");
                Path<Object> isCheck = root.get("isCheck");
                //1.2构造查询条件
                Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(status, 1),criteriaBuilder.equal(isCheck,1));
                return predicate;
            }
        };
        PageRequest request = PageRequest.of(page,size);
        return noticeDao.findAll(spec,request);
    }

    //降序
    @GetMapping("/findAllDesc/{page}/{size}")
    public Page<notice> findAllDesc(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> status =root.get("status");
                Path<Object> isCheck = root.get("isCheck");
                //1.2构造查询条件
                Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(status, 1),criteriaBuilder.equal(isCheck,2));
                return predicate;
            }
        };
        PageRequest request = PageRequest.of(page,size, Sort.Direction.DESC,"id");
        return noticeDao.findAll(spec,request);
    }
    @GetMapping("/findById/{id}")
    public notice findById(@PathVariable("id") Integer id){
        return noticeDao.findById(id).get();
    }
    @PostMapping("/addOrUpdate")
    public String addOrUpdate(@RequestBody notice notice ){
        notice result = noticeDao.save(notice);
        if(result !=null){
            return "success";
        }
        else {
            return "error";
        }
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id){
        notice notice = noticeDao.findById(id).get();
        notice.setStatus(0);
        noticeDao.save(notice);

    }

}
