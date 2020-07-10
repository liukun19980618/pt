package com.pt.ptuser.service.serviceImpl;

import com.pt.ptcommonsecurity.exception.CustomException;
import com.pt.ptcommonsecurity.util.SecurityUtils;
import com.pt.ptuser.entity.SysUser;
import com.pt.ptuser.mapper.SysUserMapper;
import com.pt.ptuser.service.SysProfileService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author wl
 */
@Service
@AllArgsConstructor
public class SysProfileServiceImpl implements SysProfileService {
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    private SysUserMapper sysUserMapper;

    @Override
    public Boolean updateUserProfile(SysUser sysUser) {
        if(!sysUserMapper.updateUser(sysUser)){
            throw new CustomException("信息修改错误，请联系管理员!");
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean updatePwd(String oldPassword, String newPassword) {
        SysUser sysUser = sysUserMapper.getByUserId(SecurityUtils.getId());
        if(!ENCODER.encode(oldPassword).equals(sysUser.getPassword())){
            throw new CustomException("密码修改失败，旧密码输入错误!");
        }
        if(oldPassword.equals(newPassword)){
            throw new CustomException("新密码不能与旧密码相同!");
        }
        if(!sysUserMapper.resetUserPwd(SecurityUtils.getUserName(),ENCODER.encode(newPassword))){
            throw new CustomException("密码修改失败，请联系管理员!");
        }
        return Boolean.TRUE;
    }
}
