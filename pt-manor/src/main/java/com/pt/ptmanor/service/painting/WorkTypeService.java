package com.pt.ptmanor.service.painting;

import org.springframework.data.domain.Page;

public interface WorkTypeService {
    Page list(int pageNum, int pageRow);
}
