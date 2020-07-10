package com.pt.ptuser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
    * 字典数据表
    */
@Data
public class SysDictData  {

    /**
    * 字典编码
    */
//    @ApiModelProperty(value="字典编码")
    private Long dictCode;

    /**
    * 字典排序
    */
//    @ApiModelProperty(value="字典排序")
    private Integer dictSort;

    /**
    * 字典标签
    */
//    @ApiModelProperty(value="字典标签")
    private String dictLabel;

    /**
    * 字典键值
    */
//    @ApiModelProperty(value="字典键值")
    private String dictValue;

    /**
    * 字典类型
    */
//    @ApiModelProperty(value="字典类型")
    private String dictType;

    /**
    * 样式属性（其他样式扩展）
    */
//    @ApiModelProperty(value="样式属性（其他样式扩展）")
    private String cssClass;

    /**
    * 表格回显样式
    */
//    @ApiModelProperty(value="表格回显样式")
    private String listClass;

    /**
    * 是否默认（Y是 N否）
    */
//    @ApiModelProperty(value="是否默认（Y是 N否）")
    private String isDefault;

    /**
    * 状态（0正常 1停用）
    */
//    @ApiModelProperty(value="状态（0正常 1停用）")
    private String status;

    /**
    * 创建者
    */
//    @ApiModelProperty(value="创建者")
    private String createBy;

    /**
    * 创建时间
    */
//    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
    * 更新者
    */
//    @ApiModelProperty(value="更新者")
    private String updateBy;

    /**
    * 更新时间
    */
//    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    /**
    * 备注
    */
//    @ApiModelProperty(value="备注")
    private String remark;

}