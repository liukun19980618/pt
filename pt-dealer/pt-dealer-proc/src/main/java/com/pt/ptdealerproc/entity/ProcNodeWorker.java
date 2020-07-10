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

package com.pt.ptdealerproc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 节点-工人表
 *
 * @author pig code generator
 * @date 2020-04-19 10:46:44
 */
@Data
public class ProcNodeWorker{
private static final long serialVersionUID = 1L;

    /**
     * 加工节点编号
     */
    @ApiModelProperty(value="加工节点Id")
    private String nodeId;
    /**
     * 加工节点名称
     */
    @ApiModelProperty(value="工人Id")
    private String workerId;

    public ProcNodeWorker(String nodeId, String workerId) {
        this.nodeId = nodeId;
        this.workerId = workerId;
    }
}
