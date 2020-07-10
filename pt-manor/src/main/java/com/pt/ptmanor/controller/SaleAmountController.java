package com.pt.ptmanor.controller;

import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.mapper.SaleAmountRepository;
import com.pt.ptmanor.service.SaleAmountService;
import com.pt.ptmanor.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RequestMapping("/saleAmount")
@RestController
public class SaleAmountController {

    @Autowired
    SaleAmountService saleAmountService;

    @Autowired
    SaleAmountRepository saleAmountRepository;


    @RequestMapping("/list")
    public YunResult getList(int pageNum , int pageRow){

        Page page = saleAmountService.list(pageNum, pageRow);
        return YunResult.createBySuccess("查询成功！",page);
    }

    @RequestMapping(value = "/find",method = RequestMethod.POST)
    public YunResult find(@RequestBody JSONObject jsonObject){

        String crops = jsonObject.getString("crops");
        Date stime = jsonObject.getDate("stime");
        Date etime = jsonObject.getDate("etime");
        Integer pageRow = jsonObject.getInteger("pageRow");
        Integer pageNum = jsonObject.getInteger("pageNum");

        Page page = saleAmountService.findByMany(crops, stime, etime, pageNum, pageRow);
        return YunResult.createBySuccess(page);
    }


    @RequestMapping("chart")
    public YunResult chart(){
        String s1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        s1 = sdf.format(date);
        Map chart = saleAmountService.chart(s1);
        return YunResult.createBySuccess("查询成功",chart);
    }



    @RequestMapping(value = "findList",method = RequestMethod.POST)
    public YunResult getList(@RequestBody JSONObject jsonObject){

        String buyerName = jsonObject.getString("buyerName");
        String crops = jsonObject.getString("crops");
        Date year = jsonObject.getDate("year");

        String s1;
        if (year==null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date date = new Date();
            s1 = sdf.format(date);

        }else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            s1 = sdf.format(year);
        }

        Map chart = saleAmountService.findChart(s1, crops, buyerName);
        return YunResult.createBySuccess("查询成功",chart);
    }
    
}
