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

package com.pt.ptdealerproc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pt.ptdealerproc.dto.MissionDto;
import com.pt.ptdealerproc.dto.ProcessDto;
import com.pt.ptdealerproc.entity.ProcProcess;
import com.pt.ptdealerproc.entity.ProcProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 加工流程表
 *
 * @author pig code generator
 * @date 2020-04-19 14:58:16
 */
@Mapper
public interface ProcProcessMapper extends BaseMapper<ProcProcess> {
	IPage<List<ProcessDto>> getProcessDtoPage(Page page, @Param("query") ProcessDto processDto);
	

	/**
	 * 查询流程数据集合
	 *
	 * @param process 流程信息
	 * @return 流程数据集合
	 */
	List<ProcProcess> selectProcessList(ProcProcess process);

	/**
	 * 查询所有流程
	 *
	 * @return 流程列表
	 */
	List<ProcProcess> selectProcessAll();

	/**
	 * 通过流程ID查询流程信息
	 *
	 * @param processId 流程ID
	 * @return 角色对象信息
	 */
	ProcProcess selectProcessById(String processId);

	/**
	 * 删除流程信息
	 *
	 * @param processId 流程ID
	 * @return 结果
	 */
	Boolean deleteProcessById(String processId);

	/**
	 * 批量删除流程信息
	 *
	 * @param processIds 需要删除的流程ID
	 * @return 结果
	 */
	Boolean deleteProcessByIds(String[] processIds);

	/**
	 * 修改流程信息
	 *
	 * @param process 流程信息
	 * @return 结果
	 */
	Boolean updateProcess(ProcProcess process);

	/**
	 * 新增流程信息
	 *
	 * @param process 流程信息
	 * @return 结果
	 */
	Boolean insertProcess(ProcProcess process);

	/**
	 * 校验流程名称
	 *
	 * @param processName 流程名称
	 * @return 结果
	 */
	ProcProcess checkProcessNameUnique(String processName);

	/**
	 * 校验流程编码
	 *
	 * @param processCode 流程编码
	 * @return 结果
	 */
	ProcProcess checkProcessCodeUnique(String processCode);

	/**
	 * 获取节点列表
	 * @return
	 */
	List<ProcProcess> getProcProcessList();
}
