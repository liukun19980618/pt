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
package com.pt.ptdealerorder.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pt.ptcommon.constant.CommonConstants;
import com.pt.ptcommon.util.IdUtils;
import com.pt.ptdealerorder.entity.Query;
import com.pt.ptdealerorder.entity.SysOrder;
import com.pt.ptdealerorder.mapper.SysOrderMapper;
import com.pt.ptdealerorder.service.SysOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 *
 *
 * @author pig code generator
 * @date 2020-04-29 19:37:32
 */
@Service
public class SysOrderServiceImpl extends ServiceImpl<SysOrderMapper, SysOrder> implements SysOrderService {
	@Override
	public IPage getSysOrderPage(Page page, Query query) {
		return baseMapper.getOrderPage(page,query);
	}

	@Override
	public Boolean saveOrder(SysOrder sysOrder) {
		sysOrder.setOrderId(IdUtils.simpleUUID());
		sysOrder.setCreateTime(LocalDateTime.now());
		sysOrder.setCheckFlag(CommonConstants.STATUS_NORMAL);
		sysOrder.setInvFlag(CommonConstants.STATUS_NORMAL);
		sysOrder.setDelFlag(CommonConstants.STATUS_NORMAL);
		sysOrder.setPreviousId(sysOrder.getOrderId());
		baseMapper.insert(sysOrder);
		return Boolean.TRUE;
	}

	@Override
	public Boolean updateOrder(SysOrder sysOrder) {
		this.updateById(sysOrder);
		return Boolean.TRUE;
	}

	@Override
	public Boolean changeCheckFlag(Integer orderId, String checkFlag) {
		SysOrder sysOrder = this.getById(orderId);
		sysOrder.setCheckFlag(checkFlag);
		this.updateOrder(sysOrder);
		return Boolean.TRUE;
	}
}
