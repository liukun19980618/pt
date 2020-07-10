package com.pt.ptmanor.controller.paintingController;

import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.mapper.product.ProductRepository;
import com.pt.ptmanor.service.painting.ProductionService;
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

@RequestMapping("/production")
@RestController
public class ProductionController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductionService productionService;

    @RequestMapping("/list")
    public YunResult getList(int pageNum , int pageRow){

        Page page = productionService.list(pageNum, pageRow);
        return YunResult.createBySuccess("查询成功！",page);
    }


    @RequestMapping(value = "/find",method = RequestMethod.POST)
    public YunResult  find(@RequestBody JSONObject jsonObject){

        String crops = jsonObject.getString("crops");
        Date stime = jsonObject.getDate("stime");
        Date etime = jsonObject.getDate("etime");
        Integer pageRow = jsonObject.getInteger("pageRow");
        Integer pageNum = jsonObject.getInteger("pageNum");

        Page page = productionService.findByMany(crops, stime, etime, pageNum, pageRow);
        return YunResult.createBySuccess(page);
    }

    @RequestMapping("chart")
    public YunResult chart(){
        String s1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        s1 = sdf.format(date);
        Map chart = productionService.chart(s1);
        return YunResult.createBySuccess("查询成功",chart);
    }


    @RequestMapping(value = "findChart",method = RequestMethod.POST)
    public YunResult getList(@RequestBody JSONObject jsonObject){

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

        if (!crops.isEmpty()){
            Map chart = productionService.findChart(s1, crops);
            return YunResult.createBySuccess("查询成功！",chart);
        }

        Map chart = productionService.chart(s1);
        return YunResult.createBySuccess("查询成功",chart);
    }



}
