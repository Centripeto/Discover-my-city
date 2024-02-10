package com.boomers.www.discover_my_city.core.repository;

import com.boomers.www.discover_my_city.core.model.municipality.Municipality;

public interface MunicipalityRepository {
  Municipality save(Municipality municipality);
}
