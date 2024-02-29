package com.boomers.www.discover_my_city.core.service.poi.behaviour.list;

import com.boomers.www.discover_my_city.api.dto.Paged;
import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.poi.POIRequest;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.POIRepository;

public class ListAllApprovedPoiAndUserInApproval implements ListPoiBehaviour {

  private final User user;

  public ListAllApprovedPoiAndUserInApproval(User user) {
    this.user = user;
  }

  @Override
  public Paged<POI> find(POIRequest request, POIRepository poiRepository) {
    request.setCreator(user);
    request.setMunicipality(user.getMunicipality());
    return poiRepository.getAllApprovedPoiAndUserInApproval(request);
  }
}
