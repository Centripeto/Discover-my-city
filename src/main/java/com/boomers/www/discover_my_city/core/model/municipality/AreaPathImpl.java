package com.boomers.www.discover_my_city.core.model.municipality;

import com.boomers.www.discover_my_city.core.model.poi.Coordinate;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class AreaPathImpl implements Area {

  List<Coordinate> coordinates;

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
    Path2D path = new Path2D.Double();
    for (Coordinate c : coordinates) {
      path.lineTo(c.getLongitude(), c.getLatitude());
    }
    path.closePath();
    return path.contains(point.getLongitude(), point.getLatitude());
  }

  @Override
  public List<Coordinate> getEdges() {
    return coordinates;
  }
}
