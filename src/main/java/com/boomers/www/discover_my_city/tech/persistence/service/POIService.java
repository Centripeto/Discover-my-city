package com.boomers.www.discover_my_city.tech.persistence.service;

import com.boomers.www.discover_my_city.core.model.user.POI;
import com.boomers.www.discover_my_city.core.repository.POIRepository;
import com.boomers.www.discover_my_city.tech.persistence.entity.POIEntity;
import com.boomers.www.discover_my_city.tech.persistence.repository.POIEntityRepository;
import com.boomers.www.discover_my_city.utils.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class POIService implements POIRepository {
  private final POIEntityRepository poiEntityRepository;
  private final Mapper<POI, POIEntity> poiToPoiEntityMapper;

  public POIService(
      POIEntityRepository poiEntityRepository, Mapper<POI, POIEntity> poiToPoiEntityMapper) {
    this.poiEntityRepository = poiEntityRepository;
    this.poiToPoiEntityMapper = poiToPoiEntityMapper;
  }

  @Override
  public POI save(POI poi) {
    return poiToPoiEntityMapper.from(poiEntityRepository.save(poiToPoiEntityMapper.to(poi)));
  }
}
