package com.pt.ptmanor.service.painting;

import org.springframework.data.domain.Page;

public interface CropsService {
    Page list(int pageNum, int pageRow);

    Page findByMany(String name, Integer pageNum, Integer pageRow);
}
