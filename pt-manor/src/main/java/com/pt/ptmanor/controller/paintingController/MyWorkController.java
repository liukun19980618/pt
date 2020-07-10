package com.pt.ptmanor.controller.paintingController;

import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.mapper.painting.ProductionRepository;
import com.pt.ptmanor.mapper.painting.WorkRepository;
import com.pt.ptmanor.pojo.painting.Production;
import com.pt.ptmanor.pojo.painting.Work;
import com.pt.ptmanor.ptcommon.util.SecurityUtils;
import com.pt.ptmanor.service.painting.WorkService;
import com.pt.ptmanor.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;

@RequestMapping("/planting/myWork")
@RestController
public class MyWorkController {

    @Autowired
    WorkService workService;

    @Autowired
    WorkRepository workRepository;



    @RequestMapping("/list")
    public YunResult getList(int pageNum , int pageRow ){

        String userName = SecurityUtils.getUser().getUserName();
        Page page = workService.myList(pageNum, pageRow,userName);
        return YunResult.createBySuccess(page);
    }

    @RequestMapping("/toCheck")
    public  YunResult toCheck(@RequestBody Work work){

        String id = work.getId();
        Optional<Work> byId = workRepository.findById(id);
        Work work1 = byId.get();
        work1.setCheckNumber(1);
        workRepository.save(work1);
        return  YunResult.createBySuccess("送审成功！",null);
    }


    @Autowired
    ProductionRepository productionRepository;

    @RequestMapping("/pass")
    public  YunResult pass(@RequestBody Work work){

        String id = work.getId();
        Optional<Work> byId = workRepository.findById(id);
        Work work1 = byId.get();
        work1.setCheckNumber(2);
        if (work1.getWork().equals("采摘")){

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date date = new Date();
            String s1 = sdf.format(date);

                Production production = new Production();
                production.setId(id);
                production.setCrops(work1.getCrops());
                production.setAmount(work1.getAmount());
                production.setType("采摘");
                production.setDate(new Date());
                production.setIsDeleted(0);
                production.setUpdateDate(new Date());
                production.setYear(s1);
                productionRepository.save(production);
        }
        workRepository.save(work1);
        return  YunResult.createBySuccess("审核成功！",null);
    }







    @RequestMapping("/checkBack")
    public YunResult checkBack(@RequestBody Work work){
        String id = work.getId();
        Optional<Work> byId = workRepository.findById(id);
        Work work1 = byId.get();
        work1.setCheckNumber(3);
        workRepository.save(work1);
        return YunResult.createBySuccess("打回成功！");
    }


    //撤回
    @RequestMapping("/backFromCheck")
    public YunResult backFromCheck(@RequestBody Work work){
        String id = work.getId();
        Optional<Work> byId = workRepository.findById(id);
        Work work1 = byId.get();
        work1.setCheckNumber(0);
        workRepository.save(work1);
        return YunResult.createBySuccess("撤回成功！");
    }


    @RequestMapping(value = "/myWorkFindByMany",method = RequestMethod.POST)
    public YunResult myWorkFindByMany(@RequestBody JSONObject jsonObject){

        String id = jsonObject.getString("id");
        Integer pageNum = jsonObject.getInteger("pageNum");
        Integer pageRow = jsonObject.getInteger("pageRow");
        String crops = jsonObject.getString("crops");
        String work = jsonObject.getString("work");
        Integer checkNumber = jsonObject.getInteger("checkNumber");
        String findFarmlandRegionId = jsonObject.getString("findFarmlandRegionId");
        Date etime = jsonObject.getDate("etime");
        Date stime  = jsonObject.getDate("stime");

        String userName = SecurityUtils.getUser().getUserName();



        Page page = workService.myWorkFindByMany(userName,stime, etime, work, crops, checkNumber, id, findFarmlandRegionId, pageNum, pageRow);
        return  YunResult.createBySuccess(page);
    }

    @RequestMapping(value = "/toCheckWorkFindByMany",method = RequestMethod.POST)
    public YunResult toCheckWorkFindByMany(@RequestBody JSONObject jsonObject){

        String id = jsonObject.getString("id");
        Integer pageNum = jsonObject.getInteger("pageNum");
        Integer pageRow = jsonObject.getInteger("pageRow");
        String crops = jsonObject.getString("crops");
        String work = jsonObject.getString("work");
        String staff = jsonObject.getString("staff");
        String findFarmlandRegionId = jsonObject.getString("findFarmlandRegionId");
        Date etime = jsonObject.getDate("etime");
        Date stime  = jsonObject.getDate("stime");

        String userName = SecurityUtils.getUser().getUserName();


        Page page = workService.toCheckWorkFindByMany(userName,stime, etime, work, crops, staff, id, findFarmlandRegionId, pageNum, pageRow);
        return  YunResult.createBySuccess(page);
    }



    //已审核表单，查找
    @RequestMapping(value = "/finalWorkFindByMany",method = RequestMethod.POST)
    public YunResult finalWorkFindByMany(@RequestBody JSONObject jsonObject){

        String id = jsonObject.getString("id");
        Integer pageNum = jsonObject.getInteger("pageNum");
        Integer pageRow = jsonObject.getInteger("pageRow");
        Integer checkNumber = jsonObject.getInteger("checkNumber");
        String staff = jsonObject.getString("staff");
        String findFarmlandRegionId = jsonObject.getString("findFarmlandRegionId");
        Date etime = jsonObject.getDate("etime");
        Date stime  = jsonObject.getDate("stime");

        System.out.println(checkNumber+"checkNUmber");
        System.out.println(staff+"staff");
        System.out.println(findFarmlandRegionId+"findFarmlandRegionId");
        System.out.println(etime+"etime");
        System.out.println(id+"id");
        System.out.println(checkNumber+"checkNUmber");
        Page page = workService.finalWorkFindByMany(stime, etime, staff, id, findFarmlandRegionId, checkNumber,pageNum, pageRow);
        return  YunResult.createBySuccess(page);
    }











}
