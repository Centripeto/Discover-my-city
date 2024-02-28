package com.boomers.www.discover_my_city.utils.mapper;

import com.boomers.www.discover_my_city.api.dto.CoordinateDto;
import com.boomers.www.discover_my_city.api.dto.MunicipalityDto;
import com.boomers.www.discover_my_city.core.model.municipality.AreaPathImpl;
import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.model.poi.Coordinate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MunicipalityToMunicipalityDto implements Mapper<Municipality, MunicipalityDto> {
  @Override
  public Municipality from(MunicipalityDto model) {
    if (Objects.isNull(model)) return null;
    Municipality municipality = new Municipality();
    municipality.setName(model.getName());
    municipality.setId(model.getId());
    municipality.setDescription(model.getDescription());
    if (!Objects.isNull(model.getEdges())) {
      municipality.setArea(
          new AreaPathImpl(
              model.getEdges().stream()
                  .map(
                      el -> {
                        Coordinate coordinate = new Coordinate();
                        coordinate.setLatitude(el.getLatitude());
                        coordinate.setLongitude(el.getLongitude());
                        return coordinate;
                      })
                  .collect(Collectors.toList())));
    }
    return municipality;
  }

  @Override
  public MunicipalityDto to(Municipality model) {
    if (Objects.isNull(model)) return null;
    MunicipalityDto municipality = new MunicipalityDto();
    municipality.setName(model.getName());
    municipality.setId(model.getId());
    municipality.setDescription(model.getDescription());
    if (!Objects.isNull(model.getArea())) {
      municipality.setEdges(
          model.getArea().getEdges().stream()
              .map(
                  coordinate ->
                      new CoordinateDto(coordinate.getLatitude(), coordinate.getLongitude()))
              .collect(Collectors.toList()));
    }
    return municipality;
  }
}
