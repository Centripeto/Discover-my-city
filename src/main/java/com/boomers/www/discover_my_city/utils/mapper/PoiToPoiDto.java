package com.boomers.www.discover_my_city.utils.mapper;

import com.boomers.www.discover_my_city.api.dto.CoordinateDto;
import com.boomers.www.discover_my_city.api.dto.POIDto;
import com.boomers.www.discover_my_city.core.model.user.Coordinate;
import com.boomers.www.discover_my_city.core.model.user.POI;
import com.boomers.www.discover_my_city.core.model.user.Status;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PoiToPoiDto implements Mapper<POI, POIDto> {
  @Override
  public POI from(POIDto model) {
    POI poi = new POI();
    poi.setId(model.getId());
    poi.setName(model.getName());
    poi.setDescription(model.getDescription());
    poi.setCoordinate(
        new Coordinate(model.getCoordinate().getLatitude(), model.getCoordinate().getLongitude()));
    if (!Objects.isNull(model.getStatus())) poi.setStatus(Status.valueOf(model.getStatus()));
    return poi;
  }

  @Override
  public POIDto to(POI model) {
    POIDto poi = new POIDto();
    poi.setId(model.getId());
    poi.setName(model.getName());
    poi.setDescription(model.getDescription());
    poi.setCoordinate(
        new CoordinateDto(
            model.getCoordinate().getLatitude(), model.getCoordinate().getLongitude()));
    poi.setStatus(model.getStatus().toString());
    return poi;
  }
}
