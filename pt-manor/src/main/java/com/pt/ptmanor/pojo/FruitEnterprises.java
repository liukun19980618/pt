package com.pt.ptmanor.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

@TableName("fruit_enterprises")
public class FruitEnterprises {

    @TableId(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;


    //企业名
    private String enterpriseName;

    private String taxBunber;

    private String address;

    private String phoneNumber;

    private String bank;

    private String bankNumber;

    private String isDeleted;

    private Date createTime;






//    企业名	buyerName
//    纳税人识别号 	buyerTaxBunber
//    地址	buyerAddress
//    电话	buyerPhone
//    开户行	buyerBank
//    账号	buyerBankNumber


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getTaxBunber() {
        return taxBunber;
    }

    public void setTaxBunber(String taxBunber) {
        this.taxBunber = taxBunber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}
