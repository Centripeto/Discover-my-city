package com.boomers.www.discover_my_city.core.service.municipality.behaviour.create;

import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.repository.MunicipalityRepository;

public class CreateSimpleMunicipalityBehaviour implements CreateMunicipalityBehaviour {

  @Override
  public Municipality create(
      Municipality municipality, MunicipalityRepository municipalityRepository)
      throws UnauthorizedException {
    return municipalityRepository.save(municipality);
  }
}
