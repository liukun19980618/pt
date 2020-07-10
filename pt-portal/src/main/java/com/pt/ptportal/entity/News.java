package com.pt.ptportal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Table(name = "News")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String newTitle;
    //图片文件的URL地址
    private String profile;
   //发布时间
   @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",insertable = false,updatable = false)
   @Generated(GenerationTime.INSERT)
   private Timestamp createTime;

    //设置一个新闻的失效时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date failTime;
    //文章的摘要
    private String newsAbstract;
    //文章的原文
    private String newsContentMd;
    //形成的文章HTML
    private String newsHtml;
    //设置状态位0或者是1,0为不可见，1为可见（默认为1）
    private Integer status=1;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Timestamp getCreateTime() {
        return createTime;
    }
}
