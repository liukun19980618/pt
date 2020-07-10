package com.pt.ptmanor.mapper;


import com.pt.ptmanor.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterprisesRepository extends JpaRepository<Enterprise,String>, JpaSpecificationExecutor<Enterprise> {

    Enterprise findByEnterpriseName(String enterpriseName);


}
