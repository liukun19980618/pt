package com.pt.ptportal.controller;

import com.pt.ptportal.dao.BlogCommentDao;
import com.pt.ptportal.entity.BlogComments;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_MODIFIED;
import static org.springframework.http.HttpStatus.OK;


@Slf4j
@RestController
@RequestMapping(value = "/Blogcomment")
public class BlogCommentController {

    private final BlogCommentDao blogCommentDao;

    @Autowired
    public BlogCommentController(BlogCommentDao commentDao) {
        this.blogCommentDao = commentDao;
    }
    @GetMapping("/getAll")
    public List<BlogComments> getAll(){
      return blogCommentDao.findAll();
    }
    @PostMapping("/addOrUpdate")
    public String addOrUpdate(@RequestBody BlogComments blogComments){
        BlogComments result = blogCommentDao.save(blogComments);
        if(result !=null){
            return "success";
        }
        else {
            return "error";
        }
    }

    @GetMapping("/get/by-blog/{id}")
    public Iterable<BlogComments> getCommentByBlog(@PathVariable("id")  int id){

        return blogCommentDao.findAllByBlogId(id);
    }
    @PutMapping("/update")
    public ResponseEntity updateComment(@RequestHeader("uid") String uid, @RequestBody BlogComments comments){
        //获取当前系统时间
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format( now );
        BlogComments commentsNew = new BlogComments(
                comments.getCommentId(),
                comments.getUserId(),
                comments.getUserName(),
                comments.getContent(),
                comments.getBlogId(),
                comments.getParentId(),
                comments.getParentName(),
                comments.getCreateTime(),
                comments.getState()

        );
        return new ResponseEntity<>(blogCommentDao.save(commentsNew),OK);
    }
    //此处第一个参数id：commentId
    @PutMapping("/set/state")
    public ResponseEntity setState(@RequestHeader("uid") String uid, @RequestBody List<String> list) {
        final Iterable<BlogComments> commentList = blogCommentDao.findAllByCommentIdIn(list);
       if(commentList.iterator().hasNext()){
           //获取当前系统时间
           Date now = new Date();
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String date = dateFormat.format(now);
           for(BlogComments comment:commentList){
               comment.setState(!comment.getState());

               blogCommentDao.save(comment);
           }
           return new ResponseEntity<>(commentList,OK);
       }else return new ResponseEntity<>(NOT_MODIFIED);
    }
}
