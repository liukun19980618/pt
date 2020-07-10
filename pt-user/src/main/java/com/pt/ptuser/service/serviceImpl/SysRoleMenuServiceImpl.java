package com.pt.ptuser.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pt.ptuser.entity.SysRoleMenu;
import com.pt.ptuser.mapper.SysRoleMenuMapper;
import com.pt.ptuser.service.SysRoleMenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Override
    public List<SysRoleMenu> listRoleMenu(String roleId, String clientId) {
        return baseMapper.listRoleMenu(roleId,clientId);
    }

    @Override
    public Boolean deleteRoleMenuByRoleId(String roleId) {
        return baseMapper.deleteRoleMenuByRoleId(roleId);
    }

    /**
     * 批量新增角色菜单信息
     *
     * @param roleMenuList 角色菜单列表
     * @return 结果
     */
    @Override
    public Boolean batchRoleMenu(List<SysRoleMenu> roleMenuList){
        return baseMapper.batchRoleMenu(roleMenuList);
    };
    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int checkMenuExistRole(String menuId){ return baseMapper.checkMenuExistRole(menuId);};

}
