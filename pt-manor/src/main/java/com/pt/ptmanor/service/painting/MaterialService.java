package com.pt.ptmanor.service.painting;

import org.springframework.data.domain.Page;

public interface MaterialService {

    Page list(int pageNum, int pageRow);

    Page findByMany(String materialName, Integer pageNum, Integer pageRow);
}
