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

package com.pt.ptdealerproc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pt.ptdealerproc.entity.ProcNodeWorker;
import com.pt.ptdealerproc.entity.ProcProcessNode;

import java.util.List;

/**
 * 加工节点表
 *
 * @author pig code generator
 * @date 2020-04-19 10:46:44
 */
public interface ProcNodeWorkerService {
    /**
     * 批量新增流程节点信息
     *
     * @param procNodeWorkerList 流程节点列表
     * @return 结果
     */
    Boolean batchNodeWorker(List<ProcNodeWorker> procNodeWorkerList);
}
