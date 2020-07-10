package com.pt.ptuser.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 用户与岗位关联表
    */

@Data
public class SysUserPost {
    /**
    * 用户ID
    */
//    @ApiModelProperty(value="用户ID")
    private String userId;

    /**
    * 岗位ID
    */
//    @ApiModelProperty(value="岗位ID")
    private String postId;
}