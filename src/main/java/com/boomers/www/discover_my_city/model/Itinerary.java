package com.boomers.www.discover_my_city.model;

import java.time.LocalDateTime;
import java.util.List;

public class Itinerary {
  private String id;
  private String name;
  private String description;
  private List<POI> pois;
  private boolean temporary;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Status status;

  public Itinerary() { }

  public Itinerary(
      String name,
      String description,
      List<POI> pois,
      boolean temporary,
      LocalDateTime startDate,
      LocalDateTime endDate,
      Status status) {
    this.name = name;
    this.description = description;
    this.pois = pois;
    this.temporary = temporary;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
  }

  public Itinerary(
      String id,
      String name,
      String description,
      List<POI> pois,
      boolean temporary,
      LocalDateTime startDate,
      LocalDateTime endDate,
      Status status) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.pois = pois;
    this.temporary = temporary;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<POI> getPois() {
    return pois;
  }

  public void setPois(List<POI> pois) {
    this.pois = pois;
  }

  public boolean isTemporary() {
    return temporary;
  }

  public void setTemporary(boolean temporary) {
    this.temporary = temporary;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
