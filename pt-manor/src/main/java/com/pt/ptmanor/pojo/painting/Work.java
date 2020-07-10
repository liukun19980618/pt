package com.pt.ptmanor.pojo.painting;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "work")
public class Work {

    //日期
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;


    //作业内容
    @Column(name = "work")
    private  String work;

    //农作物
    @Column(name = "crops")
    private String crops;

    //图片
    @Column(name = "pricture")
    private String picture;

    //视频
    @Column(name = "video")
    private String video;

    //备注
    @Column(name = "remark")
    private String remark;

    @Column(name = "id")
    @Id
    @NotFound(action= NotFoundAction.IGNORE)
    private String id;

    //责任人
    @Column(name = "staff")
    private String staff;

    @Column
    private Integer amount;

    @Column(name = "is_deleted")
    private  int isDeleted;

    //批次
    @Column
    private String batch;

//
//    @ManyToOne(cascade =CascadeType.ALL,optional = false)
//    @JoinColumn(name = "farmlandRegion_id")
//    private FarmlandRegion farmlandRegion;


    @Column
    private String findFarmlandRegionId;


    @Column
    private String materialName;

    @Column
    private String materialInformation;

    @Column
    private String materialCompany;

    public Integer getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(Integer checkNumber) {
        this.checkNumber = checkNumber;
    }

    @Column
    private Integer checkNumber;

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    @Column
    private String checkUser;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateDate;

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialInformation() {
        return materialInformation;
    }

    public void setMaterialInformation(String materialInformation) {
        this.materialInformation = materialInformation;
    }

    public String getMaterialCompany() {
        return materialCompany;
    }

    public void setMaterialCompany(String materialCompany) {
        this.materialCompany = materialCompany;
    }








    public String getFindFarmlandRegionId() {
        return findFarmlandRegionId;
    }

    public void setFindFarmlandRegionId(String findFarmlandRegionId) {
        this.findFarmlandRegionId = findFarmlandRegionId;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

//    public FarmlandRegion getFarmlandRegion() {
//        return farmlandRegion;
//    }
//
//    public void setFarmlandRegion(FarmlandRegion farmlandRegion) {
//        this.farmlandRegion = farmlandRegion;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
//
//    public String getField() {
//        return field;
//    }
//
//    public void setField(String field) {
//        this.field = field;
//    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getCrops() {
        return crops;
    }

    public void setCrops(String crops) {
        this.crops = crops;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }


}
