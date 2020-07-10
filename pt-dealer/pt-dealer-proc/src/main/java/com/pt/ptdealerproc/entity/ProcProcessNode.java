package com.pt.ptdealerproc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author wl
 */
@Data
public class ProcProcessNode {
    private String processId;

    private String nodeId;

    private String workerId;

    private  Integer sort;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm")
    private Date endTime;

    public ProcProcessNode(String processId, String nodeId) {
        this.processId = processId;
        this.nodeId = nodeId;
    }
}