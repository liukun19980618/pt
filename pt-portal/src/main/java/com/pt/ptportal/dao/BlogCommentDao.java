package com.pt.ptportal.dao;

import com.pt.ptportal.entity.BlogComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogCommentDao extends JpaRepository<BlogComments, String> {
    Iterable<BlogComments> findAllByBlogId(int blogId);
    Iterable<BlogComments> findAllByCommentIdIn(List<String> list);
}
