package com.pt.ptuser.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pt.ptcommoncore.constant.CommonConstants;
import com.pt.ptcommoncore.util.IdUtils;
import com.pt.ptcommoncore.util.R;
import com.pt.ptcommonsecurity.exception.CustomException;
import com.pt.ptuser.entity.SysRoleMenu;
import com.pt.ptuser.mapper.SysUserRoleMapper;
import com.pt.ptuser.service.SysRoleMenuService;
import com.pt.ptuser.service.SysUserRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.pt.ptuser.mapper.SysRoleMapper;
import com.pt.ptuser.entity.SysRole;
import com.pt.ptuser.service.SysRoleService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    private SysRoleMenuService roleMenuService;
    private SysUserRoleService sysUserRoleService;
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysRole> findRolesByUserId(String UserId, String clientId) {
        return baseMapper.listRolesByUserId(UserId,clientId);
    }

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    @Override
    public List<String> selectRoleListByUserId(String userId)
    {
        return baseMapper.selectRoleListByUserId(userId);
    }

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    @Override
    public List<SysRole> selectRoleAll()
    {
        return selectRoleList(new SysRole());
    }

    /**
     * 根据条件查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
    public List<SysRole> selectRoleList(SysRole role)
    {
        return baseMapper.selectRoleList(role);
    }

    /**
     * 根据角色名获取角色
     * @param roleCode
     * @return
     */
    @Override
    public SysRole getByRoleCode(String roleCode) {
        return baseMapper.getByRoleCode(roleCode);
    }

    @Override
    public IPage<List<SysRole>> getRolePage(Page page, SysRole sysRole) {
        return baseMapper.getRolePage(page,sysRole);
    }

    /**
     * 根据id获取角色
     * @param roleId
     * @return
     */
    @Override
    public SysRole selectRoleById(String roleId) {
        return baseMapper.selectRoleById(roleId);
    }

    /**
     * 检验是否为管理员
     * @param role 角色信息
     * @return
     */
    @Override
    public Boolean checkRoleAllowed(SysRole role) {
        if (StrUtil.isNotEmpty(role.getRoleId()) &&this.selectRoleById(role.getRoleId())
                .getRoleCode().equals(CommonConstants.ROLE_ADMIN)) {
            throw new CustomException("修改用户'" + role.getRoleName() + "'失败，无修改权限");
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateRoleStatus(SysRole role) {
        return baseMapper.updateRole(role);
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public Boolean checkRoleNameUnique(SysRole role)
    {

        SysRole sysRole = baseMapper.checkRoleNameUnique(role.getRoleName());
        if(sysRole != null && !sysRole.getRoleId().equals(role.getRoleId())){
            throw new CustomException("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        return Boolean.TRUE;
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public Boolean checkRoleCodeUnique(SysRole role)
    {

        SysRole sysRole = baseMapper.checkRoleCodeUnique(role.getRoleCode());
        if(sysRole != null && !sysRole.getRoleId().equals(role.getRoleId())){
            throw new CustomException("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        return Boolean.TRUE;
    }
    /**
     * 修改保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public Boolean updateRole(SysRole role) {
        // 修改角色信息
        baseMapper.updateRole(role);
        // 删除角色与菜单关联
        roleMenuService.deleteRoleMenuByRoleId(role.getRoleId());
        return insertRoleMenu(role);
    }
    /**
     * 新增保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public Boolean insertRole(SysRole role) {
        role.setRoleId(IdUtils.simpleUUID());
        // 新增角色信息
        baseMapper.insertRole(role);
        return insertRoleMenu(role);
    }

    /**
     * 新增角色菜单信息
     *
     * @param role 角色对象
     */
    public Boolean insertRoleMenu(SysRole role)
    {

        // 新增用户与角色管理
        List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
        for (String menuId : role.getMenuIds())
        {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            return roleMenuService.batchRoleMenu(list);
        }
        return Boolean.FALSE;
    }

    /**
     * 批量删除角色信息
     *
     * @param roleIds 需要删除的角色ID
     * @return 结果
     */
    @Override
    public Boolean deleteRoleByIds(String[] roleIds)
    {
        SysRole sysRole = new SysRole();
        for (String roleId : roleIds)
        {
            checkRoleAllowed(sysRole);
            SysRole role = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0)
            {
                throw new CustomException(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
        }
        return baseMapper.deleteRoleByIds(roleIds);
    }

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public Integer countUserRoleByRoleId(String roleId)
    {
        return sysUserRoleMapper.countUserRoleByRoleId(roleId);
    }

}
