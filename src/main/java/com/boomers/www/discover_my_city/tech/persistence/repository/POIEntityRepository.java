package com.boomers.www.discover_my_city.tech.persistence.repository;

import com.boomers.www.discover_my_city.tech.persistence.entity.POIEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface POIEntityRepository extends JpaRepository<POIEntity, Integer> {
    @Query(value = """
      select p from POIEntity p inner join UserEntity u\s
      on p.creator.username = u.username\s
      where (u.username = :username and p.status = 'IN_APPROVAL') OR p.status = 'APPROVED'\s
      """,
            countQuery = """
      select COUNT(*) from POIEntity p inner join UserEntity u\s
      on p.creator.username = u.username\s
      where (u.username = :username and p.status = 'IN_APPROVAL') OR p.status = 'APPROVED'\s
      """,
            nativeQuery = false
    )
    Page<POIEntity> getAllApprovedPoiAndUserInApproval(String username, Pageable pageable);
}
