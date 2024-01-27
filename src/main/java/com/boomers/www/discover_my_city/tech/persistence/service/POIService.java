package com.boomers.www.discover_my_city.tech.persistence.service;

import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.repository.POIRepository;
import com.boomers.www.discover_my_city.tech.persistence.entity.POIEntity;
import com.boomers.www.discover_my_city.tech.persistence.entity.UserEntity;
import com.boomers.www.discover_my_city.tech.persistence.repository.POIEntityRepository;
import com.boomers.www.discover_my_city.tech.persistence.repository.UserEntityRepository;
import com.boomers.www.discover_my_city.utils.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class POIService implements POIRepository {
  private final POIEntityRepository poiEntityRepository;
  private final UserEntityRepository userRepository;
  private final Mapper<POI, POIEntity> poiToPoiEntityMapper;

  public POIService(
      POIEntityRepository poiEntityRepository,
      UserEntityRepository userRepository,
      Mapper<POI, POIEntity> poiToPoiEntityMapper) {
    this.poiEntityRepository = poiEntityRepository;
    this.userRepository = userRepository;
    this.poiToPoiEntityMapper = poiToPoiEntityMapper;
  }

  @Override
  public POI save(POI poi) {
    return poiToPoiEntityMapper.from(poiEntityRepository.save(attachCreatorAndApprover(poi)));
  }

  private POIEntity attachCreatorAndApprover(POI poi) {
    POIEntity entity = poiToPoiEntityMapper.to(poi);
    UserEntity creator =
        userRepository
            .findByUsernameOrEmail(
                entity.getCreator().getUsername(), entity.getCreator().getEmail())
            .orElse(null);
    UserEntity approver = null;
    if (!Objects.isNull(entity.getApprover())) {
      approver =
          userRepository
              .findByUsernameOrEmail(
                  entity.getApprover().getUsername(), entity.getApprover().getEmail())
              .orElse(null);
    }
    entity.setCreator(creator);
    entity.setApprover(approver);
    return entity;
  }
}
