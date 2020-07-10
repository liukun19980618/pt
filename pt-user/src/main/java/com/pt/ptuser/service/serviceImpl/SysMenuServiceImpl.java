package com.pt.ptuser.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pt.ptcommoncore.constant.CommonConstants;
import com.pt.ptcommoncore.security.CustomUser;
import com.pt.ptcommoncore.util.IdUtils;
import com.pt.ptcommonsecurity.exception.CustomException;
import com.pt.ptcommonsecurity.util.SecurityUtils;
import com.pt.ptuser.dto.TreeSelect;
import com.pt.ptuser.entity.SysDept;
import com.pt.ptuser.entity.SysRole;
import com.pt.ptuser.entity.SysRoleMenu;
import com.pt.ptuser.mapper.SysRoleMapper;
import com.pt.ptuser.service.*;
import com.pt.ptuser.vo.MetaVo;
import com.pt.ptuser.vo.RouterVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.pt.ptuser.mapper.SysMenuMapper;
import com.pt.ptuser.entity.SysMenu;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wl
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private SysRoleMenuService sysRoleMenuService;
    private SysMenuMapper sysMenuMapper;
    private SysRoleMapper sysRoleMapper;
    private SysUserRoleService sysUserRoleService;

    /**
     * 根据角色获取权限
     * @param roleId
     * @return
     */
    @Override
    public List<String> findPermissionsByRoleId(String roleId,String clientId) {
        List<String> permissionsList = new ArrayList<>();
        if(sysRoleMapper.isAdmin(roleId,CommonConstants.ROLE_ADMIN,clientId) != null){
            List<SysMenu> allMenu = sysMenuMapper.listAllMenu(clientId);
            allMenu.stream().forEach(menu -> {
                String perms = menu.getPerms();
                if(perms != null){
                    permissionsList.add(perms);
                }
            });
        }else{
            List<SysRoleMenu> sysRoleMenuList = sysRoleMenuService.listRoleMenu(roleId,clientId);
            sysRoleMenuList.stream().forEach(dealerRoleMenu -> {
                String perms = sysMenuMapper.getMenuById(dealerRoleMenu.getMenuId(),clientId).getPerms();
                if(perms != null){
                    permissionsList.add(perms);
                }
            });
        }
        return permissionsList;
    }



    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuList(String userId)
    {
        return selectMenuList(new SysMenu(), userId);
    }
    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuList(SysMenu menu, String userId)
    {
        List<SysMenu> menuList = null;
        Boolean isAdmin = sysUserRoleService.isAdmin(userId);
        // 管理员显示所有菜单信息
        if (isAdmin)
        {
            menuList = sysMenuMapper.selectMenuList(menu);
        }
        else
        {
            menu.getParams().put("userId", userId);
            menuList = sysMenuMapper.selectMenuListByUserId(menu);
        }
        return menuList;
    }

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<String> selectMenuListByRoleId(String roleId)
    {
        return sysMenuMapper.selectMenuListByRoleId(roleId);
    }
    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户名称
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuTreeByUserId(String userId)
    {
        List<SysMenu> menus = null;
        if (sysUserRoleService.isAdmin(userId))
        {
            menus = sysMenuMapper.selectMenuTreeAll();
        }
        else
        {
            menus = sysMenuMapper.selectMenuTreeByUserId(userId);
        }
        return getChildPerms(menus, CommonConstants.TREE_ROOT);
    }
    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, String parentId)
    {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext();)
        {
            SysMenu t = (SysMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId().equals(parentId))
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }
    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus)
    {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenu menu : menus)
        {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(StringUtils.capitalize(menu.getPath()));
            router.setPath(getRouterPath(menu));
            router.setComponent(StrUtil.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
            List<SysMenu> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && "M".equals(menu.getMenuType()))
            {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenu menu)
    {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录
        if (menu.getParentId().equals(CommonConstants.TREE_ROOT) && "1".equals(menu.getIsFrame()))
        {
            routerPath = "/" + menu.getPath();
        }
        return routerPath;
    }
    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus)
    {
        List<SysMenu> menuTrees = buildMenuTree(menus);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }
    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    @Override
    public List<SysMenu> buildMenuTree(List<SysMenu> menus)
    {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (Iterator<SysMenu> iterator = menus.iterator(); iterator.hasNext();)
        {
            SysMenu t = (SysMenu) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId().equals(CommonConstants.TREE_ROOT) )
            {
                recursionFn(menus, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = menus;
        }
        return returnList;
    }
    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list, SysMenu t)
    {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<SysMenu> it = childList.iterator();
                while (it.hasNext())
                {
                    SysMenu n = (SysMenu) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }
    /**
     * 得到子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t)
    {
        List<SysMenu> tlist = new ArrayList<SysMenu>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext())
        {
            SysMenu n = (SysMenu) it.next();
            if (n.getParentId().equals(t.getMenuId()))
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public SysMenu selectMenuById(String menuId)
    {
        return sysMenuMapper.selectMenuById(menuId);
    }

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public Boolean insertMenu(SysMenu menu)
    {
        menu.setMenuId(IdUtils.simpleUUID());
        if(menu.getMenuType().equals("M")){
            menu.setComponent("Layout");
        }
        return sysMenuMapper.insertMenu(menu);
    }

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public Boolean updateMenu(SysMenu menu)
    {
        if(menu.getMenuType().equals("M")){
            menu.setComponent("Layout");
        }
        return sysMenuMapper.updateMenu(menu);
    }
    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public Boolean checkMenuNameUnique(SysMenu menu)
    {
        if(StrUtil.isEmpty(menu.getMenuId())){
            return Boolean.TRUE;
        }

        SysMenu sysMenu = sysMenuMapper.checkMenuNameUnique(menu.getMenuName(),menu.getParentId());
        if (sysMenu != null && !sysMenu.getMenuId().equals(menu.getMenuId()))
        {
            throw new CustomException("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }

        return Boolean.TRUE;

    }
    /**
     * 是否存在菜单子节点
     *
     * @return 结果
     */
    @Override
    public Boolean hasChildByMenuId(String menuId)
    {
        int result = sysMenuMapper.hasChildByMenuId(menuId);
        if(result > 0){
            throw new CustomException("存在子菜单,不允许删除");
        }
        return  Boolean.TRUE;
    }

    /**
     * 查询菜单使用数量
     *
     * @return 结果
     */
    @Override
    public Boolean checkMenuExistRole(String menuId)
    {
        int result = sysRoleMenuService.checkMenuExistRole(menuId);
        if(result > 0){
            throw new CustomException("菜单已分配,不允许删除");
        }
        return  Boolean.TRUE;
    }
    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public Boolean deleteMenuById(String menuId)
    {
        return sysMenuMapper.deleteMenuById(menuId);
    }
}
