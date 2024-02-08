package com.boomers.www.discover_my_city.core.repository;

import com.boomers.www.discover_my_city.api.dto.Paged;
import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.poi.POIRequest;

public interface POIRepository {
  POI save(POI poi);

  Paged<POI> getAllApprovedPoiAndUserInApproval(POIRequest request);

  Paged<POI> findAll(POIRequest request);

  POI update(POI poi);
}
