package com.pt.ptmanor.service;

import org.springframework.data.domain.Page;

public interface EnterpriseService {

    Page list(int pageNum, int pageRow);

    Page findByMany(String enterpriseName, Integer pageNum, Integer pageRow);

//    List<Enterprise> all();
}
