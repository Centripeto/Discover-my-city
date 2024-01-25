package com.boomers.www.discover_my_city.api.dto;

public class POIDto {
  private Integer id;
  private String name;
  private String description;
  private CoordinateDto coordinate;
  private String status;

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
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

  public CoordinateDto getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(CoordinateDto coordinate) {
    this.coordinate = coordinate;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
