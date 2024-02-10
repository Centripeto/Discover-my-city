package com.boomers.www.discover_my_city.core.handler;

import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.MunicipalityRepository;
import com.boomers.www.discover_my_city.core.service.municipality.MunicipalityService;
import com.boomers.www.discover_my_city.core.service.municipality.behaviour.create.CreateMunicipalityBehaviour;
import com.boomers.www.discover_my_city.core.service.municipality.behaviour.create.CreateNotAuthorizedMunicipalityBehaviour;
import com.boomers.www.discover_my_city.core.service.municipality.behaviour.create.CreateSimpleMunicipalityBehaviour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MunicipalityFacade {
  private final MunicipalityService municipalityService;

  @Autowired
  public MunicipalityFacade(MunicipalityRepository municipalityRepository) {
    municipalityService = new MunicipalityService(municipalityRepository);
  }

    public Municipality createMunicipality(User user, Municipality municipality) throws UnauthorizedException {
        return municipalityService.createMunicipality(getCreateMunicipalityStrategy(user), municipality);
    }

  private CreateMunicipalityBehaviour getCreateMunicipalityStrategy(User user) {
      return switch (user.getRole()) {
          case ADMIN -> new CreateSimpleMunicipalityBehaviour();
          default -> new CreateNotAuthorizedMunicipalityBehaviour();
      };
  }
}
