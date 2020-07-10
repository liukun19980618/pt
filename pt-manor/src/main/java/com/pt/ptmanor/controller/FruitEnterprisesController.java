package com.pt.ptmanor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/fruitEnterprises")
@RestController
public class FruitEnterprisesController {


//    @Autowired
//    private FruitEnterprisesService fruitEnterprisesService;
//
//
////    @RequiresPermissions("fruitEnterprises:list")
//    @RequestMapping("/list")
//    public YunResult getList (int pageNum, int pageRow){
//
//        IPage<FruitEnterprises> page = new Page<>(pageNum,pageRow);
//
//        IPage<FruitEnterprises> FruitEnterprisesIPage = fruitEnterprisesService.page(page,new QueryWrapper<FruitEnterprises>().lambda().eq(FruitEnterprises::getIsDeleted,"0"));
//
//        return  YunResult.createBySuccess("查询成功!",FruitEnterprisesIPage);
//    }
//
//
//
//    @RequestMapping(value = "/add",method = RequestMethod.POST)
//    public YunResult add(@RequestBody FruitEnterprises jsonObject){
//        if (jsonObject!= null && !jsonObject.equals("")){
//            jsonObject.setIsDeleted("0");
//            boolean saveOrUpdate = fruitEnterprisesService.saveOrUpdate(jsonObject);
//            return  YunResult.createBySuccess(saveOrUpdate);
//        }
//        return YunResult.createByError();
//    }
//
//        @RequestMapping("/update")
//        public YunResult update(@RequestBody FruitEnterprises fruitEnterprises){
//
//            if (fruitEnterprises!=null&&!fruitEnterprises.equals("")){
//                boolean saveOrUpdate = fruitEnterprisesService.saveOrUpdate(fruitEnterprises);
//                return YunResult.createBySuccess("更新成功!",saveOrUpdate);
//            }
//
//        return YunResult.createByError();
//        }
//
//
////    @RequiresPermissions("fruitEnterprises:update")
//    @RequestMapping("/delete")
//    public YunResult delete(@RequestBody FruitEnterprises fruitEnterprises){
//
//        if (fruitEnterprises!=null&&!fruitEnterprises.equals("")){
//            fruitEnterprises.setIsDeleted("1");
//            boolean saveOrUpdate = fruitEnterprisesService.saveOrUpdate(fruitEnterprises);
//            return YunResult.createBySuccess("删除!",saveOrUpdate);
//        }
//
//        return  YunResult.createByError();
//    }
//
////    @RequiresPermissions("fruitEnterprises:add")
//    @RequestMapping(value = "/find",method = RequestMethod.POST)
//    public YunResult find1(@RequestBody JSONObject jsonObject){
//
//        Integer pageNum = jsonObject.getInteger("pageNum");
//        Integer pageRow = jsonObject.getInteger("pageRow");
//        String enterpriseName = jsonObject.getString("enterpriseName");
//
//        String enterpriseNumbers = "";
//
//        System.out.println(enterpriseName);
//        System.out.println("pagenum"+pageNum);
//        System.out.println("entername"+enterpriseName);
//        FruitEnterprises fruitEnterprises = new FruitEnterprises();
//
//
//        if (fruitEnterprises!=null&&!fruitEnterprises.equals("")){
//
//            IPage<FruitEnterprises> FruitEnterprisesIPage = fruitEnterprisesService.page(new Page<>(pageNum,pageRow),
//                    new QueryWrapper<FruitEnterprises>().lambda().eq(FruitEnterprises::getIsDeleted,"0")
//                    .like(!StringUtils.isEmpty(enterpriseName), FruitEnterprises::getEnterpriseName,enterpriseName)) ;
////                    .like(!StringUtils.isEmpty(enterpriseNumbers),FruitEnterprises::getEnterpriseNumbers,enterpriseNumbers));
//
//            return  YunResult.createBySuccess("查询成功!",FruitEnterprisesIPage);
//        }
//
//        return  YunResult.createByError();
//    }


}
