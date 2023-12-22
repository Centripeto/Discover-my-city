package com.boomers.www.discover_my_city.repository;

import com.boomers.www.discover_my_city.model.POI;
import com.boomers.www.discover_my_city.persistance.repository.MongoPOIRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class POIRepository {
  private final MongoPOIRepository repository;

  public POIRepository(MongoPOIRepository repository) {
    this.repository = repository;
  }

  public POI create(POI poi) {
    return this.save(poi);
  }

  public POI update(POI poi) {
    return this.save(poi);
  }

  public List<POI> readAll() {
    return this.repository.findAll().stream()
        .map(
            mongo ->
                new POI(
                    mongo.getId(),
                    mongo.getName(),
                    mongo.getDescription(),
                    mongo.getCoordinate(),
                    mongo.getStatus()))
        .collect(Collectors.toList());
  }

  public POI readById(String id) {
    Optional<com.boomers.www.discover_my_city.persistance.model.POI> poiMongoOpt =
        this.repository.findById(id);
    if (poiMongoOpt.isEmpty()) {
      throw new RuntimeException("POI not found");
    }
    com.boomers.www.discover_my_city.persistance.model.POI poiMongo = poiMongoOpt.get();
    return new POI(
        poiMongo.getId(),
        poiMongo.getName(),
        poiMongo.getDescription(),
        poiMongo.getCoordinate(),
        poiMongo.getStatus());
  }

  private POI save(POI poi) {
    com.boomers.www.discover_my_city.persistance.model.POI poiMongo =
        new com.boomers.www.discover_my_city.persistance.model.POI();
    poiMongo.setId(poi.getId());
    poiMongo.setName(poi.getName());
    poiMongo.setDescription(poi.getDescription());
    poiMongo.setStatus(poi.getStatus());
    poiMongo.setCoordinate(poi.getCoordinate());
    // TODO mapper
    this.repository.save(poiMongo);
    return poi;
  }
}
