package com.pt.ptportal.dao;


import com.pt.ptportal.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface BlogDao extends JpaRepository<Blog, String>, JpaSpecificationExecutor<Blog> {
    Blog findByBlogId(int id);
    List<Blog> findAllByBlogId(int id);
    List<Blog> findAllByStateIsTrue();
    Iterable<Blog> findAllByUserId(String userId);
    Iterable<Blog> findBlogsByBlogIdIn(List<String> ids);
}
