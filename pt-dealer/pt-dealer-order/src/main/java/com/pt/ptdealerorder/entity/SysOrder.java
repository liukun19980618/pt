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

package com.pt.ptdealerorder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * @author wl
 * @date 2020/6/11
 */
@Data
@ApiModel(value = "")
public class SysOrder {
private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id",type = IdType.INPUT)
    @ApiModelProperty(value="订单id")
    private String orderId;
    /**
     * 卖方id
     */
    @ApiModelProperty(value="卖方id")
    private String sellerId;
    /**
     * 卖方公司名称
     */
    @ApiModelProperty(value="卖方公司名称")
    private String sellerName;
    /**
     * 买方id
     */
    @ApiModelProperty(value="买方id")
    private String buyerId;
    /**
     * 买方公司名称
     */
    @ApiModelProperty(value="买方公司名称")
    private String buyerName;
    /**
     * 产品id
     */
    @ApiModelProperty(value="产品id")
    private String productId;
    /**
     * 产品名称
     */
    @ApiModelProperty(value="产品名称")
    private String productName;
    /**
     * 产品批次数量
     */
    @ApiModelProperty(value="产品批次数量")
    private String productAmount;
    /**
     * 产品单价
     */
    @ApiModelProperty(value="产品单价")
    private String productPrice;
    /**
     * 产品总价
     */
    @ApiModelProperty(value="产品总价")
    private String productTotal;
    /**
     * 0-庄园经销商开单 1-仓库开单
     */
    @ApiModelProperty(value="0-庄园经销商开单 1-仓库开单")
    private String orderType;
	/**
	 * 订单创建者
	 */
	@ApiModelProperty(value="订单创建者")
	private String CreatorId;

	private String CreatorName;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime CreateTime;

    private String previousId;

    private String invFlag;

    private String reviewerId;

	private String reviewerName;

    private String reviewerSuggestions;

	/**
	 * 是否审批  -0：未送审  1：已送审 未审批 2：已审批 3：为通过
	 */
    private String checkFlag;
	/**
	 * 是否删除  -1：已删除  0：正常
	 */
	@TableLogic
	private String delFlag;
    }
