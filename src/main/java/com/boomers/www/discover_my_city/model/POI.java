package com.boomers.www.discover_my_city.model;

public class POI {
  private String id;
  private String name;
  private String description;
  private Coordinate coordinate;
  private Status status;

  public POI() { }

  public POI(String id, String name, String description, Coordinate coordinate, Status status) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.coordinate = coordinate;
    this.status = status;
  }

  public POI(String name, String description, Coordinate coordinate, Status status) {
    this.name = name;
    this.description = description;
    this.coordinate = coordinate;
    this.status = status;
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

  public void setStatus(Status status) { this.status = status; }

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
