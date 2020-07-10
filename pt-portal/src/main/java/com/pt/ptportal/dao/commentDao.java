package com.pt.ptportal.dao;


import com.pt.ptportal.entity.comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface commentDao extends JpaRepository<comment, Integer>, JpaSpecificationExecutor<comment> {
    List<comment> findAllById(Integer id);
    Optional<comment> findById(Integer id);


}
