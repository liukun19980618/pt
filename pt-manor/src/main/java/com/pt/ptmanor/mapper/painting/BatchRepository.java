package com.pt.ptmanor.mapper.painting;


import com.pt.ptmanor.pojo.painting.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch,String>, JpaSpecificationExecutor<Batch> {
}
