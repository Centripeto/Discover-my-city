package com.boomers.www.discover_my_city.core.service.poi.behaviour.list;

import com.boomers.www.discover_my_city.api.dto.Paged;
import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.poi.POIRequest;
import com.boomers.www.discover_my_city.core.repository.POIRepository;

public interface ListPoiBehaviour {
  Paged<POI> find(POIRequest request, POIRepository poiRepository);
}
