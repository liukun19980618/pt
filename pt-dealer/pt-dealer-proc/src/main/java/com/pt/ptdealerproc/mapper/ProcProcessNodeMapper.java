package com.pt.ptdealerproc.mapper;

import com.pt.ptdealerproc.entity.ProcProcessNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProcProcessNodeMapper {

    Boolean batchProcessNode(List<ProcProcessNode> processNodeList);

    List<ProcProcessNode> listProcessNode(@Param("processId") String processId);
}