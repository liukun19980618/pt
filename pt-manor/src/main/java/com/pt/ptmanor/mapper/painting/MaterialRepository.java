package com.pt.ptmanor.mapper.painting;


import com.pt.ptmanor.pojo.painting.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material,String>, JpaSpecificationExecutor<Material> {

    Material findByMaterialName(String materialName);
}
