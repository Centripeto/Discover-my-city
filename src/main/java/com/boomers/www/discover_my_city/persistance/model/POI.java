package com.boomers.www.discover_my_city.persistance.model;

import com.boomers.www.discover_my_city.Coordinate;
import com.boomers.www.discover_my_city.model.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "poi")
public class POI {
  @Id private String id;
  private String name;
  private String description;
  private Coordinate coordinate;
  private Status status;

  public POI() {}

  public POI(String id, String name, String description, Coordinate coordinate, Status status) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.coordinate = coordinate;
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

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
