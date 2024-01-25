package com.boomers.www.discover_my_city.utils.mapper;

import com.boomers.www.discover_my_city.api.dto.AuthenticationRequestDto;
import com.boomers.www.discover_my_city.core.model.auth.AuthenticationRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationRequestDtoToAuthenticationRequest
    implements Mapper<AuthenticationRequestDto, AuthenticationRequest> {
  @Override
  public AuthenticationRequestDto from(AuthenticationRequest model) {
    return new AuthenticationRequestDto(model.getUsername(), model.getPassword());
  }

  @Override
  public AuthenticationRequest to(AuthenticationRequestDto model) {
    return new AuthenticationRequest(model.getUsername(), model.getPassword());
  }
}
