package com.pt.ptmanor.controller.paintingController;


import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.mapper.painting.CropsRepository;
import com.pt.ptmanor.pojo.painting.Crops;
import com.pt.ptmanor.service.painting.CropsService;
import com.pt.ptmanor.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/planting/crops")
@RestController
public class CropsController {

    @Autowired
    CropsRepository cropsRepository;

    @Autowired
    CropsService cropsService;

    @RequestMapping("/list")
    public YunResult getList(int pageNum , int pageRow){

        Page page = cropsService.list(pageNum, pageRow);
        return YunResult.createBySuccess("查询成功！",page);
    }


    @RequestMapping(value = "/find",method = RequestMethod.POST)
    public YunResult find(@RequestBody JSONObject jsonObject){

        String name = jsonObject.getString("name");

        Integer pageNum = jsonObject.getInteger("pageNum");
        Integer pageRow = jsonObject.getInteger("pageRow");

        Page page = cropsService.findByMany(name,pageNum,pageRow);

        return YunResult.createBySuccess(page);
    }



    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public YunResult add(@RequestBody JSONObject jsonObject){


        UUID u = UUID.randomUUID();
        String str = u.toString();
        str = str.replace("-","");
        String name = jsonObject.getString("name");
        String remark = jsonObject.getString("remark");
        Crops crops = new Crops();
        crops.setIsDeleted(0);
        crops.setName(name);
        crops.setRemark(remark);
        crops.setId(str);
        cropsRepository.save(crops);

        return YunResult.createBySuccess("保存成功！");
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public YunResult delete(@RequestBody Crops crops){

        Optional<Crops> byId = cropsRepository.findById(crops.getId());
        Crops crops1 = byId.get();
        crops1.setIsDeleted(1);
        cropsRepository.save(crops1);

        return YunResult.createBySuccess("删除成功！");
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public YunResult update(@RequestBody Crops crops){

        Optional<Crops> byId = cropsRepository.findById(crops.getId());
        Crops crops1 = byId.get();
        crops1.setName(crops.getName());
        cropsRepository.save(crops1);

        return YunResult.createBySuccess("修改成功！");
    }





}
