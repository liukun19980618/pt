package com.pt.ptmanor.service.painting;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pt.ptmanor.pojo.painting.Production;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.Map;

public interface ProductionService extends IService<Production> {

    Page list(int pageNum, int pageRow);


//    Map getList();

    Page productList(int pageNum, int pageRow);


    Page saleList(int pageNum, int pageRow);

    Map chart(String year);

    Map findChart(String year, String crops);


    Page findByMany(String crops, Date stime, Date etime, Integer pageNum, Integer pageRow);
}
