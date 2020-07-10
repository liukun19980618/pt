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

package com.pt.ptdealerproc.dto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 个人任务表
 *
 * @author pig code generator
 * @date 2020-04-19 10:46:44
 */
@Data
@ApiModel(value = "个人任务表")
public class MissionDto{
private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="工人Id")
	private Integer workerId;

    @ApiModelProperty(value="加工流程编号")
    private String processNumber;

	@ApiModelProperty(value="加工流程编号")
	private String processName;

    @ApiModelProperty(value="加工节点编号")
    private String nodeNumber;

	@ApiModelProperty(value="加工节点编号")
	private String nodeName;

	@ApiModelProperty(value="开始时间")
	private LocalDateTime startTime;

	@ApiModelProperty(value="结束时间")
	private LocalDateTime endTime;

}
