package com.pt.ptmanor.controller.salesController;

import com.alibaba.fastjson.JSONObject;
import com.pt.ptmanor.mapper.EnterprisesRepository;
import com.pt.ptmanor.mapper.FinancialFormRepository;
import com.pt.ptmanor.mapper.SaleAmountRepository;
import com.pt.ptmanor.mapper.painting.BatchRepository;
import com.pt.ptmanor.mapper.painting.CropsRepository;
import com.pt.ptmanor.mapper.painting.FarmlandRegionRepository;
import com.pt.ptmanor.mapper.painting.ProductionRepository;
import com.pt.ptmanor.mapper.product.InvoiceRepository;
import com.pt.ptmanor.pojo.Enterprise;
import com.pt.ptmanor.pojo.FinancialForm;
import com.pt.ptmanor.pojo.SaleAmount;
import com.pt.ptmanor.pojo.painting.Batch;
import com.pt.ptmanor.pojo.painting.Crops;
import com.pt.ptmanor.pojo.painting.FarmlandRegion;
import com.pt.ptmanor.pojo.product.Invoice;
import com.pt.ptmanor.pojo.user.SysUser;
import com.pt.ptmanor.service.painting.FarmlandRegionService;
import com.pt.ptmanor.service.product.InvoiceService;
import com.pt.ptmanor.service.user.UserService;
import com.pt.ptmanor.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/invoice")
@RestController
public class invoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    FinancialFormRepository financialFormRepository;

    @Autowired
    ProductionRepository productionRepository;

    @Autowired
    FarmlandRegionRepository farmlandRegionRepository;

    @Autowired
    FarmlandRegionService farmlandRegionService;

    @Autowired
    SaleAmountRepository saleAmountRepository;

    @Autowired
    UserService userService;

    @RequestMapping("/list")
    public YunResult getList(int pageNum , int pageRow){

        Page page = invoiceService.list(pageNum, pageRow);
        return YunResult.createBySuccess("查询成功！",page);
    }

    @RequestMapping("/getCheckInvoiceUserList")
    public YunResult getCheckInvoiceUserList(){

        List<SysUser> checkInvoiceUserList = userService.getCheckInvoiceUserList();
        return YunResult.createBySuccess(checkInvoiceUserList);
    }



    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public YunResult save(@RequestBody Invoice invoice){

        if (invoice!= null && !invoice.equals("")){
            UUID u = UUID.randomUUID();
            String str = u.toString();
            str = str.replace("-","");
            UUID u1 = UUID.randomUUID();
            String str1=u1.toString();
            str1 = str1.replace("-","");
            invoice.setSellerNumber(str1);
            invoice.setOrderId(str);

            String buyerId = invoice.getBuyerId();
            Optional<Enterprise> byId = enterprisesRepository.findById(buyerId);
            Enterprise enterprise = byId.get();
            invoice.setBuyerName(enterprise.getEnterpriseName());
            //销售额表单
            if ((!invoice.getProductTotal().equals(""))&&(invoice.getOrderType()==0)){
                FinancialForm financialForm = new FinancialForm();
                financialForm.setId(str);
                financialForm.setBuyerName(invoice.getBuyerName());
                financialForm.setDate(new Date());
                financialForm.setIsDeleted(0);
                financialForm.setMoney(invoice.getProductTotal());
                financialForm.setUpdateDate(new Date());

                financialFormRepository.save(financialForm);
            }

            //销售产量表单
            if ((!invoice.getProductAmount().equals(0))&&(invoice.getOrderType()==0)){
                SaleAmount saleAmount = new SaleAmount();
                saleAmount.setId(str);
                saleAmount.setDate(new Date());
                saleAmount.setIsDeleted(0);
                saleAmount.setAmount(invoice.getProductAmount());
                saleAmount.setCrops(invoice.getProductName());
                saleAmount.setUpdateDate(new Date());
                saleAmountRepository.save(saleAmount);
            }
            invoice.setIsDeleted(0);
            invoice.setCreateTime(new Date());
            invoice.setOrderUpdateTime(new Date());
            invoice.setStatus(0);

            invoiceRepository.save(invoice);

            return  YunResult.createBySuccess("添加成功");
        }
        return YunResult.createByError();
    }

    @Autowired
    CropsRepository cropsRepository;

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


    @Autowired
    BatchRepository batchRepository;

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


    @RequestMapping(value = "findByMany",method = RequestMethod.POST)
    public YunResult find(@RequestBody JSONObject jsonObject){

        String orderId = jsonObject.getString("orderId");
        Integer pageNum = jsonObject.getInteger("pageNum");
        Integer pageRow = jsonObject.getInteger("pageRow");
        Integer status = jsonObject.getInteger("status");
        String buyerName = jsonObject.getString("buyerName");
        String productName = jsonObject.getString("productName");


        Date etime = jsonObject.getDate("etime");
        Date stime = jsonObject.getDate("stime");

        Page page = invoiceService.findByMany(stime, etime, status,buyerName, productName, orderId, pageNum, pageRow);
        return YunResult.createBySuccess(page);
    }

//    @RequestMapping(value = "getBatch",method = RequestMethod.GET)
//    public YunResult getBatch(){
//
//        List<Batch> all = batchRepository.findAll();
//        for (int i=0;i<all.size();i++){
//            if(all.get(i).getIsDeleted()==1){
//                all.remove(i);
//                i--;
//            }
//        }
//        return YunResult.createBySuccess(all);
//
//    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public YunResult find(@RequestBody Invoice invoice){
        String orderId = invoice.getOrderId();
        Optional<Invoice> byId2 = invoiceRepository.findById(orderId);
        Invoice invoice1 = byId2.get();
        if ((!invoice.getOrderId().equals(""))&&(invoice1.getOrderType()==0)){
            Optional<SaleAmount> byId = saleAmountRepository.findById(invoice.getOrderId());
            SaleAmount saleAmount = byId.get();
            saleAmount.setIsDeleted(1);
            saleAmountRepository.save(saleAmount);

            Optional<FinancialForm> byId1 = financialFormRepository.findById(invoice.getOrderId());
            FinancialForm financialForm = byId1.get();
            financialForm.setIsDeleted(1);
            financialFormRepository.save(financialForm);
        }
        invoice1.setIsDeleted(1);
        invoiceRepository.save(invoice1);
        return YunResult.createBySuccessMessage("删除成功！");
    }


    @RequestMapping(value = "/invalid",method = RequestMethod.POST)
    public YunResult invalid(@RequestBody Invoice invoice){
        String orderId = invoice.getOrderId();
        Optional<Invoice> byId2 = invoiceRepository.findById(orderId);
        Invoice invoice1 = byId2.get();
        if ((!invoice.getOrderId().equals(""))&&(invoice1.getOrderType()==0)){
            Optional<SaleAmount> byId = saleAmountRepository.findById(invoice.getOrderId());
            SaleAmount saleAmount = byId.get();
            saleAmount.setIsDeleted(1);
            saleAmountRepository.save(saleAmount);

            Optional<FinancialForm> byId1 = financialFormRepository.findById(invoice.getOrderId());
            FinancialForm financialForm = byId1.get();
            financialForm.setIsDeleted(1);
            financialFormRepository.save(financialForm);
        }
//        invoice.setIsDeleted(1);
        invoice1.setStatus(2);
        invoiceRepository.save(invoice1);
        return YunResult.createBySuccessMessage("删除成功！");
    }



    @RequestMapping(value = "get",method = RequestMethod.GET)
    public YunResult getById(@RequestParam("id") String id){
        Optional<Invoice> byId = invoiceRepository.findById(id);
        Invoice invoice = byId.get();
        return YunResult.createBySuccess(invoice);
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public YunResult update(@RequestBody Invoice invoice){

        Invoice invoice1 = new Invoice();

        UUID u = UUID.randomUUID();
        String str = u.toString();
        str = str.replace("-","");

        invoice1.setOrderId(str);
        invoice1.setStatus(1);
        invoice1.setBuyerName(invoice.getBuyerName());
        invoice1.setIsDeleted(0);
        invoice1.setProductName(invoice.getProductName());
        invoice1.setSellerNumber(invoice.getSellerNumber());
        invoice1.setBeforeInvoiceId(invoice.getOrderId());
        invoice1.setBuyerNumber(invoice.getBuyerNumber());
        invoice1.setProductAmount(invoice.getProductAmount());
        invoice1.setBuyerId(invoice.getBuyerId());
        invoice1.setOrderType(invoice.getOrderType());
        invoice1.setProductId(invoice.getProductId());
        invoice1.setProductPrice(invoice.getProductPrice());
        invoice1.setProductTotal(invoice.getProductTotal());
        invoice1.setSellerCreator(invoice.getSellerCreator());
        invoice1.setSellerId(invoice.getSellerId());
        invoice1.setSellerName(invoice.getSellerName());

        invoice1.setCreateTime(new Date());


        if ((!invoice.getOrderId().equals(""))){

            invoice.setStatus(2);

            if(invoice.getOrderType()==0){

                Optional<SaleAmount> byId = saleAmountRepository.findById(invoice.getOrderId());
                Optional<FinancialForm> byId1 = financialFormRepository.findById(invoice.getOrderId());

                if ((byId.isPresent()==false)||(byId1.isPresent()==false)){
                    SaleAmount saleAmount = new SaleAmount();
                    saleAmount.setId(invoice1.getOrderId());
                    saleAmount.setIsDeleted(0);
                    saleAmount.setDate(new Date());
                    saleAmount.setUpdateDate(new Date());
                    saleAmount.setCrops(invoice1.getProductName());
                    saleAmount.setAmount(invoice1.getProductAmount());

                    saleAmountRepository.save(saleAmount);

                    FinancialForm financialForm = new FinancialForm();
                    financialForm.setIsDeleted(0);
                    financialForm.setUpdateDate(new Date());
                    financialForm.setDate(new Date());
                    financialForm.setBuyerName(invoice1.getBuyerName());
                    financialForm.setId(invoice1.getOrderId());
                    financialForm.setMoney(invoice1.getProductTotal());
                    financialFormRepository.save(financialForm);
                }else {

                    SaleAmount saleAmount = byId.get();
                    saleAmount.setIsDeleted(1);


                    SaleAmount saleAmount1 = new SaleAmount();
                    saleAmount1.setDate(new Date());
                    saleAmount1.setId(invoice1.getOrderId());
                    saleAmount1.setCrops(invoice1.getProductName());
                    saleAmount1.setAmount(invoice1.getProductAmount());
                    saleAmount1.setIsDeleted(0);
                    saleAmountRepository.save(saleAmount);
                    saleAmountRepository.save(saleAmount1);

                    FinancialForm financialForm = byId1.get();
                    financialForm.setIsDeleted(1);

                    FinancialForm financialForm1 = new FinancialForm();
                    financialForm1.setUpdateDate(new Date());
                    financialForm1.setDate(new Date());
                    financialForm1.setBuyerName(invoice1.getBuyerName());
                    financialForm1.setIsDeleted(0);
                    financialForm1.setId(invoice1.getOrderId());
                    financialForm1.setMoney(invoice1.getProductTotal());
                    financialFormRepository.save(financialForm);
                    financialFormRepository.save(financialForm1);
                }

            }else {

                //以前存在
                Optional<SaleAmount> byId = saleAmountRepository.findById(invoice.getOrderId());
                Optional<FinancialForm> byId1 = financialFormRepository.findById(invoice.getOrderId());

                if((byId.isPresent()==true)&&(byId1.isPresent()==true)&&(invoice.getOrderType()==1)){

                    SaleAmount saleAmount = byId.get();
                    saleAmount.setIsDeleted(1);
                    saleAmount.setId(invoice1.getOrderId());
                    saleAmountRepository.save(saleAmount);
                    FinancialForm financialForm = byId1.get();
                    financialForm.setIsDeleted(1);
                    financialForm.setId(invoice1.getOrderId());
                    financialFormRepository.save(financialForm);
                }
            }
        }
        Optional<Invoice> byId = invoiceRepository.findById(invoice.getOrderId());
        Invoice invoice2 = byId.get();
        invoice2.setStatus(2);


        invoiceRepository.save(invoice2);
        invoiceRepository.save(invoice1);
        return YunResult.createBySuccess("修改成功！");
    }


//    @RequestMapping(value = "/update",method = RequestMethod.POST)
//    public YunResult update(@RequestBody Invoice invoice){
//
//        if ((!invoice.getOrderId().equals(""))){
//            if(invoice.getOrderType()==0){
//
//                Optional<SaleAmount> byId = saleAmountRepository.findById(invoice.getOrderId());
//                Optional<FinancialForm> byId1 = financialFormRepository.findById(invoice.getOrderId());
//
//                if ((byId.isPresent()==false)||(byId1.isPresent()==false)){
//                    SaleAmount saleAmount = new SaleAmount();
//                    saleAmount.setId(invoice.getOrderId());
//                    saleAmount.setIsDeleted(0);
//                    saleAmount.setDate(new Date());
//                    saleAmount.setUpdateDate(new Date());
//                    saleAmount.setCrops(invoice.getProductName());
//                    saleAmount.setAmount(invoice.getProductAmount());
//
//                    saleAmountRepository.save(saleAmount);
//
//                    FinancialForm financialForm = new FinancialForm();
//                    financialForm.setIsDeleted(0);
//                    financialForm.setUpdateDate(new Date());
//                    financialForm.setDate(new Date());
//                    financialForm.setBuyerName(invoice.getBuyerName());
//                    financialForm.setId(invoice.getOrderId());
//                    financialForm.setMoney(invoice.getProductTotal());
//                    financialFormRepository.save(financialForm);
//                }else {
//                    SaleAmount saleAmount = byId.get();
//                    saleAmount.setUpdateDate(new Date());
//
//                    saleAmount.setCrops(invoice.getProductName());
//                    saleAmount.setAmount(invoice.getProductAmount());
//                    saleAmount.setIsDeleted(0);
//                    saleAmountRepository.save(saleAmount);
//
//                    FinancialForm financialForm = byId1.get();
//                    financialForm.setUpdateDate(new Date());
//                    financialForm.setIsDeleted(0);
//                    financialForm.setMoney(invoice.getProductTotal());
//                    financialFormRepository.save(financialForm);
//                }
//
//            }else {
//
//                Optional<SaleAmount> byId = saleAmountRepository.findById(invoice.getOrderId());
//                Optional<FinancialForm> byId1 = financialFormRepository.findById(invoice.getOrderId());
//
//                if((byId.isPresent()==true)&&(byId1.isPresent()==true)&&(invoice.getOrderType()==1)){
//                    SaleAmount saleAmount = byId.get();
//                    saleAmount.setIsDeleted(1);
//                    saleAmountRepository.save(saleAmount);
//                    FinancialForm financialForm = byId1.get();
//                    financialForm.setIsDeleted(1);
//                    financialFormRepository.save(financialForm);
//                }
//
//            }
//
//        }
//        invoice.setOrderUpdateTime(new Date());
//        invoiceRepository.save(invoice);
//        return YunResult.createBySuccess("修改成功！");
//    }

//
//    @RequestMapping(value = "/traceByInvoice",method = RequestMethod.GET)
//    public TraceResult traceByInvoice(@RequestParam String invoiceId){
//
//
//        Optional<Invoice> byId = invoiceRepository.findById(invoiceId);
//        Invoice invoice = byId.get();
//        String batch = invoice.getBatch();
////        String batch = trace.getBatch();
//        FarmlandRegion farmlandRegion = invoice.getFarmlandRegion();
////        FarmlandRegion farmlandRegion = trace.getFarmlandRegion();
//        List<Work> workList = farmlandRegion.getWorkList();
//        for(int i = 0 ; i < workList.size() ; i++) {
//            String batch1 = workList.get(i).getBatch();
//            if (!batch1.equals(batch)){
//                System.out.println(workList.get(i).getBatch());
//                workList.remove(i);
//
//            }
//        }
//        Collections.sort(workList, new Comparator<Work>() {
//            @Override
//            public int compare(Work o1, Work o2) {
//                try {
//                    if (o1.getDate().getTime()>o2.getDate().getTime()){
//                        return 1;
//                    }else if (o1.getDate().getTime() < o2.getDate().getTime()){
//                        return  -1;
//                    }else  {
//                        return 0;
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                return 0;
//            }
//        });
//
//        TraceResult traceResult = new TraceResult();
//        traceResult.setInvoice(invoice);
//        traceResult.setWorkList(workList);
//        traceResult.setFarmlandRegion(farmlandRegion);
//
//
//        return traceResult;
//
//    }
//


    @RequestMapping(value = "getFarmlandRegionId",method = RequestMethod.GET)
    public YunResult getFarmlandRegionId(){

        List<FarmlandRegion> all = farmlandRegionRepository.findAll();

        for (int i=0;i<all.size();i++){
            if(all.get(i).getIsDeleted()==1){
                all.remove(i);
                i--;
            }
        }
        return YunResult.createBySuccess(all);

    }

   @Autowired
   EnterprisesRepository enterprisesRepository;

    @RequestMapping(value = "/getEnterprise",method = RequestMethod.GET)
    public YunResult getEnterprise(){

        List<Enterprise> all = enterprisesRepository.findAll();
        for (int i=0;i<all.size();i++){
            if(all.get(i).getIsDeleted()==1){
                all.remove(i);
                i--;
            }
        }
        return YunResult.createBySuccess(all);
    }





}
