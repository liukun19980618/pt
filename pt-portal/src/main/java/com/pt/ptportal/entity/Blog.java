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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Blog {
    //帖子id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogId;
    //用户id
    private String userId;
    //用户的名称
    private String userName;
    //内容
    private String content;
    //创建时间
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",insertable = false,updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp createTime;
    //状态(1表示有效，0表示失效)
    private Boolean state=true;
    //帖子的点赞数
    private int likeNum;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Timestamp getCreateTime() {
        return createTime;
    }
}
