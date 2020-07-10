package com.pt.ptuser.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pt.ptuser.entity.SysDept;
import com.pt.ptuser.entity.SysMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Treeselect树结构实体类
 * 
 * @author ruoyi
 */
@Data
public class TreeSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private String id;

    /** 节点名称 */
    private String name;
    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 前端路由标识路径
     */
    private String path;

    private String component;
    /**
     * 权限编码
     */
    private String permission;

    /**
     * 菜单标签
     */
    private String label;
    /**
     * 排序值
     */
    private Integer sort;
    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect()
    {

    }

    public TreeSelect(SysDept dept)
    {
        this.id = dept.getDeptId();
        this.name = dept.getDeptName();
        this.label = dept.getDeptName();
        this.sort = dept.getOrderNum();
        this.children = dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(SysMenu menu)
    {
        this.id = menu.getMenuId();
        this.name = menu.getMenuName();
        this.icon = menu.getIcon();
        this.path = menu.getPath();
        this.component = menu.getComponent();
        this.permission = menu.getPerms();
        this.label = menu.getMenuName();
        this.sort = menu.getOrderNum();
        this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

}
