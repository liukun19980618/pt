package com.pt.ptuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.pt.ptcommoncore.dto.MenuVO;
import com.pt.ptuser.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 按角色获取路由
     * @param role
     * @return
     */
    List<SysMenu> listRoutesByRole(@Param("role") String role);
    /**
     * 按角色获取菜单
     * @param role
     * @return
     */
    List<MenuVO> listMenuByRole(@Param("role") String role);
    /**
     * 获取所有菜单
     *
     * @return 菜单列表
     */
    List<SysMenu> listAllMenu(@Param("clientId") String clientId);

    SysMenu getMenuById(@Param("menuId") String menuId,@Param("clientId") String clientId);

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenuListByUserId(SysMenu menu);

    /**
     * 根据角色获取菜单列表
     * @param roleId
     * @return
     */
    List<String> selectMenuListByRoleId(@Param("roleId") String roleId);

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    SysMenu selectMenuById(String menuId);
    /**
     * 新增菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    Boolean insertMenu(SysMenu menu);

    /**
     * 修改菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    Boolean updateMenu(SysMenu menu);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menuName 菜单名称
     * @param parentId 父菜单ID
     * @return 结果
     */
    SysMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") String parentId);

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int hasChildByMenuId(String menuId);

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    Boolean deleteMenuById(String menuId);

    /**
     * 根据用户ID查询菜单
     *
     * @return 菜单列表
     */
    public List<SysMenu> selectMenuTreeAll();

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<SysMenu> selectMenuTreeByUserId(String userId);
}