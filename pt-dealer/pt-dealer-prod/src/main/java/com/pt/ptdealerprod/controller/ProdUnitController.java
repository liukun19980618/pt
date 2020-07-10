package com.pt.ptdealerprod.controller;/*
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


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pt.ptcommoncore.util.R;
import com.pt.ptdealerprod.entity.ProdUnit;
import com.pt.ptdealerprod.service.ProdUnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 单位表
 *
 * @author pig code generator
 * @date 2020-03-31 20:06:19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/unit" )
@Api(value = "unit", tags = "单位表管理")
public class ProdUnitController {

    private ProdUnitService prodUnitService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param prodUnit 单位表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getProdUnitPage(Page page, ProdUnit prodUnit) {
        return R.ok(prodUnitService.page(page, Wrappers.query(prodUnit)));
    }


    /**
     * 通过id查询单位表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(prodUnitService.getById(id));
    }

    /**
     * 新增单位表
     * @param prodUnit 单位表
     * @return R
     */
    @ApiOperation(value = "新增单位表", notes = "新增单位表")
    @PostMapping
    public R save(@RequestBody ProdUnit prodUnit) {
        return R.ok(prodUnitService.saveUnit(prodUnit));
    }

    /**
     * 修改单位表
     * @param prodUnit 单位表
     * @return R
     */
    @ApiOperation(value = "修改单位表", notes = "修改单位表")
    @PutMapping
    public R updateById(@RequestBody ProdUnit prodUnit) {
        return R.ok(prodUnitService.updateById(prodUnit));
    }

    /**
     * 通过id删除单位表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除单位表", notes = "通过id删除单位表")
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(prodUnitService.removeById(id));
    }

}
