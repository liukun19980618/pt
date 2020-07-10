package com.pt.ptuser.controller;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.pt.ptcommoncore.security.CustomUser;
import com.pt.ptcommoncore.util.R;
import com.pt.ptcommonsecurity.util.SecurityUtils;
import com.pt.ptuser.dto.UserInfo;
import com.pt.ptuser.entity.SysUser;
import com.pt.ptuser.mapper.SysUserMapper;
import com.pt.ptuser.service.SysDeptService;
import com.pt.ptuser.service.SysProfileService;
import com.pt.ptuser.service.SysRoleService;
import com.pt.ptuser.service.SysUserService;
import lombok.AllArgsConstructor;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.beanutils.BeanUtils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 个人信息 业务处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/user/profile")
@AllArgsConstructor
public class ProfileController
{

    private SysUserService sysUserService;
    private SysDeptService sysDeptService;
    private SysProfileService sysProfileService;
    private SysUserMapper sysUserMapper;



    /**
     * 个人信息
     */
    @GetMapping
    public R profile() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {


        SysUser sysUser = sysUserService.getByUserId(SecurityUtils.getUser().getId());
        Map<String, String> result = BeanUtils.describe(sysUser);
        result.put("deptName",sysDeptService.selectDeptById(sysUser.getDeptId()).getDeptName());
        result.put("roleGroup", sysUserService.selectUserRoleGroup(SecurityUtils.getUserName()));
        result.put("postGroup", sysUserService.selectUserPostGroup(SecurityUtils.getUserName()));

        return R.ok(result);
    }

    /**
     * 修改用户
     */
    @PutMapping
    public R updateProfile(@RequestBody SysUser user)
    {
        sysProfileService.updateUserProfile(user);
        return R.ok(sysUserService.getByUserId(SecurityUtils.getId()));
    }

    /**
     * 重置密码
     */
    @PutMapping("/updatePwd")
    public R updatePwd(String oldPassword, String newPassword)
    {
        sysProfileService.updatePwd(oldPassword,newPassword);
        return R.ok();
    }

    @PostMapping("/updateAvatar")
    public  R updateAvatar(@RequestBody  String avatar){

        CustomUser user1 = SecurityUtils.getUser();
        SysUser byUserId = sysUserMapper.getByUserId(user1.getId());
        byUserId.setAvatar(avatar);
        boolean save = sysUserMapper.updateUser(byUserId);
        return R.ok();
    }





}
