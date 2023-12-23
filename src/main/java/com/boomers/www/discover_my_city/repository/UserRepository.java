package com.boomers.www.discover_my_city.repository;

import com.boomers.www.discover_my_city.model.Contributor;
import com.boomers.www.discover_my_city.model.User;
import com.boomers.www.discover_my_city.persistance.repository.MongoUserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {
  private final MongoUserRepository mongoUserRepository;

  public UserRepository(MongoUserRepository mongoUserRepository) {
    this.mongoUserRepository = mongoUserRepository;
  }

  public User create(User user) {
    return mapToModel(this.mongoUserRepository.save(mapToMongo(user)));
  }

  public List<User> readAll() {
    return this.mongoUserRepository.findAll().stream().map(this::mapToModel).collect(Collectors.toList());
  }

  private User mapToModel(com.boomers.www.discover_my_city.persistance.model.User user) {
    switch (user.getRole()) {
      case CONTRIBUTOR:
        return new Contributor(user.getId(), user.getName(), user.getSurname(), user.getEmail());
      default:
        throw new RuntimeException("Not yet implemented");
    }
  }

  private com.boomers.www.discover_my_city.persistance.model.User mapToMongo(User user) {
    return new com.boomers.www.discover_my_city.persistance.model.User(
        user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getRole());
  }
}
