package com.pt.ptdealerproc.service.impl;

import com.pt.ptdealerproc.entity.ProcProcessNode;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pt.ptdealerproc.mapper.ProcProcessNodeMapper;
import com.pt.ptdealerproc.service.ProcProcessNodeService;

import java.util.List;

@Service
public class ProcProcessNodeServiceImpl implements ProcProcessNodeService{

    @Resource
    private ProcProcessNodeMapper procProcessNodeMapper;
    /**
     * 批量新增流程节点信息
     *
     * @param processNodeList 流程节点列表
     * @return 结果
     */
    @Override
    public Boolean batchProcessNode(List<ProcProcessNode> processNodeList){
        return procProcessNodeMapper.batchProcessNode(processNodeList);
    };
}
