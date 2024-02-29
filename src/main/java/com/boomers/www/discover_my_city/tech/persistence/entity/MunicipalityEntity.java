package com.boomers.www.discover_my_city.tech.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "municipalities")
public class MunicipalityEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private String description;

  @OneToMany(mappedBy = "municipality", fetch = FetchType.EAGER)
  private List<EdgeEntity> edges;

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

  public List<EdgeEntity> getEdges() {
    return edges;
  }

  public void setEdges(List<EdgeEntity> edges) {
    this.edges = edges;
  }
}
