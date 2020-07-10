package com.pt.ptportal.dao;


import com.pt.ptportal.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface AnnouncementDao extends JpaRepository<Announcement, Integer>, JpaSpecificationExecutor<Announcement> {
    List<Announcement> findAllById(Integer id);
      Optional<Announcement> findById(Integer id);

}
