package com.pt.ptmanor.controller.paintingController;

import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.mapper.painting.FarmlandRegionTypeRepository;
import com.pt.ptmanor.pojo.painting.Crops;
import com.pt.ptmanor.pojo.painting.FarmlandRegionType;
import com.pt.ptmanor.service.painting.FarmlandRegionTypeService;
import com.pt.ptmanor.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/planting/farmlandRegionType")
@RestController
public class FarmlandRegionTypeController {
    @Autowired
    FarmlandRegionTypeRepository farmlandRegionTypeRepository;

    @Autowired
    FarmlandRegionTypeService farmlandRegionTypeService;

    @RequestMapping("/list")
    public YunResult getList(int pageNum , int pageRow){

        Page page = farmlandRegionTypeService.list(pageNum, pageRow);
        return YunResult.createBySuccess("查询成功！",page);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public YunResult add(@RequestBody JSONObject jsonObject){


        UUID u = UUID.randomUUID();
        String str = u.toString();
        str = str.replace("-","");
        String name = jsonObject.getString("name");
        FarmlandRegionType farmlandRegionType = new FarmlandRegionType();
        Crops crops = new Crops();
        farmlandRegionType.setIsDeleted(0);
        farmlandRegionType.setName(name);
        farmlandRegionType.setId(str);
        farmlandRegionTypeRepository.save(farmlandRegionType);
        return YunResult.createBySuccess("保存成功！");
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public YunResult delete(@RequestBody FarmlandRegionType farmlandRegionType){

        Optional<FarmlandRegionType> byId = farmlandRegionTypeRepository.findById(farmlandRegionType.getId());
        FarmlandRegionType farmlandRegionType1 = byId.get();
        farmlandRegionType1.setIsDeleted(1);
        farmlandRegionTypeRepository.save(farmlandRegionType1);
        return YunResult.createBySuccess("删除成功！");
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public YunResult update(@RequestBody FarmlandRegionType farmlandRegionType){

        Optional<FarmlandRegionType> byId = farmlandRegionTypeRepository.findById(farmlandRegionType.getId());
        FarmlandRegionType farmlandRegionType1 = byId.get();
        farmlandRegionType1.setName(farmlandRegionType.getName());
        farmlandRegionTypeRepository.save(farmlandRegionType1);
        return YunResult.createBySuccess("修改成功！");
    }
}
