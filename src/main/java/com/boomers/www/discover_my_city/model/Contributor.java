package com.boomers.www.discover_my_city.model;

import com.boomers.www.discover_my_city.service.ItineraryService;
import com.boomers.www.discover_my_city.service.POIService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Contributor {
  private final String id;
  private String name;
  private String surname;
  private String email;
  private final POIService poiService;
  private ItineraryService itineraryService;

  public Contributor(
      String name,
      String surname,
      String email,
      POIService poiService,
      ItineraryService itineraryService) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.poiService = poiService;
    this.itineraryService = itineraryService;
  }

  public POI createPOI(String name, String description, Coordinate coordinate) {
    POI poi = new POI(name, description, coordinate, Status.IN_APPROVAL);
    return poiService.create(poi);
  }

  public Itinerary createItinerary(
      String name,
      String description,
      List<POI> pois,
      boolean temporary,
      LocalDateTime startDate,
      LocalDateTime endDate) {
    Itinerary itinerary =
        new Itinerary(name, description, pois, temporary, startDate, endDate, Status.IN_APPROVAL);
    return itineraryService.create(itinerary);
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
