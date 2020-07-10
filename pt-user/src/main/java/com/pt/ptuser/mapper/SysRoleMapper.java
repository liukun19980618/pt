package com.pt.ptuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pt.ptuser.entity.SysRole;
import com.pt.ptuser.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> listRolesByUserId(@Param("userId") String userId, @Param("clientId") String clientId);

    SysRole getByRoleCode(@Param("roleCode") String roleCode);
    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    List<String> selectRoleListByUserId(String userId);
    /**
     * 根据条件查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    List<SysRole> selectRoleList(SysRole role);

    /**
     * 分页获取角色
     * @param page 分页参数
     * @param sysRole 角色实体
     * @return
     */
    IPage<List<SysRole>> getRolePage(Page page, @Param("role") SysRole sysRole);

    /**
     * 根据id获取角色
     * @param roleId
     * @return
     */
    SysRole selectRoleById(@Param("roleId") String roleId);
    /**
     * 判断是否为管理员
     * @param roleId
     * @return
     */
    SysRole isAdmin(@Param("roleId") String roleId,@Param("roleCode") String roleCode,@Param("clientId") String clientId);
    /**
     * 修改角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    Boolean updateRole(SysRole role);

    /**
     * 校验角色名称是否唯一
     *
     * @param roleName 角色名称
     * @return 角色信息
     */
    SysRole checkRoleNameUnique(String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleCode 角色权限
     * @return 角色信息
     */
    SysRole checkRoleCodeUnique(String roleCode);



    /**
     * 新增角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    Boolean insertRole(SysRole role);
    /**
     * 批量删除角色信息
     *
     * @param roleIds 需要删除的角色ID
     * @return 结果
     */
    Boolean deleteRoleByIds(String[] roleIds);

    /**
     * 通过角色ID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    Boolean deleteRoleById(String roleId);

    /**
     * 根据用户ID查询角色
     *
     * @param userName 用户名
     * @return 角色列表
     */
    List<SysRole> selectRolesByUserName(String userName);
}