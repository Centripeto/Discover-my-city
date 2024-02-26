package com.boomers.www.discover_my_city.core.service.municipality.behaviour.connectUser;

import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.municipality.UserMunicipality;
import com.boomers.www.discover_my_city.core.repository.UserMunicipalityRepository;

public class ConnectUserSimpleBehaviour implements ConnectUserBehaviour {
  @Override
  public UserMunicipality connectUser(
      UserMunicipality userMunicipality, UserMunicipalityRepository userMunicipalityRepository)
      throws UnauthorizedException {
    return userMunicipalityRepository.save(userMunicipality);
  }
}
