package com.pt.ptmanor.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "enterprises")
public class Enterprise {


    @Id
    @Column(name = "id")
    private String id;
//
    //企业名
    @Column
    private String enterpriseName;

//    //纳税人识别号
//    @Column
//    private String taxBunber;
//
//    @Column
//    private String address;
//
//    @Column
//    private String phoneNumber;
//
//    @Column
//    private String bank;
//
//    @Column
//    private String bankNumber;

    @Column
    private Integer isDeleted;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }




}
