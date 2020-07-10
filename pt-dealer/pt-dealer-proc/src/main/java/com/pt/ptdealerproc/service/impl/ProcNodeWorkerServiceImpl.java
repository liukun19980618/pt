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


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pt.ptdealerproc.entity.ProcNodeWorker;
import com.pt.ptdealerproc.entity.ProcProcessNode;
import com.pt.ptdealerproc.mapper.ProcNodeWorkerMapper;
import com.pt.ptdealerproc.mapper.ProcProcessNodeMapper;
import com.pt.ptdealerproc.service.ProcNodeWorkerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 加工节点表
 *
 * @author pig code generator
 * @date 2020-04-19 10:46:44
 */
@Service
public class ProcNodeWorkerServiceImpl implements ProcNodeWorkerService {
    @Resource
    private ProcNodeWorkerMapper procNodeWorkerMapper;
    /**
     * 批量新增流程节点信息
     *
     * @param nodeWorkerList 流程节点列表
     * @return 结果
     */
    @Override
    public Boolean batchNodeWorker(List<ProcNodeWorker> nodeWorkerList){
        return procNodeWorkerMapper.batchNodeWorker(nodeWorkerList);
    };
}
