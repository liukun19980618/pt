package com.pt.ptmanor.util;



import com.pt.ptmanor.pojo.painting.FarmlandRegion;
import com.pt.ptmanor.pojo.painting.Work;
import com.pt.ptmanor.pojo.product.Invoice;

import java.util.List;

public class TraceResult {

    public List<Work> getWorkList() {
        return workList;
    }

    public void setWorkList(List<Work> workList) {
        this.workList = workList;
    }

    private List<Work> workList;

    private Invoice invoice ;

    private FarmlandRegion farmlandRegion;

    public FarmlandRegion getFarmlandRegion() {
        return farmlandRegion;
    }

    public void setFarmlandRegion(FarmlandRegion farmlandRegion) {
        this.farmlandRegion = farmlandRegion;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
