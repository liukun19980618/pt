package com.pt.ptmanor.service.painting;

import org.springframework.data.domain.Page;

public interface BatchService {

    Page list(int pageNum, int pageRow);
}
