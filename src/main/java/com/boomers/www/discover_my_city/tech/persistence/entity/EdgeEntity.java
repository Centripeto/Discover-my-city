package com.boomers.www.discover_my_city.tech.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "edges")
public class EdgeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer position;
  private Double longitude;
  private Double latitude;

  @ManyToOne
  @JoinColumn(name = "municipality_id", nullable = false)
  private MunicipalityEntity municipality;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public MunicipalityEntity getMunicipality() {
    return municipality;
  }

  public void setMunicipality(MunicipalityEntity municipality) {
    this.municipality = municipality;
  }

  public Integer getOrder() {
    return position;
  }

  public void setOrder(Integer order) {
    this.position = order;
  }
}
