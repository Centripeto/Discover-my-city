package com.boomers.www.discover_my_city.tech.persistence.repository;

import com.boomers.www.discover_my_city.tech.persistence.entity.UserMunicipalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMunicipalityEntityRepository
    extends JpaRepository<UserMunicipalityEntity, Integer> {
  Optional<UserMunicipalityEntity> findByUserId(Integer id);
}
