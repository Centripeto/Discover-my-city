package com.boomers.www.discover_my_city.tech.persistence.repository;

import com.boomers.www.discover_my_city.tech.persistence.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenEntityRepository extends JpaRepository<TokenEntity, Integer> {

    @Query(value = """
      select t from TokenEntity t inner join UserEntity u\s
      on t.user.username = u.username\s
      where u.username = :username and (t.expired = false or t.revoked = false)\s
      """)
    List<TokenEntity> findAllValidTokenByUser(String username);

    @Query(value = """
      select t from TokenEntity t inner join UserEntity u\s
      on t.user.username = u.username\s
      where t.token in :tokens\s
      """)
    List<TokenEntity> findAllTokenByTokens(List<String> tokens);

    Optional<TokenEntity> findByToken(String token);
}