package com.boomers.www.discover_my_city.api.controller;

import com.boomers.www.discover_my_city.api.dto.MunicipalityDto;
import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.handler.MunicipalityFacade;
import com.boomers.www.discover_my_city.core.model.municipality.AreaPathImpl;
import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.model.poi.Coordinate;
import com.boomers.www.discover_my_city.service.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/municipality")
public class MunicipalityController {

  private final MunicipalityFacade municipalityFacade;

  @Autowired
  public MunicipalityController(MunicipalityFacade municipalityFacade) {
    this.municipalityFacade = municipalityFacade;
  }

  @PostMapping("/")
  public ResponseEntity<MunicipalityDto> create(
      @RequestBody MunicipalityDto dto, Principal principal) throws UnauthorizedException {
    UserSecurity security =
        (UserSecurity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    Municipality municipality = new Municipality();
    municipality.setDescription(dto.getDescription());
    municipality.setName(dto.getName());
    municipality.setArea(
        new AreaPathImpl(
            dto.getEdges().stream()
                .map(
                    el -> {
                      Coordinate coordinate = new Coordinate();
                      coordinate.setLatitude(el.getLatitude());
                      coordinate.setLongitude(el.getLongitude());
                      return coordinate;
                    })
                .collect(Collectors.toList())));
    municipalityFacade.createMunicipality(security.getUser(), municipality);
    return null;
  }
}
