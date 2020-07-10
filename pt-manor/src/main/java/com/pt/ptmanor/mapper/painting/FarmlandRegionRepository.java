package com.pt.ptmanor.mapper.painting;


import com.pt.ptmanor.pojo.painting.FarmlandRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmlandRegionRepository extends JpaRepository<FarmlandRegion,String>, JpaSpecificationExecutor<FarmlandRegion> {


    FarmlandRegion findByFarmlandRegionId(String farmlandRegionId);
}
