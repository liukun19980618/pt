package com.pt.ptmanor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pt.ptmanor.pojo.YunRoleMenus;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface YunRoleMenusMapper extends BaseMapper<YunRoleMenus> {
    int deleteByPrimaryKey(Long id);

    int insert(YunRoleMenus record);

    int insertSelective(YunRoleMenus record);

    YunRoleMenus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YunRoleMenus record);

    int updateByPrimaryKey(YunRoleMenus record);

    @Select("delete from  yun_role_menus where role_id=#{0}")
    void deleteByRoleId(Long roleId);
}