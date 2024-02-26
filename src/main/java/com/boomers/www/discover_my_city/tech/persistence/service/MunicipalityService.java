package com.boomers.www.discover_my_city.tech.persistence.service;

import com.boomers.www.discover_my_city.core.model.municipality.AreaPathImpl;
import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.model.municipality.UserMunicipality;
import com.boomers.www.discover_my_city.core.model.poi.Coordinate;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.MunicipalityRepository;
import com.boomers.www.discover_my_city.core.repository.UserMunicipalityRepository;
import com.boomers.www.discover_my_city.tech.persistence.entity.EdgeEntity;
import com.boomers.www.discover_my_city.tech.persistence.entity.MunicipalityEntity;
import com.boomers.www.discover_my_city.tech.persistence.entity.UserEntity;
import com.boomers.www.discover_my_city.tech.persistence.entity.UserMunicipalityEntity;
import com.boomers.www.discover_my_city.tech.persistence.repository.EdgeEntityRepository;
import com.boomers.www.discover_my_city.tech.persistence.repository.MunicipalityEntityRepository;
import com.boomers.www.discover_my_city.tech.persistence.repository.UserEntityRepository;
import com.boomers.www.discover_my_city.tech.persistence.repository.UserMunicipalityEntityRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MunicipalityService implements MunicipalityRepository, UserMunicipalityRepository {

  private final UserEntityRepository userEntityRepository;
  private final MunicipalityEntityRepository municipalityEntityRepository;
  private final EdgeEntityRepository edgeEntityRepository;
  private final UserMunicipalityEntityRepository userMunicipalityEntityRepository;

  public MunicipalityService(
      MunicipalityEntityRepository municipalityEntityRepository,
      UserEntityRepository userEntityRepository,
      EdgeEntityRepository edgeEntityRepository,
      UserMunicipalityEntityRepository userMunicipalityEntityRepository) {
    this.userEntityRepository = userEntityRepository;
    this.municipalityEntityRepository = municipalityEntityRepository;
    this.edgeEntityRepository = edgeEntityRepository;
    this.userMunicipalityEntityRepository = userMunicipalityEntityRepository;
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

  @Override
  public Optional<Municipality> findById(Integer municipalityId) {
    Optional<MunicipalityEntity> entity = municipalityEntityRepository.findById(municipalityId);
    if (entity.isEmpty()) {
      return Optional.empty();
    }
    List<EdgeEntity> edges = edgeEntityRepository.findByMunicipalityId(entity.get().getId());
    return Optional.of(mapEntity(entity.get(), edges));
  }

  private Municipality mapEntity(MunicipalityEntity entity, List<EdgeEntity> edges) {
    Municipality municipality = new Municipality();
    municipality.setName(entity.getName());
    municipality.setId(entity.getId());
    municipality.setDescription(entity.getDescription());
    municipality.setArea(
        new AreaPathImpl(
            edges.stream()
                .map(
                    el -> {
                      Coordinate coordinate = new Coordinate();
                      coordinate.setLatitude(el.getLatitude());
                      coordinate.setLongitude(el.getLongitude());
                      return coordinate;
                    })
                .collect(Collectors.toList())));
    return municipality;
  }

  @Override
  public UserMunicipality save(UserMunicipality userMunicipality) {
    UserMunicipalityEntity entity = new UserMunicipalityEntity();
    MunicipalityEntity municipalityEntity =
        municipalityEntityRepository
            .findById(userMunicipality.getMunicipality().getId())
            .orElse(null);
    UserEntity userEntity =
        userEntityRepository.findById(userMunicipality.getMunicipality().getId()).orElse(null);
    entity.setMunicipality(municipalityEntity);
    entity.setUser(userEntity);
    return null;
  }

  @Override
  public Optional<Municipality> getUserMunicipality(User user) {
    Optional<UserMunicipalityEntity> entity =
        userMunicipalityEntityRepository.findByUserId(user.getId());
    if (entity.isEmpty()) {
      return Optional.empty();
    }
    List<EdgeEntity> edges =
        edgeEntityRepository.findByMunicipalityId(entity.get().getMunicipality().getId());
    return Optional.of(mapEntity(entity.get().getMunicipality(), edges));
  }
}
