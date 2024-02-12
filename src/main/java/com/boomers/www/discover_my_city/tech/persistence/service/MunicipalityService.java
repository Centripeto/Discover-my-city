package com.boomers.www.discover_my_city.tech.persistence.service;

import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.model.poi.Coordinate;
import com.boomers.www.discover_my_city.core.repository.MunicipalityRepository;
import com.boomers.www.discover_my_city.tech.persistence.entity.EdgeEntity;
import com.boomers.www.discover_my_city.tech.persistence.entity.MunicipalityEntity;
import com.boomers.www.discover_my_city.tech.persistence.repository.EdgeEntityRepository;
import com.boomers.www.discover_my_city.tech.persistence.repository.MunicipalityEntityRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MunicipalityService implements MunicipalityRepository {

  private final MunicipalityEntityRepository municipalityEntityRepository;
  private final EdgeEntityRepository edgeEntityRepository;

  public MunicipalityService(
      MunicipalityEntityRepository municipalityEntityRepository,
      EdgeEntityRepository edgeEntityRepository) {
    this.municipalityEntityRepository = municipalityEntityRepository;
    this.edgeEntityRepository = edgeEntityRepository;
  }

  @Override
  public Municipality save(Municipality municipality) {
    MunicipalityEntity municipalityEntity = new MunicipalityEntity();
    municipalityEntity.setName(municipality.getName());
    municipalityEntity.setDescription(municipality.getDescription());
    MunicipalityEntity saved = municipalityEntityRepository.save(municipalityEntity);
    List<EdgeEntity> edges = new LinkedList<>();
    List<Coordinate> coordinates = municipality.getArea().getEdges();
    for (int i = 0; i < coordinates.size(); i++) {
      EdgeEntity edge = new EdgeEntity();
      edge.setOrder(i);
      edge.setLatitude(coordinates.get(i).getLatitude());
      edge.setLongitude(coordinates.get(i).getLongitude());
      edge.setMunicipality(saved);
      edges.add(edge);
    }
    edgeEntityRepository.saveAll(edges);
    municipality.setId(saved.getId());
    return municipality;
  }
}
