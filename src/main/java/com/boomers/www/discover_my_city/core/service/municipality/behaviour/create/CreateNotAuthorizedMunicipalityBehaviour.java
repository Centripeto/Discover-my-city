package com.boomers.www.discover_my_city.core.service.municipality.behaviour.create;

import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.repository.MunicipalityRepository;

public class CreateNotAuthorizedMunicipalityBehaviour implements CreateMunicipalityBehaviour {
  @Override
  public Municipality create(
      Municipality municipality, MunicipalityRepository municipalityRepository)
      throws UnauthorizedException {
    throw new UnauthorizedException();
  }
}
