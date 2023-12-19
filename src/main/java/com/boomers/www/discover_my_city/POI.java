package com.boomers.www.discover_my_city;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class POI {
  private final String id;
  private String name;
  private String description;
  private Coordinate coordinate;
  private Status status;

  private static List<POI> pois = new ArrayList<>();

  public POI(String name, String description, Coordinate coordinate) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.description = description;
    this.coordinate = coordinate;
    this.status = Status.IN_APPROVAL;
  }

  public POI approve() {
    this.status = Status.APPROVED;
    return this;
  }

  public POI save() {
    pois.add(this);
    return this;
  }

  public static List<POI> getPois() {
    return POI.pois;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  public Status getStatus() {
    return this.status;
  }

  @Override
  public String toString() {
      String json = """
        {
            "id": "%s",
            "name": "%s",
            "description": "%s",
            "status": "%s",
            "coordinate": %s,
        }
      """;
      return String.format(json, this.id, this.name, this.description, this.status, this.coordinate);
  }
}
