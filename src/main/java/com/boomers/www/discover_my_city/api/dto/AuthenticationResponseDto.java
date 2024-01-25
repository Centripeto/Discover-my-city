package com.boomers.www.discover_my_city.api.dto;

public class AuthenticationResponseDto {

  private String accessToken;

  private String refreshToken;

  public AuthenticationResponseDto() {}

  public AuthenticationResponseDto(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
