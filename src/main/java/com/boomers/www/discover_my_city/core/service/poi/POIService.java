package com.boomers.www.discover_my_city.core.service.poi;

import com.boomers.www.discover_my_city.api.dto.Paged;
import com.boomers.www.discover_my_city.core.exception.NotFoundException;
import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.poi.POIRequest;
import com.boomers.www.discover_my_city.core.model.poi.Status;
import com.boomers.www.discover_my_city.core.model.user.Role;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.POIRepository;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.create.CreatePoiBehaviour;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.list.ListPoiBehaviour;

import java.util.List;
import java.util.NoSuchElementException;

public class POIService {
  private final POIRepository poiRepository;

  public POIService(POIRepository poiRepository) {
    this.poiRepository = poiRepository;
  }

  public POI createPOI(CreatePoiBehaviour createPoiBehaviour, POI poi)
      throws UnauthorizedException {
    return createPoiBehaviour.createPoi(poi, poiRepository);
  }

  public Paged<POI> findPois(ListPoiBehaviour listPoiStrategy, POIRequest request) {
    return listPoiStrategy.find(request, poiRepository);
  }

  public POI findPoi(POIRequest request) throws NotFoundException {
    try {
      return poiRepository.findAll(request).getList().getFirst();
    } catch (NoSuchElementException ex) {
      throw new NotFoundException();
    }
  }

  public POI approvePoi(User user, Integer id) throws UnauthorizedException, NotFoundException {
    if (!List.of(Role.CURATORE).contains(user.getRole())) {
      throw new UnauthorizedException();
    }
    POIRequest request = new POIRequest();
    request.setId(id);
    POI poi = findPoi(request);
    poi.setStatus(Status.APPROVED);
    return poiRepository.update(poi);
  }

  public POI rejectPoi(User user, Integer id) throws UnauthorizedException, NotFoundException {
    if (!List.of(Role.CURATORE).contains(user.getRole())) {
      throw new UnauthorizedException();
    }
    POIRequest request = new POIRequest();
    request.setId(id);
    POI poi = findPoi(request);
    poi.setStatus(Status.REJECTED);
    return poiRepository.update(poi);
  }
}
