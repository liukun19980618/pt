package com.pt.ptportal.dao;



import com.pt.ptportal.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;


public interface newsDao  extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News> {

     List<News> findAllById(Integer id);
     Optional<News> findById(Integer id);

}
