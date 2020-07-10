package com.pt.ptuser.service.serviceImpl;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pt.ptcommoncore.constant.CommonConstants;
import com.pt.ptuser.entity.SysUserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pt.ptuser.mapper.SysUserRoleMapper;
import com.pt.ptuser.service.SysUserRoleService;

import java.util.List;

@Service
@AllArgsConstructor
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService{

    @Override
    public List<SysUserRole> getListByUserId(String userId) {
        return baseMapper.getUserRoleList(userId,"");
    }

    @Override
    public Boolean isAdmin(String userId) {
        return baseMapper.getUserRoleList(userId, CommonConstants.ROLE_ADMIN).size() != 0;
    }


    @Override
    public Boolean deleteUserRoleByUserId(String userId) {
        return baseMapper.deleteUserRoleByUserId(userId);
    }

    @Override
    public Boolean deleteUserRole(String[] ids) {
        return baseMapper.deleteUserRole(ids);
    }

    @Override
    public Boolean batchUserRole(List<SysUserRole> userRoleList) {
        return baseMapper.batchUserRole(userRoleList);
    }

    @Override
    public Boolean deleteUserRoleInfo(SysUserRole userRole) {
        return baseMapper.deleteUserRoleInfo(userRole);
    }

    @Override
    public Boolean deleteUserRoleInfos(String roleId, String[] userIds) {
        return baseMapper.deleteUserRoleInfos(roleId,userIds);
    }
}
