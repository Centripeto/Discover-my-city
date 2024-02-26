package com.boomers.www.discover_my_city.tech.persistence.repository;

import com.boomers.www.discover_my_city.tech.persistence.entity.EdgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EdgeEntityRepository extends JpaRepository<EdgeEntity, Integer> {
  List<EdgeEntity> findByMunicipalityId(Integer municipality);
}
