package com.pt.ptmanor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pt.ptmanor.pojo.YunRole;
import com.pt.ptmanor.vo.YunRoleVo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface YunRoleMapper  extends BaseMapper<YunRole> {
    int deleteByPrimaryKey(Long id);

    int insert(YunRole record);

    int insertSelective(YunRole record);

    YunRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YunRole record);

    int updateByPrimaryKey(YunRole record);
    @Results({
            @Result(property="yunUser",column="roleId",many=@Many(select="com.manor.demo.mapper.YunUserMapper.getYunUserByRoleId"))
    })
    @Select("SELECT *,id roleId FROM yun_role")
    List<YunRoleVo> getList();


    @Results({
            @Result(property="yunUser",column="roleId",many=@Many(select="com.manor.demo.mapper.YunUserMapper.getYunUserByRoleId"))
    })
    @Select("SELECT *,id roleId from yun_role")
    List<YunRoleVo> getSuperList();






}