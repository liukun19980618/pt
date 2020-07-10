package com.pt.ptmanor.controller;


import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.mapper.FinancialFormRepository;
import com.pt.ptmanor.service.FinancialFormService;
import com.pt.ptmanor.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("/financaial")
@RestController
public class FinancialFormController {

    @Autowired
    FinancialFormRepository financialFormRepository;

    @Autowired
    FinancialFormService financialFormService;


    @RequestMapping("/list")
    public YunResult getList(int pageNum , int pageRow){

        Page page = financialFormService.list(pageNum, pageRow);
        return YunResult.createBySuccess("查询成功！",page);
    }

    @RequestMapping(value = "/find",method = RequestMethod.POST)
    public YunResult find(@RequestBody JSONObject jsonObject){

        String invoiceId = jsonObject.getString("invoiceId");
        String buyerName = jsonObject.getString("buyerName");
        String productName = jsonObject.getString("productName");

        Integer pageNum = jsonObject.getInteger("pageNum");
        Integer pageRow = jsonObject.getInteger("pageRow");

        Date etime = jsonObject.getDate("etime");
        Date stime = jsonObject.getDate("stime");


        Page page = financialFormService.findByMany(productName,invoiceId, buyerName, etime, stime, pageNum, pageRow);
        return YunResult.createBySuccess("查询成功！",page);

    }



}
