package com.boomers.www.discover_my_city.api.controller;

import com.boomers.www.discover_my_city.api.dto.POIDto;
import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.handler.AuthFacade;
import com.boomers.www.discover_my_city.core.handler.PoiFacade;
import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.utils.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/poi")
public class POIController {

  private final PoiFacade contributorFacade;
  private final AuthFacade authFacade;
  private final Mapper<POI, POIDto> poiToPoiDtoMapper;

  @Autowired
  public POIController(
      PoiFacade contributorFacade, Mapper<POI, POIDto> poiToPoiDtoMapper, AuthFacade authFacade) {
    this.contributorFacade = contributorFacade;
    this.authFacade = authFacade;
    this.poiToPoiDtoMapper = poiToPoiDtoMapper;
  }

  @PostMapping("")
  public ResponseEntity<POIDto> create(
      @RequestBody POIDto poi, @RequestHeader(name = "Authorization") String token) {
    String jwt = token.substring(7);
    POIDto dto = null;
    try {
      dto =
          poiToPoiDtoMapper.to(
              contributorFacade.createPoi(
                  authFacade.extractUserFromToken(jwt).orElse(null), poiToPoiDtoMapper.from(poi)));
    } catch (UnauthorizedException e) {
      return ResponseEntity.status(401).body(null);
    }
    return ResponseEntity.ok(dto);
  }
}
