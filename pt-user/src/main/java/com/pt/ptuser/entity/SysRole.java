package com.pt.ptuser.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.pt.ptuser.dto.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseEntity {

    private String roleId;


    private String roleName;


    private String roleCode;

    private Integer roleSort;

    private String createBy;

    private Date createTime;

    private char status;

    private String[] menuIds;

    /**
     * 0-正常，1-删除
     */
    @TableLogic
    private String delFlag;


}