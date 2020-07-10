package com.pt.ptportal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BlogComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    //评论者id
    private String userId;
    private String userName;
    //评论（回复）内容
    private String content;
    //当前回复的根id（帖子的id）
    private int blogId;

    //父id（对应评论的回复的id）
    private String parentId;
    private String parentName;
    //创建时间
    //创建时间
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",insertable = false,updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp createTime;
    //状态(1表示有效，0表示失效)
    @Column(nullable = false)
    private Boolean state=true;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Timestamp getCreateTime() {
        return createTime;
    }

}
