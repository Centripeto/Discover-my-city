package com.boomers.www.discover_my_city.core.model.user;

public class Coordinate {
    private double latitude;
    private double longitude;

    public Coordinate() { }

    public Coordinate(double latitude, double longitude) {
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
    @Override
    public String toString() {
        String json =  """
        {
            "latitude": %.2f,
            "longitude": %.2f,
        }
      """;
        return String.format(json, this.latitude, this.longitude);
    }
}
