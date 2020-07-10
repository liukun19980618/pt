package com.pt.ptmanor.service.painting;

import org.springframework.data.domain.Page;

public interface FarmlandRegionService {

    Page list(int pageNum, int pageRow);

//    findByMany(farmlandRegionId, farmlandRegionPrincipal, farmlandRegionType, farmlandRegionExplain, farmlandLocationName, pageNum, pageRow);

    Page findByMany(String farmlandRegionId, String farmlandRegionPrincipal, String farmlandRegionType, String farmlandRegionExplain, String farmlandLocationName, Integer pageNum, Integer pageRow);
}
