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
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Query  {
private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id",type = IdType.INPUT)
    @ApiModelProperty(value="订单id")
    private String orderId;
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

    @ApiModelProperty(value="0-庄园经销商开单 1-仓库开单")
    private String orderType;
	/**
	 * 订单创建者
	 */
	@ApiModelProperty(value="订单创建者")
	private String CreatorId;

	private String CreatorName;

    private String invFlag;

	private String checkFlag;

    private String timeSearchStart;

	private String timeSearchEnd;

	private String timeSearchType;

    }
