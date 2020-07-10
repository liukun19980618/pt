package com.pt.ptmanor.controller;


import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.service.ChartsService;
import com.pt.ptmanor.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RequestMapping("yun/charts/")
@RestController
public class ChartsController {

    @Autowired
    private ChartsService chartsService;


    @RequestMapping("list")
    public YunResult getChart(){
        // 男性的人数，女性的人数，每个月的男女总人数
        Map map = chartsService.getList();
        return  YunResult.createBySuccess("查询图表数据成功！",map);
    }


	@RequestMapping("moneyList")
    public YunResult getMoney(){
        String s1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        s1 = sdf.format(date);
        Map map = chartsService.getFinancialList(s1);
        return YunResult.createBySuccess("查询成功",map);
    }


    @RequestMapping(value = "findList",method = RequestMethod.POST)
    public YunResult getList(@RequestBody JSONObject jsonObject){

        String buyerName = jsonObject.getString("buyerName");
        String productName = jsonObject.getString("productName");
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

        if (!buyerName.isEmpty()){
                if (!productName.isEmpty()){
                    Map map = chartsService.financialByBuyerNameAndProductName(s1,buyerName, productName);
                    return YunResult.createBySuccess("查询成功！",map);

                }
                Map map = chartsService.financialFindList(s1,buyerName);
                return YunResult.createBySuccess("查询成功！",map);
            }

        Map map = chartsService.getFinancialList(s1);
        return YunResult.createBySuccess("查询成功",map);
    }


}
