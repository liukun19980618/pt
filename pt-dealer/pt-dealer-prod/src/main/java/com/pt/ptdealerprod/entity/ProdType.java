/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.pt.ptdealerprod.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类型表
 *
 * @author pig code generator
 * @date 2020-03-31 21:33:12
 */
@Data
@ApiModel(value = "类型表")
@TableName(value = "dealer_prod_type")
public class ProdType{
private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "type_id",type = IdType.INPUT)
    @ApiModelProperty(value="主键")
    private String typeId;
    /**
     * 类型编号
     */
    @ApiModelProperty(value="类型编号")
    private String typeNumber;
    /**
     * 类型名称
     */
    @ApiModelProperty(value="类型名称")
    private String typeName;
    /**
     * 类型描述
     */
    @ApiModelProperty(value="类型描述")
    private String typeComment;
	/**
	 * 0--正常 1--删除
	 */
	@TableLogic
	private String delFlag;
    }
