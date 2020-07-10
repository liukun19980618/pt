package com.pt.ptmanor.mapper.painting;


import com.pt.ptmanor.pojo.painting.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work,String>, JpaSpecificationExecutor<Work> {
}
