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

package com.pt.ptdealerprod.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pt.ptcommoncore.util.R;
import com.pt.ptdealerprod.entity.ProdType;
import com.pt.ptdealerprod.service.ProdTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


/**
 * 类型表
 *
 * @author pig code generator
 * @date 2020-03-31 21:33:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/type" )
@Api(value = "type", tags = "类型表管理")
public class ProdTypeController {

    private ProdTypeService prodTypeService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param prodType 类型表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getProdTypePage(Page page, ProdType prodType) {
        return R.ok(prodTypeService.page(page, Wrappers.query(prodType)));
    }


    /**
     * 通过id查询类型表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(prodTypeService.getById(id));
    }

    /**
     * 新增类型表
     * @param prodType 类型表
     * @return R
     */
    @ApiOperation(value = "新增类型表", notes = "新增类型表")
    @PostMapping
    public R save(@RequestBody ProdType prodType) {
        return R.ok(prodTypeService.saveType(prodType));
    }

    /**
     * 修改类型表
     * @param prodType 类型表
     * @return R
     */
    @ApiOperation(value = "修改类型表", notes = "修改类型表")
    @PutMapping
    public R updateById(@RequestBody ProdType prodType) {
        return R.ok(prodTypeService.updateById(prodType));
    }

    /**
     * 通过id删除类型表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除类型表", notes = "通过id删除类型表")
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(prodTypeService.removeById(id));
    }

	/**
	 *
	 * @param
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/search/{name}" )
	public R getProdTypeList(@PathVariable String name) throws UnsupportedEncodingException {
		return R.ok(prodTypeService.getList(name));
	}


}
