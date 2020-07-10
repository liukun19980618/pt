package com.pt.ptmanor.service.painting;

import org.springframework.data.domain.Page;

public interface FarmlandLocationService {

    Page list(int pageNum, int pageRow);


    Page findByMany(String farmlandLocationId, String farmlandLocationName, String farmlandPrincipal,
                    String farmlandArea, String farmlandExplain, Integer pageNum, Integer pageRow);


}
