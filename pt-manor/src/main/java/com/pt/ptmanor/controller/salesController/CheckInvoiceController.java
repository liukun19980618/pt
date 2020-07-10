package com.pt.ptmanor.controller.salesController;



import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.mapper.FinancialFormRepository;
import com.pt.ptmanor.mapper.SaleAmountRepository;
import com.pt.ptmanor.mapper.painting.FarmlandRegionRepository;
import com.pt.ptmanor.mapper.painting.ProductionRepository;
import com.pt.ptmanor.mapper.product.InvoiceRepository;
import com.pt.ptmanor.pojo.FinancialForm;
import com.pt.ptmanor.pojo.SaleAmount;
import com.pt.ptmanor.pojo.product.Invoice;
import com.pt.ptmanor.pojo.user.SysUser;
import com.pt.ptmanor.ptcommon.security.CustomUser;
import com.pt.ptmanor.ptcommon.util.SecurityUtils;
import com.pt.ptmanor.service.painting.FarmlandRegionService;
import com.pt.ptmanor.service.product.InvoiceService;
import com.pt.ptmanor.service.user.UserService;
import com.pt.ptmanor.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping("/checkInvoice")
@RestController
public class CheckInvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    FinancialFormRepository financialFormRepository;

    @Autowired
    ProductionRepository productionRepository;

    @Autowired
    FarmlandRegionRepository farmlandRegionRepository;

    @Autowired
    FarmlandRegionService farmlandRegionService;

    @Autowired
    SaleAmountRepository saleAmountRepository;

    @RequestMapping("/toCheckList")
    public YunResult toCheckList(int pageNum , int pageRow ){


        CustomUser user = SecurityUtils.getUser();
        String userName = user.getUserName();

        Page page = invoiceService.checkList(pageNum, pageRow,userName);
        return YunResult.createBySuccess(page);
    }


    @Autowired
    UserService userService;

    @RequestMapping(value = "getFinancialUserList",method = RequestMethod.GET)
    public YunResult getCheckUsers(){

        List<SysUser> SysUser = userService.getFinancialUserList();
        return YunResult.createBySuccess(SysUser);
    }


    //打回
    @RequestMapping("/checkBack")
    public YunResult checkBack(@RequestBody Invoice invoice){
        String orderId = invoice.getOrderId();

        Optional<Invoice> byId1 = invoiceRepository.findById(orderId);
        Invoice invoice1 = byId1.get();
        invoice1.setCheckFlag(3);
        invoice1.setCheckTime(new Date());
        invoiceRepository.save(invoice1);
        return YunResult.createBySuccess("打回成功！");
    }


    @RequestMapping("/pass")
    public  YunResult pass(@RequestBody Invoice invoice){

        String orderId = invoice.getOrderId();


        Optional<Invoice> byId = invoiceRepository.findById(orderId);
        Invoice invoice1 = byId.get();
        invoice1.setCheckFlag(2);
        invoice1.setCheckTime(new Date());

        if (invoice1.getPreviousId()!=null){
            Optional<Invoice> byId1 = invoiceRepository.findById(invoice1.getPreviousId());
            Invoice invoice2 = byId1.get();
            invoice2.setCheckFlag(3);
            invoiceRepository.save(invoice2);

            if (invoice2.getOrderType()==0){
                Optional<SaleAmount> byId3 = saleAmountRepository.findById(invoice2.getOrderId());
                Optional<FinancialForm> byId4 = financialFormRepository.findById(invoice2.getOrderId());
                SaleAmount saleAmount = byId3.get();
                saleAmount.setIsDeleted(1);
                FinancialForm financialForm = byId4.get();
                financialForm.setIsDeleted(1);
                saleAmountRepository.save(saleAmount);
                financialFormRepository.save(financialForm);
            }

        }

        if (invoice1.getOrderType()==0){

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date date = new Date();
            String s1 = sdf.format(date);

            SaleAmount saleAmount = new SaleAmount();
            saleAmount.setId(invoice1.getOrderId());
            saleAmount.setIsDeleted(0);
            saleAmount.setDate(new Date());
            saleAmount.setUpdateDate(new Date());
            saleAmount.setCrops(invoice1.getProductName());
            saleAmount.setAmount(invoice1.getProductAmount());
            saleAmount.setYear(s1);
            saleAmount.setBuyerName(invoice1.getBuyerName());

            saleAmountRepository.save(saleAmount);

            FinancialForm financialForm = new FinancialForm();
            financialForm.setIsDeleted(0);
            financialForm.setUpdateDate(new Date());
            financialForm.setDate(new Date());
            financialForm.setBuyerName(invoice1.getBuyerName());
            financialForm.setId(invoice1.getOrderId());
            financialForm.setMoney(invoice1.getProductTotal());
            financialForm.setProductName(invoice1.getProductName());
            financialForm.setYear(s1);
            financialFormRepository.save(financialForm);
        }

        invoiceRepository.save(invoice1);
        return  YunResult.createBySuccess("审核成功！",null);
    }


    @RequestMapping(value = "findByMany",method = RequestMethod.POST)
    public YunResult find(@RequestBody JSONObject jsonObject){

        String orderId = jsonObject.getString("orderId");
        Integer pageNum = jsonObject.getInteger("pageNum");
        Integer pageRow = jsonObject.getInteger("pageRow");

        String buyerName = jsonObject.getString("buyerName");
        String productName = jsonObject.getString("productName");
        String staff = jsonObject.getString("staff");


        String userName = SecurityUtils.getUser().getUserName();


        Date etime = jsonObject.getDate("etime");
        Date stime = jsonObject.getDate("stime");

        Page page = invoiceService.checkInvoiceFindByMany(userName,stime, etime, staff,buyerName, productName, orderId, pageNum, pageRow);
        return YunResult.createBySuccess(page);

    }

//    @Autowired
//    UserService userService;
//
//
//    @RequestMapping(value = "getFinancialUserList",method = RequestMethod.GET)
//    public YunResult getCheckUsers(){
//
//        List<YunUser> yunUsers = userService.financialUserList();
//        return YunResult.createBySuccess(yunUsers);
//    }







}
