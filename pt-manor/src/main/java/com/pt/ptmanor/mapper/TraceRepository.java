package com.pt.ptmanor.mapper;


import com.pt.ptmanor.pojo.Trace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TraceRepository  extends JpaRepository<Trace,String>, JpaSpecificationExecutor<Trace> {


}
