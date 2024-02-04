package com.boomers.www.discover_my_city.tech.geocoding;

import com.boomers.www.discover_my_city.api.dto.GeoDto;
import com.google.gson.GsonBuilder;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeoCodingService {

  CloseableHttpClient httpClient = HttpClientBuilder.create().build();

  public List<GeoDto> search(String search) throws IOException {
    String param = encodeValue(search);
    List<Response> response =
        httpClient.execute(
            new HttpGet("https://nominatim.openstreetmap.org/search?format=json&q=" + param),
            r -> {
              String bodyAsString = EntityUtils.toString(r.getEntity());
              return Arrays.asList(
                  new GsonBuilder().create().fromJson(bodyAsString, Response[].class));
            });
    return response.stream()
        .map(
            el -> {
              GeoDto dto = new GeoDto();
              dto.setDisplayName(el.getDisplay_name());
              dto.setLatitude(el.getLat());
              dto.setLongitude(el.getLon());
              dto.setName(el.getName());
              return dto;
            })
        .collect(Collectors.toList());
  }

  private String encodeValue(String value) throws UnsupportedEncodingException {
    return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
  }

  private class Response {
    private Double lat;
    private Double lon;
    private String name;
    private String display_name;

    public Double getLat() {
      return lat;
    }

    public void setLat(Double lat) {
      this.lat = lat;
    }

    public Double getLon() {
      return lon;
    }

    public void setLon(Double lon) {
      this.lon = lon;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getDisplay_name() {
      return display_name;
    }

    public void setDisplay_name(String display_name) {
      this.display_name = display_name;
    }
  }
}
