package com.pt.ptmanor.controller.salesController;

import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.mapper.EnterprisesRepository;
import com.pt.ptmanor.mapper.FinancialFormRepository;
import com.pt.ptmanor.mapper.SaleAmountRepository;
import com.pt.ptmanor.mapper.YunUserRepository;
import com.pt.ptmanor.mapper.painting.FarmlandRegionRepository;
import com.pt.ptmanor.mapper.painting.ProductionRepository;
import com.pt.ptmanor.mapper.product.InvoiceRepository;
import com.pt.ptmanor.mapper.user.UserRepository;
import com.pt.ptmanor.pojo.Enterprise;
import com.pt.ptmanor.pojo.YunUser;
import com.pt.ptmanor.pojo.product.Invoice;
import com.pt.ptmanor.pojo.user.SysUser;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/myInvoice")
@RestController
public class MyInvoiceController {

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

    @Autowired
    EnterprisesRepository enterprisesRepository;

    @Autowired
    UserRepository userRepository;

//    @RequestMapping("/list")
//    public YunResult getList(int pageNum , int pageRow){
//
//
//        CustomUser user = SecurityUtils.getUser();
//        String userName = user.getUserName();
//        System.out.println("================================"+userName);
//
//        Page page = invoiceService.myList(pageNum, pageRow,userName);
//        return YunResult.createBySuccess(page);
//    }


    @Autowired
    YunUserRepository yunUserRepository;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public YunResult add(@RequestBody Invoice invoice){


        UUID u = UUID.randomUUID();
        String str = u.toString();
        str = str.replace("-","");
       invoice.setOrderId(str);
       invoice.setIsDeleted(0);
       invoice.setStatus(0);
       invoice.setCheckFlag(0);
       invoice.setCreateTime(new Date());

        Optional<Enterprise> byId = enterprisesRepository.findById(invoice.getBuyerId());
        Enterprise enterprise = byId.get();
        invoice.setBuyerName(enterprise.getEnterpriseName());

        SysUser byUserName = userRepository.findByUserName(invoice.getOrderCreator());
        invoice.setCreatorName(byUserName.getNickName());

        SysUser byUserName1 = userRepository.findByUserName(invoice.getReviewerId());

        invoice.setReviewerName(byUserName1.getNickName());

        invoiceRepository.save(invoice);

        return  YunResult.createBySuccess("保存成功！");
    }


    @Autowired
    UserService userService;

    @RequestMapping(value = "getCheckInvoiceUserList",method = RequestMethod.GET)
    public YunResult getCheckUsers(){

        List<SysUser> SysUser = userService.getCheckInvoiceUserList();
        return YunResult.createBySuccess(SysUser);
    }


    @RequestMapping("/toCheck")
    public  YunResult toCheck(@RequestBody Invoice invoice){

        String orderId = invoice.getOrderId();
        Optional<Invoice> byId1 = invoiceRepository.findById(orderId);
        Invoice invoice1 = byId1.get();
        invoice1.setCheckFlag(1);
        invoiceRepository.save(invoice1);
        return  YunResult.createBySuccess("送审成功！",null);
    }

    //撤回
    @RequestMapping("/backFromCheck")
    public YunResult backFromCheck(@RequestBody Invoice invoice){
        String orderId = invoice.getOrderId();

        Optional<Invoice> byId1 = invoiceRepository.findById(orderId);
        Invoice invoice1 = byId1.get();
        invoice1.setCheckFlag(0);
        invoiceRepository.save(invoice1);
        return YunResult.createBySuccess("撤回成功！");
    }


    @RequestMapping(value = "/invalid",method = RequestMethod.POST)
    public YunResult invalid(@RequestBody Invoice invoice){
        String orderId = invoice.getOrderId();
        Optional<Invoice> byId2 = invoiceRepository.findById(orderId);
        Invoice invoice1 = byId2.get();
        invoice1.setCheckFlag(3);
        invoice1.setIsDeleted(1);
        invoiceRepository.save(invoice1);
        return YunResult.createBySuccessMessage("删除成功！");
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public YunResult update(@RequestBody Invoice invoice){

        invoice.setCreateTime(new Date());
        invoice.setIsDeleted(0);
        invoice.setCheckFlag(0);

        Optional<Enterprise> byId = enterprisesRepository.findById(invoice.getBuyerId());
        Enterprise enterprise = byId.get();
        invoice.setBuyerName(enterprise.getEnterpriseName());

        YunUser byUserName = yunUserRepository.findByUserName(invoice.getOrderCreator());
        invoice.setCreatorName(byUserName.getRealName());


        YunUser byUserName1 = yunUserRepository.findByUserName(invoice.getReviewerId());
        invoice.setReviewerName(byUserName1.getRealName());

        invoiceRepository.save(invoice);

        return  YunResult.createBySuccess("修改成功！");

    }


    @RequestMapping(value = "/updateNew",method = RequestMethod.POST)
    public YunResult updateNew(@RequestBody Invoice invoice){

        UUID u = UUID.randomUUID();
        String str = u.toString();
        str = str.replace("-","");
        Invoice invoice1 = new Invoice();

        invoice1.setOrderId(str);
        invoice1.setCreateTime(new Date());
        invoice1.setIsDeleted(0);
        invoice1.setCheckFlag(0);
        invoice1.setReviewerId(invoice.getReviewerId());
        invoice1.setOrderCreator(invoice.getOrderCreator());
        invoice1.setBuyerId(invoice.getBuyerId());
        invoice1.setOrderType(invoice.getOrderType());
        invoice1.setPreviousId(invoice.getOrderId());
        invoice1.setProductTotal(invoice.getProductTotal());
        invoice1.setProductPrice(invoice.getProductPrice());
        invoice1.setProductName(invoice.getProductName());
        invoice1.setProductAmount(invoice.getProductAmount());
        invoice1.setProductId(invoice.getProductId());
        invoice1.setSellerName(invoice.getSellerName());
        invoice1.setSellerId(invoice.getSellerId());


        Optional<Enterprise> byId = enterprisesRepository.findById(invoice.getBuyerId());
        Enterprise enterprise = byId.get();
        invoice1.setBuyerName(enterprise.getEnterpriseName());

        YunUser byUserName = yunUserRepository.findByUserName(invoice.getOrderCreator());
        invoice1.setCreatorName(byUserName.getRealName());

        YunUser byUserName1 = yunUserRepository.findByUserName(invoice.getReviewerId());
        invoice1.setReviewerName(byUserName1.getRealName());

        invoice.setCheckFlag(4);

        Optional<Invoice> byId1 = invoiceRepository.findById(invoice.getOrderId());
        Invoice invoice2 = byId1.get();
        invoice2.setCheckFlag(4);

        invoiceRepository.save(invoice2);
        invoiceRepository.save(invoice1);

        return  YunResult.createBySuccess("修改成功！");

    }

    @RequestMapping(value = "findByMany",method = RequestMethod.POST)
    public YunResult find(@RequestBody JSONObject jsonObject){

        String orderId = jsonObject.getString("orderId");
        Integer pageNum = jsonObject.getInteger("pageNum");
        Integer pageRow = jsonObject.getInteger("pageRow");
        Integer checkFlag = jsonObject.getInteger("checkFlag");
        String buyerName = jsonObject.getString("buyerName");
        String productName = jsonObject.getString("productName");


        String userName = SecurityUtils.getUser().getUserName();

        Date etime = jsonObject.getDate("etime");
        Date stime = jsonObject.getDate("stime");

        Page page = invoiceService.myInvoiceFindByMany(userName,stime, etime, checkFlag,buyerName, productName, orderId, pageNum, pageRow);
        return YunResult.createBySuccess(page);
    }







}
