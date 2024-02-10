package com.boomers.www.discover_my_city.core.handler;

import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.UserRepository;
import com.boomers.www.discover_my_city.core.service.PasswordEncoder;
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
  public UserFacade (UserRepository userRepository, PasswordEncoder passwordEncoder){
      userService = new UserService(userRepository, passwordEncoder);
  }

  public User createUser(User creator, User toCreate) throws UnauthorizedException {
      return userService.createUser(createUserBehaviour(creator), toCreate);
  }

    private CreateUserBehaviour createUserBehaviour(User user) {
        return switch(user.getRole()) {
            case CURATORE -> new CreateContributorUserBehaviour();
            case  ADMIN ->  new CreateAllKindsOfUserBehaviour();
            default -> new CreateNotAuthorizedUserBehaviour();
        };
    }
}
