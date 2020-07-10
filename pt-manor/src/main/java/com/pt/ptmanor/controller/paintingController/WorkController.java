package com.pt.ptmanor.controller.paintingController;


import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.mapper.painting.*;
import com.pt.ptmanor.pojo.painting.*;
import com.pt.ptmanor.pojo.user.SysUser;
import com.pt.ptmanor.ptcommon.util.SecurityUtils;
import com.pt.ptmanor.service.painting.WorkService;
import com.pt.ptmanor.service.user.UserService;
import com.pt.ptmanor.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequestMapping("/planting/work")
@RestController
public class WorkController {

    @Autowired
    WorkService workService;

    @Autowired
    WorkRepository workRepository;

    @Autowired
    ProductionRepository productionRepository;

    @Autowired
    FarmlandRegionRepository farmlandRegionRepository;


    @Autowired
    BatchRepository batchRepository;

    @Autowired
    CropsRepository cropsRepository;

    @Autowired
    MaterialRepository materialRepository;


    @RequestMapping("/toCheckList")
    public YunResult toCheckList(int pageNum , int pageRow ){

        String userName = SecurityUtils.getUser().getUserName();

        Page page = workService.toCheckList(pageNum, pageRow,userName);
        return YunResult.createBySuccess(page);
    }

    @RequestMapping("/list")
    public YunResult getList(int pageNum , int pageRow){

        Page page = workService.list(pageNum, pageRow);
        return YunResult.createBySuccess("查询成功！",page);
    }

    @RequestMapping("/materialList")
    public YunResult materialList(int pageNum , int pageRow){

        Page page = workService.materialList(pageNum, pageRow);
        return YunResult.createBySuccess("查询成功！",page);
    }

    //添加
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public YunResult save(@RequestBody Work work){
        if (work!= null && !work.equals("")){

            UUID u = UUID.randomUUID();
            String str = u.toString();
            str = str.replace("-","");
            work.setId(str);

//            if (work.getWork().equals("采摘")){
//                Production production = new Production();
//                production.setId(str);
//                production.setCrops(work.getCrops());
//                production.setAmount(work.getAmount());
//                production.setType("采摘");
//                production.setDate(new Date());
//                production.setIsDeleted(0);
//                production.setUpdateDate(new Date());
//                productionRepository.save(production);
//            }

            if(work.getMaterialName()!= ""){
                Material byMaterialName = materialRepository.findByMaterialName(work.getMaterialName());
                work.setMaterialCompany(byMaterialName.getMaterialCompany());
                work.setMaterialInformation(byMaterialName.getMaterialInformation());
            }
            work.setCheckNumber(0);

            work.setIsDeleted(0);
            work.setDate(new Date());
            work.setUpdateDate(new Date());
            workRepository.save(work);

            return  YunResult.createBySuccess("添加成功");
        }
        return YunResult.createByError();
    }

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public YunResult getById(@RequestParam("id") String id){
        Optional<Work> byId = workRepository.findById(id);
        Work work = byId.get();
        return YunResult.createBySuccess(work);
    }


    //审核完成的列表
    @RequestMapping("/finalList")
    public YunResult finalList(int pageNum , int pageRow ){

        Page page = workService.finalList(pageNum, pageRow);
        return YunResult.createBySuccess(page);
    }



    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public YunResult delete(@RequestBody Work work){

        String id = work.getId();
        Optional<Work> byId = workRepository.findById(id);
        Work work1 = byId.get();
        if(work1.getWork().equals("采摘")){

            Optional<Production> byId1 = productionRepository.findById(work.getId());
            Production production1 = byId1.get();

            production1.setIsDeleted(1);
            productionRepository.save(production1);
        }
        work1.setIsDeleted(1);

        workRepository.save(work1);
        return YunResult.createBySuccess("删除成功！");
    }

    @RequestMapping(value = "/findByMany",method = RequestMethod.POST)
    public YunResult find(@RequestBody JSONObject jsonObject){

        String id = jsonObject.getString("id");
        Integer pageNum = jsonObject.getInteger("pageNum");
        Integer pageRow = jsonObject.getInteger("pageRow");
        String crops = jsonObject.getString("crops");
        String work = jsonObject.getString("work");
        String staff = jsonObject.getString("staff");
        String findFarmlandRegionId = jsonObject.getString("findFarmlandRegionId");
        Date etime = jsonObject.getDate("etime");
        Date stime  = jsonObject.getDate("stime");

        Page page = workService.findByMany(stime, etime, work, crops, staff, id, findFarmlandRegionId, pageNum, pageRow);
        return  YunResult.createBySuccess(page);
    }

    @RequestMapping(value = "/findByManyMaterial",method = RequestMethod.POST)
    public YunResult findMaterial(@RequestBody JSONObject jsonObject){

        String id = jsonObject.getString("id");
        Integer pageNum = jsonObject.getInteger("pageNum");
        Integer pageRow = jsonObject.getInteger("pageRow");
        String crops = jsonObject.getString("crops");
        String work = jsonObject.getString("work");
        String staff = jsonObject.getString("staff");
        String field = jsonObject.getString("field");
        Date etime = jsonObject.getDate("etime");
        Date stime  = jsonObject.getDate("stime");
        Page page = workService.findByManyMaterial(stime, etime, work, crops, staff, id, field, pageNum, pageRow);
        return  YunResult.createBySuccess(page);

    }

//    @RequestMapping(value = "/update",method = RequestMethod.POST)
//    public YunResult update(@RequestBody Work work){
//
//        String findFarmlandRegionId = work.getFindFarmlandRegionId();
//        Optional<FarmlandRegion> byId = farmlandRegionRepository.findById(findFarmlandRegionId);
//        FarmlandRegion farmlandRegion = byId.get();
//        work.setFarmlandRegion(farmlandRegion);
//
//        if(work.getWork().equals("采摘")){
//            Optional<Production> byId1 = productionRepository.findById(work.getId());
//            Production production1 = byId1.get();
//            production1.setAmount(work.getAmount());
//            production1.setUpdateDate(new Date());
//            productionRepository.save(production1);
//        }
//
//        workRepository.save(work);
//        return YunResult.createBySuccess("修改成功！");
//    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public YunResult update(@RequestBody Work work){



        work.setCheckNumber(0);
        work.setUpdateDate(new Date());
        workRepository.save(work);
        return YunResult.createBySuccess("修改成功！");

    }

    @RequestMapping(value = "getFarmlandRegionId",method = RequestMethod.GET)
    public YunResult getFarmlandRegionId(){

        List<FarmlandRegion> all = farmlandRegionRepository.findAll();
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


    @RequestMapping(value = "getBatch",method = RequestMethod.GET)
    public YunResult getBatch(){

        List<Batch> all = batchRepository.findAll();
        for (int i=0;i<all.size();i++){
            if(all.get(i).getIsDeleted()==1){
                all.remove(i);
                i--;
            }
        }
        return YunResult.createBySuccess(all);

    }


    @RequestMapping(value = "getCrops",method = RequestMethod.GET)
    public YunResult getCrops(){

        List<Crops> all = cropsRepository.findAll();
        for (int i=0;i<all.size();i++){
            if(all.get(i).getIsDeleted()==1){
                all.remove(i);
                i--;
            }
        }
        return YunResult.createBySuccess(all);
    }


    @RequestMapping(value = "getMaterial",method = RequestMethod.GET)
    public YunResult getMaterial(){

        List<Material> all = materialRepository.findAll();
        for (int i=0;i<all.size();i++){
            if(all.get(i).getIsDeleted()==1){
                all.remove(i);
                i--;
            }
        }
        return YunResult.createBySuccess(all);
    }


    @Autowired
    WorkTypeRepository workTypeRepository;

    @RequestMapping(value = "getWorkTypes",method = RequestMethod.GET)
    public YunResult getWorkTypes(){
        List<WorkType> all = workTypeRepository.findAll();
        for (int i=0;i<all.size();i++){
            if(all.get(i).getIsDeleted()==1){
                all.remove(i);
                i--;
            }
        }
        return YunResult.createBySuccess(all);
    }


    @Autowired
    UserService userService;

    @RequestMapping(value = "getCheckUsers",method = RequestMethod.GET)
    public YunResult getCheckUsers(){
        List<SysUser> checkInvoiceUserList = userService.getCheckInvoiceUserList();


        return YunResult.createBySuccess(checkInvoiceUserList);
    }






}
