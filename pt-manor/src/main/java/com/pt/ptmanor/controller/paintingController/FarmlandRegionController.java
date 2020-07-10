package com.pt.ptmanor.controller.paintingController;


import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.mapper.painting.FarmlandLocationRepository;
import com.pt.ptmanor.mapper.painting.FarmlandRegionRepository;
import com.pt.ptmanor.mapper.painting.FarmlandRegionTypeRepository;
import com.pt.ptmanor.pojo.painting.FarmlandLocation;
import com.pt.ptmanor.pojo.painting.FarmlandRegion;
import com.pt.ptmanor.pojo.painting.FarmlandRegionType;
import com.pt.ptmanor.service.painting.FarmlandRegionService;
import com.pt.ptmanor.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/planting/farmlandRegion")
@RestController
public class FarmlandRegionController {


    @Autowired
    FarmlandRegionService farmlandRegionService;

    @Autowired
    FarmlandRegionRepository farmlandRegionRepository;

    @Autowired
    FarmlandLocationRepository farmlandLocationRepository;

    @RequestMapping("/list")
    public YunResult getList(int pageNum , int pageRow){

        Page page = farmlandRegionService.list(pageNum, pageRow);
        return YunResult.createBySuccess("查询成功！",page);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public YunResult add (@RequestBody FarmlandRegion farmlandRegion){

        String farmlandLocationName = farmlandRegion.getFarmlandLocationName();
        if (farmlandLocationName != ""){
            FarmlandLocation byFarmlandLocationName = farmlandLocationRepository.findByFarmlandLocationName(farmlandRegion.getFarmlandLocationName());
            farmlandRegion.setIsDeleted(0);
            farmlandRegion.setFarmlandLocation(byFarmlandLocationName);
            farmlandRegionRepository.save(farmlandRegion);
            return YunResult.createBySuccess("添加成功！");
        }
        return  YunResult.createByErrorMessage("参数错误");
    }


    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public YunResult find (@RequestParam String farmlandRegionName){
        Optional<FarmlandRegion> byId = farmlandRegionRepository.findById(farmlandRegionName);
        FarmlandRegion farmlandRegion = byId.get();

        return YunResult.createBySuccess("查询成功",farmlandRegion);
    }

    @RequestMapping(value = "/findByMany",method = RequestMethod.POST)
    public YunResult find(@RequestBody JSONObject jsonObject){

        String farmlandRegionId = jsonObject.getString("farmlandRegionId");
        String farmlandRegionPrincipal = jsonObject.getString("farmlandRegionPrincipal");
        String farmlandRegionType = jsonObject.getString("farmlandRegionType");
        String farmlandRegionExplain = jsonObject.getString("farmlandRegionExplain");
        String farmlandLocationName = jsonObject.getString("farmlandLocationName");
        Integer pageNum = jsonObject.getInteger("pageNum");
        Integer pageRow = jsonObject.getInteger("pageRow");

        Page page = farmlandRegionService.findByMany(farmlandRegionId, farmlandRegionPrincipal, farmlandRegionType, farmlandRegionExplain, farmlandLocationName, pageNum, pageRow);

        return YunResult.createBySuccess(page);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public YunResult update(@RequestBody FarmlandRegion farmlandRegion){

        farmlandRegionRepository.save(farmlandRegion);
        return YunResult.createBySuccess("修改成功！");
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public YunResult delete(@RequestBody FarmlandRegion farmlandRegion){

        String farmlandRegionId = farmlandRegion.getFarmlandRegionId();
        System.out.println(farmlandRegionId);

        FarmlandRegion byFarmlandRegionId = farmlandRegionRepository.findByFarmlandRegionId(farmlandRegionId);

        byFarmlandRegionId.setIsDeleted(1);
        farmlandRegionRepository.save(byFarmlandRegionId);
        return YunResult.createBySuccess("删除成功！");
    }

    @RequestMapping(value = "/farmlandLocationName",method = RequestMethod.GET)
    public YunResult farmlandLocationName(){

        List<FarmlandLocation> all = farmlandLocationRepository.findAll();
        for (int i=0;i<all.size();i++){
            if(all.get(i).getIsDeleted()==1){
                all.remove(i);
                i--;
            }
        }
        return YunResult.createBySuccess(all);
    }

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public YunResult getById(@RequestParam("id") String id){
        Optional<FarmlandRegion> byId = farmlandRegionRepository.findById(id);
        System.out.println("========="+id);
        FarmlandRegion farmlandRegion = byId.get();
        return YunResult.createBySuccess(farmlandRegion);
    }

    @Autowired
    FarmlandRegionTypeRepository farmlandRegionTypeRepository;

    @RequestMapping(value = "getFarmlandRegionType",method = RequestMethod.GET)
    public YunResult getFarmlandRegionId(){

        List<FarmlandRegionType> all = farmlandRegionTypeRepository.findAll();
//        Page list = farmlandRegionService.list(1, 1000);
////        List list = new ArrayList();
        for (int i=0;i<all.size();i++){
            if(all.get(i).getIsDeleted()==1){
                all.remove(i);
                i--;
            }
        }
        return YunResult.createBySuccess(all);

    }





}
