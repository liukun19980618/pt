package com.pt.ptmanor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pt.ptmanor.pojo.Charts;

import java.util.Map;

public interface ChartsService  extends IService<Charts> {

    Map getList();

    Map getFinancialList(String year);

    Map financialFindList(String year, String buyerName);

    Map financialByBuyerNameAndProductName(String year, String buyerName, String productName);



}
