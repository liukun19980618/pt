package com.pt.ptuser.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wl
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenu extends Model<SysRoleMenu> {

    /**
     * 角色id
     */
//    @ApiModelProperty(value="角色id")
    private String roleId;
    /**
    * 菜单id
    */
//    @ApiModelProperty(value="菜单id")
    private String menuId;



}