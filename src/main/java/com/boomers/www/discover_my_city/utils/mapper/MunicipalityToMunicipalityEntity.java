package com.boomers.www.discover_my_city.utils.mapper;

import com.boomers.www.discover_my_city.core.model.municipality.AreaPathImpl;
import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.model.poi.Coordinate;
import com.boomers.www.discover_my_city.tech.persistence.entity.EdgeEntity;
import com.boomers.www.discover_my_city.tech.persistence.entity.MunicipalityEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MunicipalityToMunicipalityEntity implements Mapper<Municipality, MunicipalityEntity> {
  @Override
  public Municipality from(MunicipalityEntity model) {
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
  public MunicipalityEntity to(Municipality model) {
    if (Objects.isNull(model)) return null;
    MunicipalityEntity municipality = new MunicipalityEntity();
    municipality.setName(model.getName());
    municipality.setId(model.getId());
    municipality.setDescription(model.getDescription());
    if (!Objects.isNull(model.getArea())) {
      municipality.setEdges(new ArrayList<>());
      for (int i = 0; i < model.getArea().getEdges().size(); i++) {
        EdgeEntity edge = new EdgeEntity();
        edge.setOrder(i);
        edge.setLatitude(model.getArea().getEdges().get(i).getLatitude());
        edge.setLongitude(model.getArea().getEdges().get(i).getLongitude());
        edge.setMunicipality(municipality);
        municipality.getEdges().add(edge);
      }
    }
    return municipality;
  }
}
