package com.boomers.www.discover_my_city.core.model.municipality;

import com.boomers.www.discover_my_city.core.model.poi.Coordinate;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class AreaPathImpl implements Area {

  private List<Coordinate> coordinates;

  public AreaPathImpl(List<Coordinate> coordinates) {
    this.coordinates = coordinates;
  }

  public AreaPathImpl() {
    this(new ArrayList<>());
  }

  @Override
  public void addPoint(Coordinate point) {
    coordinates.add(point);
  }

  @Override
  public boolean isInBounds(Coordinate point) {
    Mercator mercator = new SphericalMercator();
    Path2D path = new Path2D.Double();
    boolean isFirst = true;
    for (Coordinate c : coordinates) {
      if (isFirst) {
        isFirst = false;
        path.moveTo(
            mercator.xAxisProjection(c.getLongitude()), mercator.yAxisProjection(c.getLatitude()));
      } else {
        path.lineTo(
            mercator.xAxisProjection(c.getLongitude()), mercator.yAxisProjection(c.getLatitude()));
      }
    }
    path.closePath();
    return path.contains(
        mercator.xAxisProjection(point.getLongitude()),
        mercator.yAxisProjection(point.getLatitude()));
  }

  @Override
  public List<Coordinate> getEdges() {
    return coordinates;
  }
}
