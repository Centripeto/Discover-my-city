package com.boomers.www.discover_my_city.core.model.municipality;

import com.boomers.www.discover_my_city.core.model.poi.Coordinate;

import java.util.List;

public interface Area {
  void addPoint(Coordinate point);

  boolean isInBounds(Coordinate point);

  List<Coordinate> getEdges();
}
