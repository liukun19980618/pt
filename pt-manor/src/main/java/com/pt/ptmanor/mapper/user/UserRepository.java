package com.pt.ptmanor.mapper.user;

import com.pt.ptmanor.pojo.user.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<SysUser,String>, JpaSpecificationExecutor<SysUser> {


//    List<SysUser> findByDelFlagAndAndRoleIds(String delFlag,String roleIds);

    List<SysUser> findByDelFlag(String delFlag);


    SysUser findByUserName(String userName);
}
