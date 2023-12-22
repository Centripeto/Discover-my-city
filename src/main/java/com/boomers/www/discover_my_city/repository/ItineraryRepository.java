package com.boomers.www.discover_my_city.repository;

import com.boomers.www.discover_my_city.model.Itinerary;
import com.boomers.www.discover_my_city.persistance.repository.MongoItineraryRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ItineraryRepository {
  private final MongoItineraryRepository mongoItineraryRepository;

  public ItineraryRepository(MongoItineraryRepository mongoItineraryRepository) {
    this.mongoItineraryRepository = mongoItineraryRepository;
  }

  public List<Itinerary> readAll() {
    return this.mongoItineraryRepository.findAllDeep();
  }

  public Itinerary create(Itinerary itinerary) {
    return this.save(itinerary);
  }

  private Itinerary save(Itinerary itinerary) {
    com.boomers.www.discover_my_city.persistance.model.Itinerary mongoItinerary =
        new com.boomers.www.discover_my_city.persistance.model.Itinerary();
    mongoItinerary.setId(itinerary.getId());
    mongoItinerary.setDescription(itinerary.getDescription());
    mongoItinerary.setName(itinerary.getName());
    mongoItinerary.setStatus(itinerary.getStatus());
    mongoItinerary.setPois(
        itinerary.getPois().stream().map(poi -> poi.getId()).collect(Collectors.toList()));
    mongoItinerary.setTemporary(itinerary.isTemporary());
    mongoItinerary.setStartDate(itinerary.getStartDate());
    mongoItinerary.setEndDate(itinerary.getEndDate());
    this.mongoItineraryRepository.save(mongoItinerary);
    return itinerary;
  }
}
