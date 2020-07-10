package com.pt.ptmanor.controller.paintingController;

import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.mapper.painting.WorkTypeRepository;
import com.pt.ptmanor.pojo.painting.WorkType;
import com.pt.ptmanor.service.painting.WorkTypeService;
import com.pt.ptmanor.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;


@RequestMapping("/planting/workType")
@RestController
public class WorkTypeController {

    @Autowired
    WorkTypeService workTypeService;

    @Autowired
    WorkTypeRepository workTypeRepository;

    @RequestMapping("/list")
    public YunResult getList(int pageNum , int pageRow){

        Page page = workTypeService.list(pageNum, pageRow);
        return YunResult.createBySuccess("查询成功！",page);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public YunResult add(@RequestBody JSONObject jsonObject){

        UUID u = UUID.randomUUID();
        String str = u.toString();
        str = str.replace("-","");
        String name = jsonObject.getString("name");
        Boolean needMaterial = jsonObject.getBoolean("needMaterial");
        WorkType workType = new WorkType();
        if (needMaterial == true){
            String a  = "是";
            workType.setNeedMaterial(a);
        }else {
            String a = "否";
            workType.setNeedMaterial(a);
        }
        workType.setName(name);
        workType.setId(str);
        workType.setIsDeleted(0);
        workTypeRepository.save(workType);
        return YunResult.createBySuccess("保存成功！");
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public YunResult delete(@RequestBody WorkType workType){

        System.out.println(workType.getId());

        Optional<WorkType> byId = workTypeRepository.findById(workType.getId());
        WorkType workType1 = byId.get();
        workType1.setIsDeleted(1);
        workTypeRepository.save(workType1);
        return YunResult.createBySuccess("删除成功！");
    }



}
