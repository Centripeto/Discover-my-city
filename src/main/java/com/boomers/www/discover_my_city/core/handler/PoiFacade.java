package com.boomers.www.discover_my_city.core.handler;

import com.boomers.www.discover_my_city.api.dto.Paged;
import com.boomers.www.discover_my_city.core.exception.NotFoundException;
import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.poi.POIRequest;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.MunicipalityRepository;
import com.boomers.www.discover_my_city.core.repository.POIRepository;
import com.boomers.www.discover_my_city.core.repository.UserMunicipalityRepository;
import com.boomers.www.discover_my_city.core.repository.UserRepository;
import com.boomers.www.discover_my_city.core.service.PasswordEncoder;
import com.boomers.www.discover_my_city.core.service.poi.POIService;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.create.CreateApprovedPoiBehaviour;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.create.CreateInApprovalPoiBehaviour;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.create.CreateNotAuthorizedPoiBehaviour;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.create.CreatePoiBehaviour;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.list.ListAllApprovedPoiAndUserInApproval;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.list.ListAllPoiBehaviour;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.list.ListOnlyApprovedPoiBehaviour;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.list.ListPoiBehaviour;
import com.boomers.www.discover_my_city.core.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PoiFacade {

  private final POIService poiService;
  private final UserService userService;

  @Autowired
  public PoiFacade(POIRepository poiRepository, UserRepository userRepository, MunicipalityRepository municipalityRepository, UserMunicipalityRepository userMunicipalityRepository, PasswordEncoder passwordEncoder) {
    this.poiService = new POIService(poiRepository);
    this.userService = new UserService(userRepository, municipalityRepository, userMunicipalityRepository, passwordEncoder);
  }

  public POI createPoi(User user, POI poi) throws UnauthorizedException {
    Municipality userMunicipality = userService.getUserMunicipality(user).orElseThrow(() -> new UnauthorizedException("L'utente non è abilitato su alcun comune"));
    if (!userMunicipality.getArea().isInBounds(poi.getCoordinate())) {
     throw new UnauthorizedException("POI creato al di fuori del proprio comune");
    }
    return poiService.createPOI(getCreatePoiStrategy(user), poi);
  }

  public POI approvePoi(User user, Integer id) throws UnauthorizedException, NotFoundException {
    return poiService.approvePoi(user, id);
  }

  public POI rejectPoi(User user, Integer id) throws UnauthorizedException, NotFoundException {
    return poiService.rejectPoi(user, id);
  }

  public Paged<POI> findPois(User user, POIRequest request) {
    return poiService.findPois(getListPoiStrategy(user), request);
  }

  private CreatePoiBehaviour getCreatePoiStrategy(User user) {
    return switch (user.getRole()) {
      case CONTRIBUTOR -> new CreateInApprovalPoiBehaviour(user);
      case CURATORE, AUTH_CONTRIBUTOR -> new CreateApprovedPoiBehaviour(user);
      case ADMIN -> new CreateNotAuthorizedPoiBehaviour();
    };
  }

  private ListPoiBehaviour getListPoiStrategy(User user) {
    if (Objects.isNull(user)) {
      return new ListOnlyApprovedPoiBehaviour();
    }
    return switch(user.getRole()) {
      case CONTRIBUTOR, AUTH_CONTRIBUTOR -> new ListAllApprovedPoiAndUserInApproval(user);
      case CURATORE, ADMIN ->  new ListAllPoiBehaviour();
    };
  }
}
