package com.boomers.www.discover_my_city.core.repository;

import com.boomers.www.discover_my_city.core.model.municipality.Municipality;

import java.util.Optional;

public interface MunicipalityRepository {
  Municipality save(Municipality municipality);

  Optional<Municipality> findById(Integer municipalityId);
}
