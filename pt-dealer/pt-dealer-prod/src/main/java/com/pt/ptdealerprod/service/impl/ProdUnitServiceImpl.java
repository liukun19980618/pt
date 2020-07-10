package com.pt.ptdealerprod.service.impl;/*
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


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pt.ptcommoncore.util.IdUtils;
import com.pt.ptdealerprod.entity.ProdUnit;
import com.pt.ptdealerprod.mapper.ProdUnitMapper;
import com.pt.ptdealerprod.service.ProdUnitService;
import org.springframework.stereotype.Service;

/**
 * 单位表
 *
 * @author pig code generator
 * @date 2020-03-31 20:06:19
 */
@Service
public class ProdUnitServiceImpl extends ServiceImpl<ProdUnitMapper, ProdUnit> implements ProdUnitService {
	@Override
	public Boolean saveUnit(ProdUnit prodUnit) {
		prodUnit.setUnitId(IdUtils.simpleUUID());
		baseMapper.insert(prodUnit);
		return Boolean.TRUE;
	}
}
