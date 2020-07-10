package com.pt.ptmanor.mapper;


import com.pt.ptmanor.pojo.SaleAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleAmountRepository  extends JpaRepository<SaleAmount,String>, JpaSpecificationExecutor<SaleAmount> {


    List<SaleAmount> findByIsDeletedAndYear(Integer isDeleted, String year);

    List<SaleAmount> findByIsDeletedAndYearAndCrops(Integer isDeleted, String year, String crops);

    List<SaleAmount> findByIsDeletedAndYearAndCropsAndBuyerName(Integer isDeleted, String year, String crops, String buyerName);

    List<SaleAmount> findByIsDeletedAndYearAndBuyerName(Integer isDeleted, String year, String buyerName);
}
