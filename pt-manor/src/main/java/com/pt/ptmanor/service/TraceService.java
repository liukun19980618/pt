package com.pt.ptmanor.service;

import org.springframework.data.domain.Page;

public interface TraceService {

    Page list(int pageNum, int pageRow);
}
