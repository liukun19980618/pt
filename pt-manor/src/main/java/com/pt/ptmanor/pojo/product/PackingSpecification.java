package com.pt.ptmanor.pojo.product;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "packing_specification")
public class PackingSpecification {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecificationSeries() {
        return specificationSeries;
    }

    public void setSpecificationSeries(String specificationSeries) {
        this.specificationSeries = specificationSeries;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Id
    @Column(name = "id")

    private String id;

    @Column(name = "specification_series")
    private String specificationSeries;

    @Column(name = "specification_name")
    private String specificationName;

    @Column(name = "enterprise")
    private String enterprise;

    @Column(name = "is_deleted")
    private int isDeleted;
}
