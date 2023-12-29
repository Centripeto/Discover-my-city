package com.boomers.www.discover_my_city.persistance.repository;

import com.boomers.www.discover_my_city.persistance.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepository extends MongoRepository<User, String> {}
