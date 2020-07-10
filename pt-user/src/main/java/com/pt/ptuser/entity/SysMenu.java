package com.pt.ptuser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends Model<SysMenu> {
    /**
     * 菜单ID
     */
//    @ApiModelProperty(value="菜单ID")
    @TableId(value = "menu_id",type = IdType.INPUT)
    private String menuId;

    /**
     * 菜单名称
     */
//    @ApiModelProperty(value="菜单名称")
    private String menuName;

    /**
     * 父菜单ID
     */
//    @ApiModelProperty(value="父菜单ID")
    private String parentId;

    /**
     * 显示顺序
     */
//    @ApiModelProperty(value="显示顺序")
    private Integer orderNum;

    /**
     * 路由地址
     */
//    @ApiModelProperty(value="路由地址")
    private String path;

    /**
     * 组件路径
     */
//    @ApiModelProperty(value="组件路径")
    private String component;

    /**
     * 是否为外链（0是 1否）
     */
//    @ApiModelProperty(value="是否为外链（0是 1否）")
    private Integer isFrame;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
//    @ApiModelProperty(value="菜单类型（M目录 C菜单 F按钮）")
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
//    @ApiModelProperty(value="菜单状态（0显示 1隐藏）")
    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */
//    @ApiModelProperty(value="菜单状态（0正常 1停用）")
    private String status;

    /**
     * 权限标识
     */
//    @ApiModelProperty(value="权限标识")
    private String perms;

    /**
     * 菜单图标
     */
//    @ApiModelProperty(value="菜单图标")
    private String icon;

    /**
     * 创建者
     */
//    @ApiModelProperty(value="创建者")
    private String createBy;

    /**
     * 创建时间
     */
//    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新者
     */
//    @ApiModelProperty(value="更新者")
    private String updateBy;

    /**
     * 更新时间
     */
//    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    /**
     * 备注
     */
//    @ApiModelProperty(value="备注")
    private String remark;

    /** 子部门 */
    private List<SysMenu> children = new ArrayList<SysMenu>();

    /** 请求参数 */
    private Map<String, Object> params = new HashMap<>();

    private static final long serialVersionUID = 1L;
}