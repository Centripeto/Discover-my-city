package com.boomers.www.discover_my_city.api.dto;

import java.util.List;

public class MunicipalityDto {
  private Integer id;
  private String name;
  private String description;
  private List<CoordinateDto> edges;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public List<CoordinateDto> getEdges() {
    return edges;
  }

  public void setEdges(List<CoordinateDto> edges) {
    this.edges = edges;
  }
}
