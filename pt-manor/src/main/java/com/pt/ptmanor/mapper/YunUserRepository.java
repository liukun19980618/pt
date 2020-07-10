package com.pt.ptmanor.mapper;


import com.pt.ptmanor.pojo.YunUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface YunUserRepository extends JpaRepository<YunUser,Long> , JpaSpecificationExecutor<YunUser> {


    YunUser findByUserNameAndIsDelete(String userName, String isDelete);

    YunUser findByUserName(String userName);


}
