package com.pt.ptmanor.pojo.painting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "material")
public class Material {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Id
    @Column(name = "id")
    private String id;

    @Column
    private String materialName;

    @Column
    private String materialInformation;

    @Column
    private String materialCompany;

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Column
    private Integer isDeleted;


}
