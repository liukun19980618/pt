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
import com.pt.ptdealerproc.dto.ProcessDto;
import com.pt.ptdealerproc.entity.ProcNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 加工节点表
 *
 * @author pig code generator
 * @date 2020-04-19 10:46:44
 */
@Mapper
public interface ProcNodeMapper extends BaseMapper<ProcNode> {
    IPage<List<ProcNode>> getProcNodePage(Page page, @Param("query") ProcNode procNode);

    /**
     * 查询节点数据集合
     *
     * @param node 节点信息
     * @return 节点数据集合
     */
    List<ProcNode> selectNodeList(ProcNode node);

    /**
     * 查询所有节点
     *
     * @return 节点列表
     */
    List<ProcNode> selectNodeAll();

    /**
     * 通过节点ID查询节点信息
     *
     * @param nodeId 节点ID
     * @return 角色对象信息
     */
    ProcNode selectNodeById(String nodeId);

    /**
     * 删除节点信息
     *
     * @param nodeId 节点ID
     * @return 结果
     */
    Boolean deleteNodeById(String nodeId);

    /**
     * 批量删除节点信息
     *
     * @param nodeIds 需要删除的节点ID
     * @return 结果
     */
    Boolean deleteNodeByIds(String[] nodeIds);

    /**
     * 修改节点信息
     *
     * @param node 节点信息
     * @return 结果
     */
    Boolean updateNode(ProcNode node);

    /**
     * 新增节点信息
     *
     * @param node 节点信息
     * @return 结果
     */
    Boolean insertNode(ProcNode node);

    /**
     * 校验节点名称
     *
     * @param nodeName 节点名称
     * @return 结果
     */
    ProcNode checkNodeNameUnique(String nodeName);

    /**
     * 校验节点编码
     *
     * @param nodeCode 节点编码
     * @return 结果
     */
    ProcNode checkNodeCodeUnique(String nodeCode);

    /**
     * 获取节点列表
     * @return
     */
    List<ProcNode> getProcNodeList();
}
