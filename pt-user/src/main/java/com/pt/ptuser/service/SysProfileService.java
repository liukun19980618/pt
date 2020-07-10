package com.pt.ptuser.service;

import com.pt.ptuser.entity.SysUser;

/**
 * @author wl
 */
public interface SysProfileService {
    Boolean updateUserProfile(SysUser sysUser);

    Boolean updatePwd(String oldPassword, String newPassword);
}
