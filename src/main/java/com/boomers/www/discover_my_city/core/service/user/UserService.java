package com.boomers.www.discover_my_city.core.service.user;

import com.boomers.www.discover_my_city.core.exception.AlreadyExistsException;
import com.boomers.www.discover_my_city.core.exception.NotFoundException;
import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.model.municipality.UserMunicipality;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.MunicipalityRepository;
import com.boomers.www.discover_my_city.core.repository.UserMunicipalityRepository;
import com.boomers.www.discover_my_city.core.repository.UserRepository;
import com.boomers.www.discover_my_city.core.service.PasswordEncoder;
import com.boomers.www.discover_my_city.core.service.municipality.behaviour.connectUser.ConnectUserBehaviour;
import com.boomers.www.discover_my_city.core.service.user.behaviour.create.CreateUserBehaviour;

import java.util.Optional;

public class UserService {
  private final UserRepository userRepository;
  private final MunicipalityRepository municipalityRepository;
  private final UserMunicipalityRepository userMunicipalityRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(
      UserRepository userRepository,
      MunicipalityRepository municipalityRepository,
      UserMunicipalityRepository userMunicipalityRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.municipalityRepository = municipalityRepository;
    this.userMunicipalityRepository = userMunicipalityRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User createUser(CreateUserBehaviour userBehaviour, User user)
      throws UnauthorizedException {
    validate(user);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userBehaviour.create(user, userRepository);
  }

  public UserMunicipality connectUserToMunicipality(
      ConnectUserBehaviour connectUserBehaviour, Integer userId, Integer municipalityId)
      throws UnauthorizedException, NotFoundException, AlreadyExistsException {
    User user =
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
    Municipality municipality =
        municipalityRepository
            .findById(municipalityId)
            .orElseThrow(() -> new NotFoundException("User not found"));

    Optional<UserMunicipality> userMunicipality = userMunicipalityRepository.findByUser(user);
    if (userMunicipality.isPresent()) {
      throw new AlreadyExistsException();
    }

    return connectUserBehaviour.connectUser(user, municipality, userMunicipalityRepository);
  }

  private void validate(User user) {
    // TODO validation
  }
}
