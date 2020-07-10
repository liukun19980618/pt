package com.pt.ptmanor.service.product;

import org.springframework.data.domain.Page;

import java.util.Date;

public interface InvoiceService {

    Page list(int pageNum, int pageRow);


    Page findByMany(Date stime, Date etime, Integer status, String buyerName, String productName, String orderId, Integer pageNum, Integer pageRow);

    Page checkList(int pageNum, int pageRow, String userName);

    Page myList(int pageNum, int pageRow, String userName);

    Page finalList(int pageNum, int pageRow);


    Page myInvoiceFindByMany(String userName, Date stime, Date etime, Integer checkFlag, String buyerName, String productName, String orderId, Integer pageNum, Integer pageRow);

    Page finalFindByMany(Date stime, Date etime, Integer checkFlag, String buyerName, String productName, String orderId, Integer pageNum, Integer pageRow);


    Page checkInvoiceFindByMany(String userName, Date stime, Date etime, String staff, String buyerName, String productName, String orderId, Integer pageNum, Integer pageRow);

}
