package com.pt.ptmanor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pt.ptmanor.pojo.YunUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YunUserMapper extends BaseMapper<YunUser> {
    int deleteByPrimaryKey(Long id);

    int insert(YunUser record);

    int insertSelective(YunUser record);

    YunUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YunUser record);

    int updateByPrimaryKey(YunUser record);



    @Select("select * from yun_user ym where ym.role_id=#{0}")
     List<YunUser> getYunUserByRoleId(Long rId);


}