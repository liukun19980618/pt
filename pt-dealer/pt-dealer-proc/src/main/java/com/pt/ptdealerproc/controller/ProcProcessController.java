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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pt.ptcommoncore.util.R;
import com.pt.ptcommonsecurity.util.SecurityUtils;
import com.pt.ptdealerproc.dto.MissionDto;
import com.pt.ptdealerproc.dto.ProcessDto;
import com.pt.ptdealerproc.entity.ProcProcess;
import com.pt.ptdealerproc.service.ProcProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 加工流程表
 *
 * @author pig code generator
 * @date 2020-04-19 14:58:16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/process" )
@Api(value = "process", tags = "加工流程表管理")
public class ProcProcessController {

    private final ProcProcessService procProcessService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param processDto 加工流程表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getProcProcessPage(Page page, ProcessDto processDto) {
        return R.ok(procProcessService.getProcessPage(page,processDto));
    }
    
    /**
     * 获取节点列表
     * @return
     */
    @GetMapping("/list" )
    public R getProcProcessList() {
        return R.ok(procProcessService.getProcProcessList());
    }

    /**
     * 根据节点编号获取详细信息
     */
    @GetMapping(value = "/{processId}")
    public R getInfo(@PathVariable String processId)
    {
        return R.ok(procProcessService.selectProcessById(processId));
    }

    /**
     * 新增节点
     */

    @PostMapping
    public R add( @RequestBody ProcessDto processDto)
    {
        if (!procProcessService.checkProcessNameUnique(processDto))
        {
            return R.failed("新增节点'" + processDto.getProcessName() + "'失败，节点名称已存在");
        }
        else if (!procProcessService.checkProcessCodeUnique(processDto))
        {
            return R.failed("新增节点'" + processDto.getProcessName() + "'失败，节点编码已存在");
        }
        processDto.setCreateBy(SecurityUtils.getNickName());
        return R.ok(procProcessService.insertProcess(processDto));
    }

    /**
     * 修改节点
     */

    @PutMapping
    public R edit(@Validated @RequestBody ProcProcess process)
    {
        if (!procProcessService.checkProcessNameUnique(process))
        {
            return R.failed("修改节点'" + process.getProcessName() + "'失败，节点名称已存在");
        }
        else if (!procProcessService.checkProcessCodeUnique(process))
        {
            return R.failed("修改节点'" + process.getProcessName() + "'失败，节点编码已存在");
        }
        return R.ok(procProcessService.updateProcess(process));
    }

    /**
     * 删除节点
     */

    @DeleteMapping("/{processIds}")
    public R remove(@PathVariable String[] processIds)
    {
        return R.ok(procProcessService.deleteProcessByIds(processIds));
    }

    /**
     * 获取节点选择框列表
     */
    @GetMapping("/optionselect")
    public R optionselect()
    {
        List<ProcProcess> processs = procProcessService.selectProcessAll();
        return R.ok(processs);
    }

}
