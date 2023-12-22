package com.boomers.www.discover_my_city.service;

import com.boomers.www.discover_my_city.model.Itinerary;
import com.boomers.www.discover_my_city.repository.ItineraryRepository;

import java.util.List;

public class ItineraryService {
  private final ItineraryRepository itineraryRepository;

  public ItineraryService(ItineraryRepository itineraryRepository) {
    this.itineraryRepository = itineraryRepository;
  }

  public Itinerary create(Itinerary itinerary) {
    return this.itineraryRepository.create(itinerary);
  }

  public List<Itinerary> readAll() {
    return this.itineraryRepository.readAll();
  }
}
