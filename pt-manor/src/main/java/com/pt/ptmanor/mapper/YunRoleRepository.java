package com.pt.ptmanor.mapper;



import com.pt.ptmanor.pojo.YunRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface YunRoleRepository extends JpaRepository<YunRole,Long>, JpaSpecificationExecutor<YunRole> {


    YunRole findByRoleName(String roleName);
}
