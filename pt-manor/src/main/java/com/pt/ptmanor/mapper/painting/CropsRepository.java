package com.pt.ptmanor.mapper.painting;


import com.pt.ptmanor.pojo.painting.Crops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CropsRepository extends JpaRepository<Crops,String>, JpaSpecificationExecutor<Crops> {
}
