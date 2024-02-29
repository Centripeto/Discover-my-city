package com.boomers.www.discover_my_city.tech.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_municipality")
public class UserMunicipalityEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "municipality_id", nullable = false)
  private MunicipalityEntity municipality;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public MunicipalityEntity getMunicipality() {
    return municipality;
  }

  public void setMunicipality(MunicipalityEntity municipality) {
    this.municipality = municipality;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }
}
