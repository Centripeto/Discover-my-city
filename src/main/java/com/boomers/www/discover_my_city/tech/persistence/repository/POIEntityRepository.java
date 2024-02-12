package com.boomers.www.discover_my_city.tech.persistence.repository;

import com.boomers.www.discover_my_city.tech.persistence.entity.POIEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface POIEntityRepository
    extends JpaRepository<POIEntity, Integer>, JpaSpecificationExecutor<POIEntity> {
  Page<POIEntity> findAll(Specification<POIEntity> spec, Pageable pageable);
}
