package com.pt.ptmanor.pojo.painting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "work_type")
public class WorkType {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Id
    @Column(name = "id")
    private String id;

    @Column
    private String name;

    @Column
    private Integer isDeleted;

    public String getNeedMaterial() {
        return needMaterial;
    }

    public void setNeedMaterial(String needMaterial) {
        this.needMaterial = needMaterial;
    }

    @Column
    private String needMaterial;



}
