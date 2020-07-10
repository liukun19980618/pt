package com.pt.ptmanor.mapper.painting;


import com.pt.ptmanor.pojo.painting.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionRepository extends JpaRepository<Production,String>, JpaSpecificationExecutor<Production> {


    List<Production> findByIsDeletedAndYear(Integer isDeleted, String year);

    List<Production> findByIsDeletedAndYearAndCrops(Integer isDeleted, String year, String crops);
}
