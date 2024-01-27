package com.boomers.www.discover_my_city.utils.mapper;

import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.user.Coordinate;
import com.boomers.www.discover_my_city.core.model.user.Status;
import com.boomers.www.discover_my_city.tech.persistence.entity.POIEntity;
import org.springframework.stereotype.Component;

@Component
public class PoiToPoiEntity implements Mapper<POI, POIEntity> {
  @Override
  public POI from(POIEntity model) {
    POI poi = new POI();
    poi.setId(model.getId());
    poi.setDescription(model.getDescription());
    poi.setCoordinate(new Coordinate(model.getLatitude(), model.getLongitude()));
    poi.setStatus(Status.valueOf(model.getStatus()));
    poi.setName(model.getName());
    return poi;
  }

  @Override
  public POIEntity to(POI model) {
    POIEntity poi = new POIEntity();
    poi.setId(model.getId());
    poi.setDescription(model.getDescription());
    poi.setLatitude(model.getCoordinate().getLatitude());
    poi.setLongitude(model.getCoordinate().getLongitude());
    poi.setStatus(model.getStatus().toString());
    poi.setName(model.getName());
    return poi;
  }
}
