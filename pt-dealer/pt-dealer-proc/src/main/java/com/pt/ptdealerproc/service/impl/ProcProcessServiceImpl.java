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
package com.pt.ptdealerproc.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pt.ptcommoncore.constant.CommonConstants;
import com.pt.ptcommoncore.util.IdUtils;
import com.pt.ptdealerproc.dto.NodeDto;
import com.pt.ptdealerproc.dto.ProcessDto;
import com.pt.ptdealerproc.entity.ProcNodeWorker;
import com.pt.ptdealerproc.entity.ProcProcess;
import com.pt.ptdealerproc.entity.ProcProcess;
import com.pt.ptdealerproc.entity.ProcProcessNode;
import com.pt.ptdealerproc.mapper.ProcProcessMapper;
import com.pt.ptdealerproc.service.ProcNodeWorkerService;
import com.pt.ptdealerproc.service.ProcProcessNodeService;
import com.pt.ptdealerproc.service.ProcProcessService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 加工流程表
 *
 * @author pig code generator
 * @date 2020-04-19 14:58:16
 */
@Service
@AllArgsConstructor
public class ProcProcessServiceImpl extends ServiceImpl<ProcProcessMapper, ProcProcess> implements ProcProcessService {

	private final ProcProcessNodeService procProcessNodeService;
	private final ProcNodeWorkerService procNodeWorkerService;
	/**
	 * 获取加工流程表
	 * @param page
	 * @param processDto
	 * @return
	 */
	@Override
	public IPage getProcessPage(Page page, ProcessDto processDto) {
		return baseMapper.getProcessDtoPage(page,processDto);
	}

	/**
	 * 查询流程信息集合
	 *
	 * @param procProcess 流程信息
	 * @return 流程信息集合
	 */
	@Override
	public List<ProcProcess> selectProcessList(ProcProcess procProcess)
	{
		return baseMapper.selectProcessList(procProcess);
	}



	/**
	 * 查询所有流程
	 *
	 * @return 流程列表
	 */
	@Override
	public List<ProcProcess> selectProcessAll()
	{
		return baseMapper.selectProcessAll();
	}

	/**
	 * 通过流程ID查询流程信息
	 *
	 * @param processId 流程ID
	 * @return 角色对象信息
	 */
	@Override
	public ProcProcess selectProcessById(String processId)
	{
		return baseMapper.selectProcessById(processId);
	}


	/**
	 * 校验流程名称是否唯一
	 *
	 * @param process 流程信息
	 * @return 结果
	 */
	@Override
	public Boolean checkProcessNameUnique(ProcProcess process)
	{
//		if(StrUtil.isEmpty(process.getProcessId())){
//			return Boolean.TRUE;
//		}
		ProcProcess procProcess = baseMapper.checkProcessNameUnique(process.getProcessName());

		if (procProcess != null && !procProcess.getProcessId().equals(process.getProcessId()))
		{
			return Boolean.FALSE;
		}
		return Boolean.TRUE;

	}

	/**
	 * 校验流程编码是否唯一
	 *
	 * @param process 流程信息
	 * @return 结果
	 */
	@Override
	public Boolean checkProcessCodeUnique(ProcProcess process)
	{
//		if(StrUtil.isEmpty(process.getProcessId())){
//			return Boolean.TRUE;
//		}
		ProcProcess procProcess = baseMapper.checkProcessCodeUnique(process.getProcessName());

		if (procProcess != null && !procProcess.getProcessId().equals(process.getProcessId()))
		{
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * 通过流程ID查询流程使用数量
	 *
	 * @param processId 流程ID
	 * @return 结果
	 */
	@Override
	public int countProcProcessById(String processId)
	{
//		return sysUserProcessService.countProcProcessById(processId);
		return 0;
	}

	/**
	 * 删除流程信息
	 *
	 * @param processId 流程ID
	 * @return 结果
	 */
	@Override
	public Boolean deleteProcessById(String processId)
	{
		return baseMapper.deleteProcessById(processId);
	}

	/**
	 * 批量删除流程信息
	 *
	 * @param processIds 需要删除的流程ID
	 * @return 结果
	 * @throws Exception 异常
	 */
	@Override
	public Boolean deleteProcessByIds(String[] processIds)
	{
		for (String processId : processIds)
		{
			if (countProcProcessById(processId) > 0)
			{
				return  Boolean.FALSE;
			}
		}
		return baseMapper.deleteProcessByIds(processIds);
	}

	/**
	 * 新增保存流程信息
	 *
	 * @param processDto 流程信息
	 * @return 结果
	 */
	@Override
	public Boolean insertProcess(ProcessDto processDto)
	{
		processDto.setProcessId(IdUtils.simpleUUID());
		baseMapper.insertProcess(processDto);
		List<ProcProcessNode> processNodes = processDto.getProcessNodes();
		processNodes.stream().forEach(procProcessNode -> procProcessNode.setProcessId(processDto.getProcessId()));
		procProcessNodeService.batchProcessNode(processNodes);
		return Boolean.TRUE;
	}

	/**
	 * 修改保存流程信息
	 *
	 * @param process 流程信息
	 * @return 结果
	 */
	@Override
	public Boolean updateProcess(ProcProcess process)
	{
		return baseMapper.updateProcess(process);
	}

	@Override
	public List<ProcProcess> getProcProcessList() {
		return baseMapper.getProcProcessList();
	}

}
