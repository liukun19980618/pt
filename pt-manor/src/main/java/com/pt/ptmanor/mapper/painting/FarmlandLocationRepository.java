package com.pt.ptmanor.mapper.painting;



import com.pt.ptmanor.pojo.painting.FarmlandLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface FarmlandLocationRepository extends JpaRepository<FarmlandLocation,String>, JpaSpecificationExecutor<FarmlandLocation> {

    FarmlandLocation findByFarmlandLocationName(String FarmlandLocationName);

    FarmlandLocation findByFarmlandLocationId(String farmlandLocationId);
}
