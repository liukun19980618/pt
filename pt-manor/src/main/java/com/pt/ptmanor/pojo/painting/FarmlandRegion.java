package com.pt.ptmanor.pojo.painting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pt.ptmanor.pojo.Trace;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "farmlandRegion")
public class FarmlandRegion {

    public String getFarmlandRegionId() {
        return farmlandRegionId;
    }

    public void setFarmlandRegionId(String farmlandRegionId) {
        this.farmlandRegionId = farmlandRegionId;
    }

    public String getFarmlandRegionPrincipal() {
        return farmlandRegionPrincipal;
    }

    public void setFarmlandRegionPrincipal(String farmlandRegionPrincipal) {
        this.farmlandRegionPrincipal = farmlandRegionPrincipal;
    }

    public Float getFarmlandRegionArea() {
        return farmlandRegionArea;
    }

    public void setFarmlandRegionArea(Float farmlandRegionArea) {
        this.farmlandRegionArea = farmlandRegionArea;
    }

    public String getFarmlandRegionExplain() {
        return farmlandRegionExplain;
    }

    public void setFarmlandRegionExplain(String farmlandRegionExplain) {
        this.farmlandRegionExplain = farmlandRegionExplain;
    }

    public String getFarmlandRegionType() {
        return farmlandRegionType;
    }

    public void setFarmlandRegionType(String farmlandRegionType) {
        this.farmlandRegionType = farmlandRegionType;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Float getBulkDensity() {
        return bulkDensity;
    }

    public void setBulkDensity(Float bulkDensity) {
        this.bulkDensity = bulkDensity;
    }

    public Float getPorosity() {
        return porosity;
    }

    public void setPorosity(Float porosity) {
        this.porosity = porosity;
    }

    public Float getPh() {
        return ph;
    }

    public void setPh(Float ph) {
        this.ph = ph;
    }

    public Float getSalt() {
        return salt;
    }

    public void setSalt(Float salt) {
        this.salt = salt;
    }

    public Float getOrganic() {
        return organic;
    }

    public void setOrganic(Float organic) {
        this.organic = organic;
    }

    public Float getN() {
        return N;
    }

    public void setN(Float n) {
        N = n;
    }

    public Float getP() {
        return P;
    }

    public void setP(Float p) {
        P = p;
    }

    public Float getK() {
        return K;
    }

    public void setK(Float k) {
        K = k;
    }

    public Float getSoilPermeabilityCoefficient() {
        return soilPermeabilityCoefficient;
    }

    public void setSoilPermeabilityCoefficient(Float soilPermeabilityCoefficient) {
        this.soilPermeabilityCoefficient = soilPermeabilityCoefficient;
    }

    public FarmlandLocation getFarmlandLocation() {
        return farmlandLocation;
    }

    public void setFarmlandLocation(FarmlandLocation farmlandLocation) {
        this.farmlandLocation = farmlandLocation;
    }

    public String getFarmlandLocationName() {
        return farmlandLocationName;
    }

    public void setFarmlandLocationName(String farmlandLocationName) {
        this.farmlandLocationName = farmlandLocationName;
    }

//    public List<Work> getWorkList() {
//        return workList;
//    }
//
//    public void setWorkList(List<Work> workList) {
//        this.workList = workList;
//    }

    public List<Trace> getTraceList() {
        return traceList;
    }

    public void setTraceList(List<Trace> traceList) {
        this.traceList = traceList;
    }



    //田地区块编号，也是主键
    @Id
    @Column(name = "farmlandRegion_id")
    private String farmlandRegionId;

    //田地区块的负责人
    @Column(name = "farmlandRegion_principal")
    private String farmlandRegionPrincipal;


    //田地区块的面积
    @Column(name = "farmlandRegion_area")
    private Float farmlandRegionArea;
    //说明情况
    @Column(name = "farmlandRegion_explain")
    private String farmlandRegionExplain;
    //田地的类型，如大棚，露地
    @Column(name = "farmlandRegion_type")
    private String farmlandRegionType;

    //田地的作物种类
    @Column(name = "crop_type")
    private String cropType;

    @Column(name = "is_deleted")
    private  int isDeleted;

    //容重
    @Column(name = "bulk_density")
    private Float bulkDensity;

    //总孔隙度
    @Column(name = "porosity")
    private Float porosity;

    @Column(name = "ph")
    private  Float ph;

    @Column(name = "salt")
    private  Float salt;

    @Column(name = "organic")
    private  Float organic;

    @Column(name = "n")
    private Float N;

    @Column(name = "p")
    private Float P;

    @Column(name = "k")
    private  Float K;

    //土壤渗透系数
    @Column(name = "soil_permeability_coefficient")
    private Float soilPermeabilityCoefficient;


    @ManyToOne(cascade =CascadeType.ALL,optional = false)
    @JoinColumn(name = "farmland_id")
    private FarmlandLocation farmlandLocation;

    @Column
    private String farmlandLocationName;


//    //配置与工作流程的一对多
//    @JsonIgnore
//    @OneToMany(mappedBy = "farmlandRegion",cascade = CascadeType.ALL)
//    private List<Work> workList;


    //溯源管理
    @JsonIgnore
    @OneToMany(mappedBy = "farmlandRegion",cascade = CascadeType.ALL)
    private List<Trace> traceList;

//
//    //发票溯源
//    @JsonIgnore
//    @OneToMany(mappedBy = "farmlandRegion",cascade = CascadeType.ALL)
//    private List<Invoice> invoiceList;




}
