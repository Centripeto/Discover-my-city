package com.boomers.www.discover_my_city.core.repository;

import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.model.municipality.UserMunicipality;
import com.boomers.www.discover_my_city.core.model.user.User;

import java.util.Optional;

public interface UserMunicipalityRepository {
  UserMunicipality save(User user, Municipality municipality);

  Optional<UserMunicipality> findByUser(User user);
}
