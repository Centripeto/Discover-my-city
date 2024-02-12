package com.boomers.www.discover_my_city.tech.persistence.repository;

import com.boomers.www.discover_my_city.tech.persistence.entity.MunicipalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MunicipalityEntityRepository
    extends JpaRepository<MunicipalityEntity, Integer>,
        JpaSpecificationExecutor<MunicipalityEntity> {}
