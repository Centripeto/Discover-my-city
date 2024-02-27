package com.boomers.www.discover_my_city.api.controller;

import com.boomers.www.discover_my_city.api.dto.POIDto;
import com.boomers.www.discover_my_city.api.dto.Paged;
import com.boomers.www.discover_my_city.api.dto.Response;
import com.boomers.www.discover_my_city.core.exception.NotFoundException;
import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.handler.AuthFacade;
import com.boomers.www.discover_my_city.core.handler.PoiFacade;
import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.poi.POIRequest;
import com.boomers.www.discover_my_city.core.model.poi.Status;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.service.UserSecurity;
import com.boomers.www.discover_my_city.utils.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;
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
  public ResponseEntity<Response<POIDto>> create(@RequestBody POIDto poi, Principal principal) {
    UserSecurity security =
        (UserSecurity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    POIDto dto = null;
    try {
      dto =
          poiToPoiDtoMapper.to(
              poiFacade.createPoi(security.getUser(), poiToPoiDtoMapper.from(poi)));
    } catch (UnauthorizedException e) {
      return ResponseEntity.status(401)
          .body(Response.<POIDto>builder().addMessage(e.getMessage()).build());
    }
    return ResponseEntity.ok(Response.<POIDto>builder().addMessage("").addResponse(dto).build());
  }

  @GetMapping("/")
  public ResponseEntity<Response<Paged<POIDto>>> pois(
      @RequestHeader(name = "Authorization", required = false) String token,
      Principal principal,
      @RequestParam Optional<Integer> pageNumber,
      @RequestParam Optional<Integer> pageSize,
      @RequestParam Optional<Status> status) {
    User user =
        Objects.isNull(principal)
            ? null
            : ((UserSecurity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal())
                .getUser();
    POIRequest request =
        POIRequest.builder()
            .addStatus(status.orElse(null))
            .addPageNumber(pageNumber.orElse(0))
            .addPageSize(pageSize.orElse(Integer.MAX_VALUE))
            .build();

    Paged<POI> pois = poiFacade.findPois(user, request);

    Paged<POIDto> response =
        Paged.<POIDto>builder()
            .addTotalPages(pois.getTotalPages())
            .addPageSize(pois.getPageSize())
            .addTotalSize(pois.getTotalSize())
            .addPagenumber(pois.getPageNumber())
            .addList(
                pois.getList().stream().map(poiToPoiDtoMapper::to).collect(Collectors.toList()))
            .build();

    return ResponseEntity.ok(
        Response.<Paged<POIDto>>builder().addMessage("").addResponse(response).build());
  }

  @PutMapping("/approve/{id}")
  public ResponseEntity<Response<POIDto>> approve(
      Principal principal, @PathVariable(required = true) Integer id) {
    UserSecurity security =
        (UserSecurity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    POIDto dto = null;
    try {
      dto = poiToPoiDtoMapper.to(poiFacade.approvePoi(security.getUser(), id));
    } catch (UnauthorizedException e) {
      return ResponseEntity.status(401)
          .body(Response.<POIDto>builder().addMessage("You are not authorized").build());
    } catch (NotFoundException e) {
      return ResponseEntity.status(404)
          .body(Response.<POIDto>builder().addMessage("Poi not found").build());
    }
    return ResponseEntity.ok(Response.<POIDto>builder().addResponse(dto).build());
  }

  @PutMapping("/reject/{id}")
  public ResponseEntity<Response<POIDto>> reject(
      Principal principal, @PathVariable(required = true) Integer id) {
    UserSecurity security =
        (UserSecurity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    POIDto dto = null;
    try {
      dto = poiToPoiDtoMapper.to(poiFacade.rejectPoi(security.getUser(), id));
    } catch (UnauthorizedException e) {
      return ResponseEntity.status(401)
          .body(Response.<POIDto>builder().addMessage("You are not authorized").build());
    } catch (NotFoundException e) {
      return ResponseEntity.status(404)
          .body(Response.<POIDto>builder().addMessage("Poi not found").build());
    }
    return ResponseEntity.ok(Response.<POIDto>builder().addResponse(dto).build());
  }
}
