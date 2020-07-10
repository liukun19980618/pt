package com.pt.ptportal.dao;


import com.pt.ptportal.entity.notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface noticeDao extends JpaRepository<notice, Integer>, JpaSpecificationExecutor<notice> {
     List<notice> findAllByNameAndStatus(String name, Integer status);
     List<notice> findAllByStatusAndIsCheck(Integer status, int isCheck);


}
