package com.boomers.www.discover_my_city.utils.mapper;

import com.boomers.www.discover_my_city.api.dto.CoordinateDto;
import com.boomers.www.discover_my_city.api.dto.MunicipalityDto;
import com.boomers.www.discover_my_city.api.dto.POIDto;
import com.boomers.www.discover_my_city.api.dto.UserDto;
import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.model.poi.Coordinate;
import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.poi.Status;
import com.boomers.www.discover_my_city.core.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PoiToPoiDto implements Mapper<POI, POIDto> {

  private final Mapper<User, UserDto> userMapper;
  private final Mapper<Municipality, MunicipalityDto> municipalityMapper;

  @Autowired
  public PoiToPoiDto(
      Mapper<User, UserDto> userMapper, Mapper<Municipality, MunicipalityDto> municipalityMapper) {
    this.userMapper = userMapper;
    this.municipalityMapper = municipalityMapper;
  }

  @Override
  public POI from(POIDto model) {
    POI.Builder builder =
        POI.builder()
            .addId(model.getId())
            .addName(model.getName())
            .addDescription(model.getDescription())
            .addCoordinate(
                new Coordinate(
                    model.getCoordinate().getLatitude(), model.getCoordinate().getLongitude()));
    if (!Objects.isNull(model.getStatus())) {
      builder.addStatus(Status.valueOf(model.getStatus()));
    }
    if (!Objects.isNull(model.getCreator())) {
      builder.addCreator(userMapper.from(model.getCreator()));
    }
    if (!Objects.isNull(model.getApprover())) {
      builder.addApprover(userMapper.from(model.getApprover()));
    }
    if (!Objects.isNull(model.getMunicipality())) {
      builder.addMunicipality(municipalityMapper.from(model.getMunicipality()));
    }
    return builder.build();
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
    if (!Objects.isNull(model.getCreator())) {
      poi.setCreator(userMapper.to(model.getCreator()));
    }
    if (!Objects.isNull(model.getApprover())) {
      poi.setApprover(userMapper.to(model.getApprover()));
    }
    if (!Objects.isNull(model.getMunicipality())) {
      poi.setMunicipality(municipalityMapper.to(model.getMunicipality()));
    }
    return poi;
  }
}
