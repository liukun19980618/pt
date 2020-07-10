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

package com.pt.ptdealerproc.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pt.ptcommoncore.util.R;
import com.pt.ptcommonsecurity.util.SecurityUtils;
import com.pt.ptdealerproc.entity.ProcNode;
import com.pt.ptdealerproc.service.ProcNodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 加工节点表
 *
 * @author pig code generator
 * @date 2020-04-19 10:46:44
 */
@RestController
@AllArgsConstructor
@RequestMapping("/node" )
@Api(value = "node", tags = "加工节点表管理")
public class ProcNodeController {

    private final ProcNodeService procNodeService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param procNode 加工节点表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getProcNodePage(Page page, ProcNode procNode) {
        return R.ok(procNodeService.getProcNodePage(page, procNode));
    }

    /**
     * 获取节点列表
     * @return
     */
    @GetMapping("/list" )
    public R getProcNodeList() {
        return R.ok(procNodeService.getProcNodeList());
    }

    /**
     * 根据节点编号获取详细信息
     */
    @GetMapping(value = "/{nodeId}")
    public R getInfo(@PathVariable String nodeId)
    {
        return R.ok(procNodeService.selectNodeById(nodeId));
    }

    /**
     * 新增节点
     */

    @PostMapping
    public R add( @RequestBody ProcNode node)
    {
        if (!procNodeService.checkNodeNameUnique(node))
        {
            return R.failed("新增节点'" + node.getNodeName() + "'失败，节点名称已存在");
        }
        else if (!procNodeService.checkNodeCodeUnique(node))
        {
            return R.failed("新增节点'" + node.getNodeName() + "'失败，节点编码已存在");
        }
        node.setCreateBy(SecurityUtils.getNickName());
        return R.ok(procNodeService.insertNode(node));
    }

    /**
     * 修改节点
     */

    @PutMapping
    public R edit(@Validated @RequestBody ProcNode node)
    {
        if (!procNodeService.checkNodeNameUnique(node))
        {
            return R.failed("修改节点'" + node.getNodeName() + "'失败，节点名称已存在");
        }
        else if (!procNodeService.checkNodeCodeUnique(node))
        {
            return R.failed("修改节点'" + node.getNodeName() + "'失败，节点编码已存在");
        }
        return R.ok(procNodeService.updateNode(node));
    }

    /**
     * 删除节点
     */

    @DeleteMapping("/{nodeIds}")
    public R remove(@PathVariable String[] nodeIds)
    {
        return R.ok(procNodeService.deleteNodeByIds(nodeIds));
    }

    /**
     * 获取节点选择框列表
     */
    @GetMapping("/optionselect")
    public R optionselect()
    {
        List<ProcNode> nodes = procNodeService.selectNodeAll();
        return R.ok(nodes);
    }
}
