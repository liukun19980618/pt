package com.pt.ptuser.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pt.ptuser.entity.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {
    List<SysRole> findRolesByUserId(String UserId, String clientId);

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<SysRole> selectRoleAll();

    /**
     * 根据条件查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    List<SysRole> selectRoleList(SysRole role);
    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    List<String> selectRoleListByUserId(String userId);
    /**
     * 根据角色名获取角色
     * @param roleCode
     * @return
     */
    SysRole getByRoleCode(String roleCode);

    /**
     * 分页获取角色
     * @param page
     * @param sysRole
     * @return
     */
    IPage<List<SysRole>> getRolePage(Page page, SysRole sysRole);

    /**
     * 根据id获取角色
     * @param roleId
     * @return
     */
    SysRole selectRoleById(String roleId);

    /**
     * 校验角色是否允许操作
     *
     * @param role 角色信息
     */
     Boolean checkRoleAllowed(SysRole role);

    /**
     * 修改角色状态
     *
     * @param role 角色信息
     * @return 结果
     */
     Boolean updateRoleStatus(SysRole role);

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    Boolean checkRoleNameUnique(SysRole role);

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    Boolean checkRoleCodeUnique(SysRole role);

    /**
     * 修改保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    Boolean updateRole(SysRole role);

    /**
     * 新增保存角色信息
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
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
     Integer countUserRoleByRoleId(String roleId);

}
