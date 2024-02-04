package com.boomers.www.discover_my_city.core.service;

public class JwtConfig {
  private String secretKey;

  private long jwtExpiration;

  private long refreshExpiration;

  public JwtConfig() {}

  public JwtConfig(String secretKey, long jwtExpiration, long refreshExpiration) {
    this.secretKey = secretKey;
    this.jwtExpiration = jwtExpiration;
    this.refreshExpiration = refreshExpiration;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public long getJwtExpiration() {
    return jwtExpiration;
  }

  public void setJwtExpiration(long jwtExpiration) {
    this.jwtExpiration = jwtExpiration;
  }

  public long getRefreshExpiration() {
    return refreshExpiration;
  }

  public void setRefreshExpiration(long refreshExpiration) {
    this.refreshExpiration = refreshExpiration;
  }
}
