package com.pt.ptuser.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.pt.ptcommoncore.util.R;
import com.pt.ptcommonsecurity.util.SecurityUtils;
import com.pt.ptuser.entity.SysRole;
import com.pt.ptuser.service.SysRoleService;
import com.pt.ptuser.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wl
 */
@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

    private SysRoleService sysRoleService;
    private SysUserService sysUserService;

    /**
     * 分页获取角色列表
     * @param page 分页参数
     * @param role
     * @return
     */
    @GetMapping("/page")
    public R list(Page page, SysRole role)
    {
        return R.ok(sysRoleService.getRolePage(page,role));
    }

    /**
     * 根据角色编号获取详细信息
     */
    @GetMapping(value = "/{roleId}")
    public R getInfo(@PathVariable String roleId)
    {
        return R.ok(sysRoleService.selectRoleById(roleId));
    }

    /**
     * 状态修改
     */

    @PutMapping("/changeStatus")
    public R changeStatus(@RequestBody SysRole role)
    {
        sysRoleService.checkRoleAllowed(sysRoleService.selectRoleById(role.getRoleId()));
        return R.ok(sysRoleService.updateRoleStatus(role));

    }

    /**
     * 修改保存角色
     */

    @PutMapping
    public R edit(@Validated @RequestBody SysRole role)
    {
        sysRoleService.checkRoleAllowed(role);
        sysRoleService.checkRoleNameUnique(role);
        sysRoleService.checkRoleCodeUnique(role);
        return R.ok(sysRoleService.updateRole(role));
    }

    /**
     * 新增角色
     */
    @PostMapping
    public R add( @RequestBody SysRole role)
    {
        sysRoleService.checkRoleNameUnique(role);
        sysRoleService.checkRoleCodeUnique(role);
        role.setCreateBy(sysUserService.getByUserId(SecurityUtils.getUser().getId()).getUserName());
        return R.ok(sysRoleService.insertRole(role));

    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{roleIds}")
    public R remove(@PathVariable String[] roleIds)
    {
        return R.ok(sysRoleService.deleteRoleByIds(roleIds));
    }

}
