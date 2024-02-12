package com.boomers.www.discover_my_city.api.controller;

import com.boomers.www.discover_my_city.api.dto.AuthenticationRequestDto;
import com.boomers.www.discover_my_city.api.dto.AuthenticationResponseDto;
import com.boomers.www.discover_my_city.api.dto.Response;
import com.boomers.www.discover_my_city.core.exception.AuthenticationException;
import com.boomers.www.discover_my_city.core.handler.AuthFacade;
import com.boomers.www.discover_my_city.core.model.auth.AuthenticationRequest;
import com.boomers.www.discover_my_city.core.model.auth.AuthenticationResponse;
import com.boomers.www.discover_my_city.utils.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthFacade authFacade;
  private final Mapper<AuthenticationRequestDto, AuthenticationRequest>
      authenticationRequestDtoAuthenticationRequestMapper;
  private final Mapper<AuthenticationResponseDto, AuthenticationResponse>
      authenticationResponseDtoAuthenticationResponseMapper;

  @Autowired
  public AuthController(
      AuthFacade authFacade,
      Mapper<AuthenticationRequestDto, AuthenticationRequest>
          authenticationRequestDtoAuthenticationRequestMapper,
      Mapper<AuthenticationResponseDto, AuthenticationResponse>
          authenticationResponseDtoAuthenticationResponseMapper) {
    this.authFacade = authFacade;
    this.authenticationRequestDtoAuthenticationRequestMapper =
        authenticationRequestDtoAuthenticationRequestMapper;
    this.authenticationResponseDtoAuthenticationResponseMapper =
        authenticationResponseDtoAuthenticationResponseMapper;
  }

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponseDto> register() {
    return ResponseEntity.ok(
        authenticationResponseDtoAuthenticationResponseMapper.from(authFacade.registerAdmin()));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<Response<AuthenticationResponseDto>> authenticate(
      @RequestBody AuthenticationRequestDto request) {

    AuthenticationRequest authenticationRequest =
        authenticationRequestDtoAuthenticationRequestMapper.to(request);
    AuthenticationResponseDto response = null;
    try {
      response =
          authenticationResponseDtoAuthenticationResponseMapper.from(
              authFacade.authenticate(authenticationRequest));

    } catch (AuthenticationException ex) {
      return ResponseEntity.status(403)
          .body(
              Response.<AuthenticationResponseDto>builder()
                  .addMessage("Authentication failed")
                  .build());
    }
    return ResponseEntity.ok(
        Response.<AuthenticationResponseDto>builder()
            .addMessage("Authenticated")
            .addResponse(response)
            .build());
  }

  //  @PostMapping("/refresh-token")
  //  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
  //      throws IOException {
  //    service.refreshToken(request, response);
  //  }
}
