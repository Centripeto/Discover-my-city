package com.boomers.www.discover_my_city.core.model.user;

import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.poi.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Itinerary {
  private String id;
  private String name;
  private String description;
  private List<POI> pois;
  private boolean temporary;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Status status;

  public Itinerary() {}

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

  @Override
  public String toString() {
    String json = """
        {
            "id": "%s",
            "name": "%s",
            "description": "%s",
            "status": "%s",
            "isTemporary": %s,
            "pois": [%s],
        }
      """;
    return String.format(json, this.id, this.name, this.description, this.status, this.isTemporary(), this.getPois().stream().map(POI::toString).collect(Collectors.joining(",\n")));
  }
}
