package com.boomers.www.discover_my_city.tech.persistence.repository;

import com.boomers.www.discover_my_city.tech.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
  Optional<UserEntity> findByUsernameOrEmail(String username, String email);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
