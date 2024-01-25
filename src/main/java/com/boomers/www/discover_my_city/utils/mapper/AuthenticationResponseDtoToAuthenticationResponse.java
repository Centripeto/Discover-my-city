package com.boomers.www.discover_my_city.utils.mapper;

import com.boomers.www.discover_my_city.api.dto.AuthenticationResponseDto;
import com.boomers.www.discover_my_city.core.model.auth.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationResponseDtoToAuthenticationResponse
    implements Mapper<AuthenticationResponseDto, AuthenticationResponse> {
  @Override
  public AuthenticationResponseDto from(AuthenticationResponse model) {
    return new AuthenticationResponseDto(model.getAccessToken(), model.getRefreshToken());
  }

  @Override
  public AuthenticationResponse to(AuthenticationResponseDto model) {
    return new AuthenticationResponse(model.getAccessToken(), model.getRefreshToken());
  }
}
