package com.boomers.www.discover_my_city.api.dto;

public class CoordinateDto {
  private double latitude;
  private double longitude;

  public CoordinateDto() {}

  public CoordinateDto(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
}
