package com.pt.ptmanor.mapper.painting;


import com.pt.ptmanor.pojo.painting.FarmlandRegionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmlandRegionTypeRepository extends JpaRepository<FarmlandRegionType,String>, JpaSpecificationExecutor<FarmlandRegionType> {
}
