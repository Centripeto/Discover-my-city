package com.boomers.www.discover_my_city.api.controller;

import com.boomers.www.discover_my_city.api.dto.POIDto;
import com.boomers.www.discover_my_city.api.dto.Paged;
import com.boomers.www.discover_my_city.api.dto.Response;
import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.handler.AuthFacade;
import com.boomers.www.discover_my_city.core.handler.PoiFacade;
import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.poi.POIRequest;
import com.boomers.www.discover_my_city.utils.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/poi")
public class POIController {

  private final PoiFacade poiFacade;
  private final AuthFacade authFacade;
  private final Mapper<POI, POIDto> poiToPoiDtoMapper;

  @Autowired
  public POIController(
      PoiFacade poiFacade, Mapper<POI, POIDto> poiToPoiDtoMapper, AuthFacade authFacade) {
    this.poiFacade = poiFacade;
    this.authFacade = authFacade;
    this.poiToPoiDtoMapper = poiToPoiDtoMapper;
  }

  @PostMapping("/")
  public ResponseEntity<POIDto> create(
      @RequestBody POIDto poi, @RequestHeader(name = "Authorization") String token) {
    String jwt = token.substring(7);
    POIDto dto = null;
    try {
      dto =
          poiToPoiDtoMapper.to(
              poiFacade.createPoi(
                  authFacade.extractUserFromToken(jwt).orElse(null), poiToPoiDtoMapper.from(poi)));
    } catch (UnauthorizedException e) {
      return ResponseEntity.status(401).body(null);
    }
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/")
  public ResponseEntity<Response<Paged<POIDto>>> pois(
      @RequestHeader(name = "Authorization") String token,
      @RequestParam Optional<Integer> pageNumber,
      @RequestParam Optional<Integer> pageSize) {
    String jwt = token.substring(7);
    POIRequest request = new POIRequest();
    request.setPageNumber(pageNumber.orElse(0));
    request.setPageSize(pageSize.orElse(Integer.MAX_VALUE));
    Paged<POI> pois =
        poiFacade.findPois(authFacade.extractUserFromToken(jwt).orElse(null), request);
    Paged<POIDto> response = new Paged<>();
    response.setList(
        pois.getList().stream().map(poiToPoiDtoMapper::to).collect(Collectors.toList()));
    response.setPageSize(pois.getPageSize());
    response.setTotalPages(pois.getTotalPages());
    response.setPageNumber(pois.getPageNumber());
    response.setTotalSize(pois.getTotalSize());
    return ResponseEntity.ok(
        Response.<Paged<POIDto>>builder().addMessage("").addResponse(response).build());
  }
}
