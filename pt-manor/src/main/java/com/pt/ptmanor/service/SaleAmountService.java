package com.pt.ptmanor.service;

import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.Map;

public interface SaleAmountService {

    Page list(int pageNum, int pageRow);

    Page findByMany(String crops, Date stime, Date etime, Integer pageNum, Integer pageRow);

    Map chart(String year);

    Map findChart(String year, String crops, String buyerName);
}
