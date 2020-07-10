package com.pt.ptmanor.mapper.product;


import com.pt.ptmanor.pojo.product.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice,String>, JpaSpecificationExecutor<Invoice> {


}
