package com.pt.ptportal.dao;


import com.pt.ptportal.entity.file;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface fileDao extends JpaRepository<file, Integer>, JpaSpecificationExecutor<file> {
 @Override
 Optional<file> findById(Integer id);

}
