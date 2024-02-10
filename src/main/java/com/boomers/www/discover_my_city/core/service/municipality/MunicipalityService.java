package com.boomers.www.discover_my_city.core.service.municipality;

import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.repository.MunicipalityRepository;
import com.boomers.www.discover_my_city.core.service.municipality.behaviour.create.CreateMunicipalityBehaviour;

public class MunicipalityService {
  private final MunicipalityRepository municipalityRepository;

  public MunicipalityService(MunicipalityRepository municipalityRepository) {
    this.municipalityRepository = municipalityRepository;
  }

  public Municipality createMunicipality(
      CreateMunicipalityBehaviour createMunicipalityStrategy, Municipality municipality)
      throws UnauthorizedException {
    return createMunicipalityStrategy.create(municipality, municipalityRepository);
  }
}
