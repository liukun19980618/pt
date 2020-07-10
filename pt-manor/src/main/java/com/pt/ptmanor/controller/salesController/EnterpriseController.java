package com.pt.ptmanor.controller.salesController;


import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.mapper.EnterprisesRepository;
import com.pt.ptmanor.pojo.Enterprise;
import com.pt.ptmanor.service.EnterpriseService;
import com.pt.ptmanor.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RequestMapping("/enterprise")
@RestController
public class EnterpriseController {


    @Autowired
    EnterprisesRepository enterprisesRepository;

    @Autowired
    EnterpriseService enterpriseService;

    @RequestMapping("/list")
    public YunResult getList(int pageNum , int pageRow){
        System.out.println("pageNu====================="+pageNum);
        System.out.println("pageNu====================="+pageRow);
        Page page = enterpriseService.list(pageNum, pageRow);
        return YunResult.createBySuccess("查询成功！",page);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public YunResult add(@RequestBody Enterprise enterprise){
        if (enterprise!= null && !enterprise.equals("")){
//            UUID u = UUID.randomUUID();
//            String str = u.toString();
//            str = str.replace("-","");
            Date date = new Date();
            enterprise.setCreateTime(date);
//            enterprise.setId(str);
            enterprise.setIsDeleted(0);
             enterprisesRepository.save(enterprise);
            return YunResult.createBySuccess("添加成功！");
        }
        return YunResult.createByError();
    }

    @RequestMapping(value = "/findByName",method = RequestMethod.POST)
    public YunResult findByName(@RequestBody JSONObject jsonObject){
        String enterpriseName = jsonObject.getString("buyerName");
        if (enterpriseName.equals("")){
            return YunResult.createBySuccess("请输入参数");
        }else {

            Enterprise byEnterpriseName = enterprisesRepository.findByEnterpriseName(enterpriseName);

            if (byEnterpriseName == null){
                return YunResult.createBySuccess("没有该企业信息");
            }else {
                return YunResult.createBySuccess("查找成功",byEnterpriseName);
            }

        }
    }

    @RequestMapping(value = "/findByMany",method = RequestMethod.POST)
    public YunResult findByMany(@RequestBody JSONObject jsonObject){

        String enterpriseName = jsonObject.getString("enterpriseName");
        Integer pageNum = jsonObject.getInteger("pageNum");
        Integer pageRow = jsonObject.getInteger("pageRow");

        System.out.println(enterpriseName);
        Page page = enterpriseService.findByMany(enterpriseName, pageNum, pageRow);

        return YunResult.createBySuccess(page);
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public YunResult update(@RequestBody Enterprise enterprise){
        enterprise.setIsDeleted(0);
        System.out.println(enterprise.getEnterpriseName());
        enterprisesRepository.save(enterprise);
        return YunResult.createBySuccess("修改成功！");
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public YunResult delete(@RequestBody Enterprise enterprise){

        String id = enterprise.getId();
        Optional<Enterprise> byId = enterprisesRepository.findById(id);
        Enterprise enterprise1 = byId.get();
        enterprise1.setIsDeleted(1);
        enterprisesRepository.save(enterprise1);

        return YunResult.createBySuccess("删除成功！");
    }

}
