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
package com.pt.ptdealerprod.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pt.ptcommoncore.util.IdUtils;
import com.pt.ptdealerprod.entity.ProdType;
import com.pt.ptdealerprod.mapper.ProdTypeMapper;
import com.pt.ptdealerprod.service.ProdTypeService;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * 类型表
 *
 * @author pig code generator
 * @date 2020-03-31 21:33:12
 */
@Service
public class ProdTypeServiceImpl extends ServiceImpl<ProdTypeMapper, ProdType> implements ProdTypeService {
	@Override
	public Boolean saveType(ProdType prodType) {
		prodType.setTypeId(IdUtils.simpleUUID());
		prodType.setTypeNumber(prodType.getTypeId().toString());//临时使用数据库主键代替编号
		baseMapper.insert(prodType);
		return Boolean.TRUE;
	}

	@Override
	public List<ProdType> getList(String name) throws UnsupportedEncodingException {
		String decodeName = URLDecoder.decode(name, "GBK");
		List<ProdType> prodTypeList = baseMapper.selectList(Wrappers.<ProdType>query().lambda().like(ProdType::getTypeName, decodeName));
		return prodTypeList;
	}
}
