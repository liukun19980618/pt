package com.pt.ptdealerproc.service;

import com.pt.ptdealerproc.entity.ProcProcessNode;

import java.util.List;

public interface ProcProcessNodeService{

    /**
     * 批量新增流程节点信息
     *
     * @param processNodeList 流程节点列表
     * @return 结果
     */
    Boolean batchProcessNode(List<ProcProcessNode> processNodeList);
}
