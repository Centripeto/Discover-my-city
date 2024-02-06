package com.boomers.www.discover_my_city.utils.mapper;

import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.poi.Status;
import com.boomers.www.discover_my_city.core.model.user.Coordinate;
import com.boomers.www.discover_my_city.tech.persistence.entity.POIEntity;
import com.boomers.www.discover_my_city.tech.persistence.entity.POIStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PoiToPoiEntity implements Mapper<POI, POIEntity> {

  private final UserToUserEntity userMapper;

  @Autowired
  public PoiToPoiEntity(UserToUserEntity userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public POI from(POIEntity model) {
    POI.Builder builder =
        POI.builder()
            .addId(model.getId())
            .addName(model.getName())
            .addDescription(model.getDescription())
            .addCoordinate(new Coordinate(model.getLatitude(), model.getLongitude()))
            .addCreator(userMapper.from(model.getCreator()))
            .addStatus(Status.valueOf(model.getStatus().toString()));
    if (!Objects.isNull(model.getApprover())) {
      builder.addApprover(userMapper.from(model.getApprover()));
    }
    return builder.build();
  }

  @Override
  public POIEntity to(POI model) {
    POIEntity poi = new POIEntity();
    poi.setId(model.getId());
    poi.setDescription(model.getDescription());
    poi.setLatitude(model.getCoordinate().getLatitude());
    poi.setLongitude(model.getCoordinate().getLongitude());
    poi.setStatus(POIStatus.valueOf(model.getStatus().toString()));
    poi.setName(model.getName());
    poi.setCreator(userMapper.to(model.getCreator()));
    if (!Objects.isNull(model.getApprover())) {
      poi.setApprover(userMapper.to(model.getApprover()));
    }
    return poi;
  }
}
