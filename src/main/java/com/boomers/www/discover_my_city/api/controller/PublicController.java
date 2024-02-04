package com.boomers.www.discover_my_city.api.controller;

import com.boomers.www.discover_my_city.api.dto.GeoDto;
import com.boomers.www.discover_my_city.api.dto.Response;
import com.boomers.www.discover_my_city.tech.geocoding.GeoCodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicController {

  private final GeoCodingService geoCodingService;

  @Autowired
  public PublicController(GeoCodingService geoCodingService) {
    this.geoCodingService = geoCodingService;
  }

  @GetMapping("/geo/search")
  public ResponseEntity<Response<List<GeoDto>>> geoSearch(@RequestParam String search) {
    try {
      List<GeoDto> response = geoCodingService.search(search);
      return ResponseEntity.ok(
          Response.<List<GeoDto>>builder().addMessage("").addResponse(response).build());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
