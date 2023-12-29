package com.boomers.www.discover_my_city.service;

import com.boomers.www.discover_my_city.model.Itinerary;
import com.boomers.www.discover_my_city.model.Status;
import com.boomers.www.discover_my_city.model.User;
import com.boomers.www.discover_my_city.repository.ItineraryRepository;

import java.util.List;

public class ItineraryService {
  private final ItineraryRepository itineraryRepository;

  public ItineraryService(ItineraryRepository itineraryRepository) {
    this.itineraryRepository = itineraryRepository;
  }

  public Itinerary create(Itinerary itinerary, User user) {
    switch (user.getRole()) {
      case CONTRIBUTOR:
        itinerary.setStatus(Status.IN_APPROVAL);
        break;
      case CURATOR:
      case AUTH_CONTRIBUTOR:
        itinerary.setStatus(Status.APPROVED);
        break;
      default:
        throw new RuntimeException("Not Authorized");
    }

    return this.itineraryRepository.create(itinerary);
  }

  public List<Itinerary> readAll() {
    return this.itineraryRepository.readAll();
  }
}
