package com.boomers.www.discover_my_city.tech.persistence.repository;

import com.boomers.www.discover_my_city.tech.persistence.entity.POIEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface POIEntityRepository extends JpaRepository<POIEntity, Integer> {}
