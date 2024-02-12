package com.boomers.www.discover_my_city.core.handler;

import com.boomers.www.discover_my_city.core.exception.AlreadyExistsException;
import com.boomers.www.discover_my_city.core.exception.NotFoundException;
import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.municipality.UserMunicipality;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.MunicipalityRepository;
import com.boomers.www.discover_my_city.core.repository.UserMunicipalityRepository;
import com.boomers.www.discover_my_city.core.repository.UserRepository;
import com.boomers.www.discover_my_city.core.service.PasswordEncoder;
import com.boomers.www.discover_my_city.core.service.municipality.behaviour.connectUser.ConnectUserBehaviour;
import com.boomers.www.discover_my_city.core.service.municipality.behaviour.connectUser.ConnectUserNotAuthorizedBehaviour;
import com.boomers.www.discover_my_city.core.service.municipality.behaviour.connectUser.ConnectUserSimpleBehaviour;
import com.boomers.www.discover_my_city.core.service.user.UserService;
import com.boomers.www.discover_my_city.core.service.user.behaviour.create.CreateAllKindsOfUserBehaviour;
import com.boomers.www.discover_my_city.core.service.user.behaviour.create.CreateContributorUserBehaviour;
import com.boomers.www.discover_my_city.core.service.user.behaviour.create.CreateNotAuthorizedUserBehaviour;
import com.boomers.www.discover_my_city.core.service.user.behaviour.create.CreateUserBehaviour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {
  private final UserService userService;

  @Autowired
  public UserFacade (UserRepository userRepository, MunicipalityRepository municipalityRepository, UserMunicipalityRepository userMunicipalityRepository, PasswordEncoder passwordEncoder){
      userService = new UserService(userRepository, municipalityRepository, userMunicipalityRepository, passwordEncoder);
  }

  public User createUser(User creator, User toCreate) throws UnauthorizedException {
      return userService.createUser(createUserBehaviour(creator), toCreate);
  }

  public UserMunicipality connectUserToMunicipality(User user, Integer userId, Integer municipalityId) throws AlreadyExistsException, UnauthorizedException, NotFoundException {
      return userService.connectUserToMunicipality(connectUserBehaviour(user), userId, municipalityId);
  }

    private CreateUserBehaviour createUserBehaviour(User user) {
        return switch(user.getRole()) {
            case CURATORE -> new CreateContributorUserBehaviour();
            case  ADMIN ->  new CreateAllKindsOfUserBehaviour();
            default -> new CreateNotAuthorizedUserBehaviour();
        };
    }

    private ConnectUserBehaviour connectUserBehaviour(User user) {
      return switch (user.getRole()) {
          case ADMIN -> new ConnectUserSimpleBehaviour();
          default -> new ConnectUserNotAuthorizedBehaviour();
      };
    }
}
