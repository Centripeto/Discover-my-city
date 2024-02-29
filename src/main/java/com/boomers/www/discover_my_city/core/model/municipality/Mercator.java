package com.boomers.www.discover_my_city.core.model.municipality;

abstract class Mercator {
  static final double RADIUS_MAJOR = 6378137.0;
  static final double RADIUS_MINOR = 6356752.3142;

  abstract double yAxisProjection(double input);

  abstract double xAxisProjection(double input);
}
