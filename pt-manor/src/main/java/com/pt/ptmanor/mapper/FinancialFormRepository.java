package com.pt.ptmanor.mapper;


import com.pt.ptmanor.pojo.FinancialForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialFormRepository extends JpaRepository<FinancialForm,String>, JpaSpecificationExecutor<FinancialForm> {

    List<FinancialForm> findByIsDeletedAndYear(Integer isDeleted, String year);

    List<FinancialForm> findByIsDeletedAndAndBuyerNameAndYear(Integer isDeleted, String buyerName, String year);

    List<FinancialForm> findByIsDeletedAndAndBuyerNameAndProductNameAndYear(Integer isDeleted, String buyerName, String productName, String year);
}
