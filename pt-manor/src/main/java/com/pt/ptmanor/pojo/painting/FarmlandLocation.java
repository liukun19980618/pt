package com.pt.ptmanor.pojo.painting;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "farmlandLocation")
public class FarmlandLocation {


    @Id
    @Column(name = "farmland_id")
    private String farmlandLocationId;


    @Column
    private String farmlandLocationName;


    //负责人
    @Column(name = "farmland_principal")
    private String farmlandPrincipal;


    public Integer getFarmlandArea() {
        return farmlandArea;
    }

    public void setFarmlandArea(Integer farmlandArea) {
        this.farmlandArea = farmlandArea;
    }

    //田地的面积
    @Column(name = "farmland_area")
    private Integer farmlandArea;


    //说明情况
    @Column(name = "farmland_explain")
    private String farmlandExplain;

    public Integer getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Integer precipitation) {
        this.precipitation = precipitation;
    }

    //年降水量
    @Column(name = "precipitation")
    private Integer precipitation;



    //删除
    @Column(name = "is_deleted")
    private  int isDeleted;

//precipitation

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public List<FarmlandRegion> getFarmlandRegions() {
        return farmlandRegions;
    }

    public void setFarmlandRegions(List<FarmlandRegion> farmlandRegions) {
        this.farmlandRegions = farmlandRegions;
    }

    //气候
    @Column(name = "climate")
    private  String climate;


    // 配置田地的区域与区块的关系为一对多
    @JsonIgnore//不加这条注解，会死循环报错
    @OneToMany(mappedBy = "farmlandLocation",cascade = CascadeType.ALL)//配置一对多关系
    private List<FarmlandRegion> farmlandRegions;





    public String getFarmlandLocationName() {
        return farmlandLocationName;
    }

    public void setFarmlandLocationName(String farmlandLocationName) {
        this.farmlandLocationName = farmlandLocationName;
    }


    public String getFarmlandPrincipal() {
        return farmlandPrincipal;
    }

    public void setFarmlandPrincipal(String farmlandPrincipal) {
        this.farmlandPrincipal = farmlandPrincipal;
    }



    public String getFarmlandExplain() {
        return farmlandExplain;
    }

    public void setFarmlandExplain(String farmlandExplain) {
        this.farmlandExplain = farmlandExplain;
    }


    public String getFarmlandLocationId() {
        return farmlandLocationId;
    }

    public void setFarmlandLocationId(String farmlandLocationId) {
        this.farmlandLocationId = farmlandLocationId;

    }


    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

}
