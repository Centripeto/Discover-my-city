package com.boomers.www.discover_my_city.core.model.user;

import com.boomers.www.discover_my_city.core.repository.POIRepository;

public interface ContributorInterface {
  POI createPoi(POIRepository repository, POI poi);
}
