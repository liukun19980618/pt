package com.pt.ptmanor.controller.paintingController;


import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.mapper.painting.FarmlandLocationRepository;
import com.pt.ptmanor.pojo.painting.FarmlandLocation;
import com.pt.ptmanor.service.painting.FarmlandLocationService;
import com.pt.ptmanor.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/planting/farmlandLocation")
@RestController
public class FarmlandLocationController {

    @Autowired
    FarmlandLocationService farmlandLocationService;

    @Autowired
    FarmlandLocationRepository farmlandLocationRepository;

    @RequestMapping("/list")
    public YunResult getList(int pageNum , int pageRow){

        Page page = farmlandLocationService.list(pageNum, pageRow);
        return YunResult.createBySuccess("查询成功！",page);
    }


    @RequestMapping("/add")
    public YunResult add (@RequestBody FarmlandLocation farmlandLocation){

        UUID u = UUID.randomUUID();
        String str = u.toString();
        str = str.replace("-","");
        farmlandLocation.setFarmlandLocationId(str);
        farmlandLocation.setIsDeleted(0);
        farmlandLocationRepository.save(farmlandLocation);
        return YunResult.createBySuccess("添加成功！");

    }


    @RequestMapping(value = "/findByMany",method = RequestMethod.POST)
    public YunResult find(@RequestBody JSONObject jsonObject){

        String farmlandLocationId = jsonObject.getString("farmlandLocationId");
        String farmlandLocationName = jsonObject.getString("farmlandLocationName");
        String farmlandPrincipal = jsonObject.getString("farmlandPrincipal");
        String farmlandArea = jsonObject.getString("farmlandArea");
        String farmlandExplain = jsonObject.getString("farmlandExplain");


        Integer pageNum = jsonObject.getInteger("pageNum");
        Integer pageRow = jsonObject.getInteger("pageRow");

         Page page = farmlandLocationService.findByMany(farmlandLocationId,farmlandLocationName,farmlandPrincipal,
                farmlandArea,farmlandExplain,pageNum,pageRow);


        return YunResult.createBySuccess(page);
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public YunResult update(@RequestBody FarmlandLocation farmlandLocation){
        farmlandLocationRepository.save(farmlandLocation);
        return YunResult.createBySuccess("修改成功！");
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public YunResult delete(@RequestBody FarmlandLocation farmlandLocation){

        String farmlandLocationId = farmlandLocation.getFarmlandLocationId();
        FarmlandLocation byFarmlandLocationId = farmlandLocationRepository.findByFarmlandLocationId(farmlandLocationId);

        byFarmlandLocationId.setIsDeleted(1);
        farmlandLocationRepository.save(byFarmlandLocationId);

        return YunResult.createBySuccess("删除成功！");
    }



    @RequestMapping(value = "get",method = RequestMethod.GET)
    public YunResult getById(@RequestParam("id") String id){
        Optional<FarmlandLocation> byId = farmlandLocationRepository.findById(id);
        FarmlandLocation farmlandLocation = byId.get();
        return YunResult.createBySuccess(farmlandLocation);
    }



}
