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

package com.pt.ptdealerorder.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pt.ptcommon.util.R;
import com.pt.ptdealerorder.entity.Query;
import com.pt.ptdealerorder.entity.SysOrder;
import com.pt.ptdealerorder.service.SysOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 *
 *
 * @author pig code generator
 * @date 2020-04-29 19:37:32
 */
@RestController
@AllArgsConstructor
@RequestMapping("/order" )
@Api(value = "order", tags = "管理")
public class SysOrderController {

    private final SysOrderService sysOrderService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param query
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getSysOrderPage(Page page, Query query) {
        return R.ok(sysOrderService.getSysOrderPage(page, query));
    }


    /**
     * 通过id查询
     * @param orderId id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{orderId}" )
    public R getById(@PathVariable("orderId" ) String orderId) {
        return R.ok(sysOrderService.getById(orderId));
    }

    /**
     * 新增
     * @param sysOrder
     * @return R
     */
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping
    public R save(@RequestBody SysOrder sysOrder) {
        return R.ok(sysOrderService.saveOrder(sysOrder));
    }

    /**
     * 修改
     * @param sysOrder
     * @return R
     */
    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping
    public R updateById(@RequestBody SysOrder sysOrder) {
        return R.ok(sysOrderService.updateOrder(sysOrder));
    }

    /**
     * 通过id删除
     * @param orderId id
     * @return R
     */
    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @DeleteMapping("/{orderId}" )
    public R removeById(@PathVariable String orderId) {
        return R.ok(sysOrderService.removeById(orderId));
    }

	@PostMapping("/check" )
    public R changeCheckFlag(@RequestParam Integer orderId,@RequestParam String checkFlag){
    	return R.ok(sysOrderService.changeCheckFlag(orderId,checkFlag));
	}
}
