package com.pt.ptmanor.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pt.ptmanor.mapper.ChartsMapper;
import com.pt.ptmanor.mapper.FinancialFormRepository;
import com.pt.ptmanor.pojo.Charts;
import com.pt.ptmanor.pojo.FinancialForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChartsServiceImpl extends ServiceImpl<ChartsMapper, Charts> implements  ChartsService {

    @Autowired
    private  ChartsMapper chartsMapper;

    @Autowired
    private FinancialFormRepository financialFormRepository;



    @Override
    public Map getList() {
        //女性的人员
        List<Charts> wList = chartsMapper.getListBySex("女");
        // 男性人员
        List<Charts> mList = chartsMapper.getListBySex("男");

        //女性每月的人数
        List<Integer> wListForCount = new ArrayList<>();
        //男性每月的人数
        List<Integer> mListForCount = new ArrayList<>();

        //每月总人数
        List<Integer> sumListForCount = new ArrayList<>();


        for (Charts wchart:wList) {
            for (Charts mchart:mList) {
                if(wchart.getTime().equals(mchart.getTime())){
                    wListForCount.add(wchart.getCount());
                    mListForCount.add(mchart.getCount());
                    sumListForCount.add(wchart.getCount()+mchart.getCount());
                }
            }
        }

        Map map = new HashMap();
        map.put("wListForCount",wListForCount);
        map.put("mListForCount",mListForCount);
        map.put("sumListForCount",sumListForCount);

        return map;
    }

    @Override
    public Map getFinancialList(String year) {

        List<FinancialForm> byIsDeleted = financialFormRepository.findByIsDeletedAndYear(0,year);
        List<Float> money = new ArrayList<>();
        int i;
        for (i=0;i<12;i++){
            Float monthMoney = 0.0f;
            for (FinancialForm financialForm:byIsDeleted){

                Calendar ca = Calendar.getInstance();
                ca.setTime(financialForm.getDate());

                int month = ca.get(Calendar.MONTH);//第几个月
                if (month==i){
                    monthMoney = monthMoney+financialForm.getMoney();
                }
            }
            money.add(monthMoney);
        }
        Map map= new HashMap();
        map.put("moneyList",money);
        return map;
    }


    @Override
    public Map financialFindList( String year,String buyerName) {

        List<FinancialForm> byIsDeletedAndAndBuyerName = financialFormRepository.findByIsDeletedAndAndBuyerNameAndYear(0, buyerName,year);
        List<Float> money = new ArrayList<>();
        int i;
        for (i=0;i<12;i++){
            Float monthMoney = 0.0f;
            for (FinancialForm financialForm:byIsDeletedAndAndBuyerName){

                Calendar ca = Calendar.getInstance();
                ca.setTime(financialForm.getDate());

                int month = ca.get(Calendar.MONTH);//第几个月
                if (month==i){
                    monthMoney = monthMoney+financialForm.getMoney();
                }
            }
            money.add(monthMoney);
        }
        Map map= new HashMap();
        map.put("moneyList",money);

        return map;
    }

    @Override
    public Map financialByBuyerNameAndProductName(String year, String buyerName, String productName) {
        List<FinancialForm> byIsDeletedAndAndBuyerNameAndProductName = financialFormRepository.findByIsDeletedAndAndBuyerNameAndProductNameAndYear(0, buyerName, productName,year);
        List<Float> money = new ArrayList<>();

        int i;
        for (i=0;i<12;i++){
            Float monthMoney = 0.0f;
            for (FinancialForm financialForm:byIsDeletedAndAndBuyerNameAndProductName){

                Calendar ca = Calendar.getInstance();
                ca.setTime(financialForm.getDate());

                int month = ca.get(Calendar.MONTH);//第几个月
                if (month==i){
                    monthMoney = monthMoney+financialForm.getMoney();
                }
            }
            money.add(monthMoney);
        }
        Map map= new HashMap();
        map.put("moneyList",money);

        return map;
    }



}
