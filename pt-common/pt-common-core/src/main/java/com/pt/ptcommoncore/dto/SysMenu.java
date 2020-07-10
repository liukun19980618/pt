package com.pt.ptcommoncore.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author wl
 */
@Data
public class SysMenu {
    /**
     * 菜单ID
     */


    private String menuId;

    /**
     * 菜单名称
     */

    private String menuName;

    /**
     * 父菜单ID
     */

    private String parentId;

    /**
     * 显示顺序
     */

    private Integer orderNum;

    /**
     * 路由地址
     */

    private String path;

    /**
     * 组件路径
     */

    private String component;

    /**
     * 是否为外链（0是 1否）
     */

    private Integer isFrame;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */

    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */

    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */

    private String status;

    /**
     * 权限标识
     */

    private String perms;

    /**
     * 菜单图标
     */

    private String icon;

    /**
     * 创建者
     */

    private String createBy;

    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 更新者
     */

    private String updateBy;

    /**
     * 更新时间
     */

    private Date updateTime;

    /**
     * 备注
     */

    private String remark;

    private static final long serialVersionUID = 1L;


}