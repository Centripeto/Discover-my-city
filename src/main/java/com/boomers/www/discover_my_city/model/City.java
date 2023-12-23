package com.boomers.www.discover_my_city.model;

public class City {
  private String id;
  private String name;
  private String description;
  private Coordinate coordinate;

  public City() {}

  public City(String name, String description, Coordinate coordinate) {
    this.name = name;
    this.description = description;
    this.coordinate = coordinate;
  }

  public City(String id, String name, String description, Coordinate coordinate) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.coordinate = coordinate;
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

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }
}
