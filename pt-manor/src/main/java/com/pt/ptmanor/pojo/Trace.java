package com.pt.ptmanor.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pt.ptmanor.pojo.painting.FarmlandRegion;
import com.pt.ptmanor.pojo.product.Invoice;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trace")
public class Trace {

    //主键
    @Id
    @Column(name = "id")
    private String id;


    @Column
    private String batch;


    @ManyToOne(cascade =CascadeType.ALL,optional = false)
    @JoinColumn(name = "farmlandRegion_id")
    private FarmlandRegion farmlandRegion;


    @Column
    private String findFarmlandRegionId;



    @Column(name = "is_deleted")
    private  int isDeleted;

    //日期
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;


    @Column
    private String invoiceNumber;


    @ManyToOne(cascade =CascadeType.ALL,optional = false)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;




    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getFindFarmlandRegionId() {
        return findFarmlandRegionId;
    }

    public void setFindFarmlandRegionId(String findFarmlandRegionId) {
        this.findFarmlandRegionId = findFarmlandRegionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public FarmlandRegion getFarmlandRegion() {
        return farmlandRegion;
    }

    public void setFarmlandRegion(FarmlandRegion farmlandRegion) {
        this.farmlandRegion = farmlandRegion;
    }





}
