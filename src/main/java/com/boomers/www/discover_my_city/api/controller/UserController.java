package com.boomers.www.discover_my_city.api.controller;

import com.boomers.www.discover_my_city.api.dto.Response;
import com.boomers.www.discover_my_city.api.dto.UserDto;
import com.boomers.www.discover_my_city.core.handler.AuthFacade;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.utils.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final AuthFacade authFacade;
  private final Mapper<User, UserDto> userMapper;

  @Autowired
  public UserController(AuthFacade authFacade, Mapper<User, UserDto> userMapper) {
    this.authFacade = authFacade;
    this.userMapper = userMapper;
  }

  @GetMapping("/whoami")
  public ResponseEntity<Response<UserDto>> whoami(
      @RequestHeader(name = "Authorization") String token) {
    String jwt = token.substring(7);
    User user = authFacade.extractUserFromToken(jwt).orElse(null);
    if (Objects.isNull(user)) {
      return ResponseEntity.status(401)
          .body(Response.<UserDto>builder().addMessage("User not found").build());
    }
    return ResponseEntity.ok(
        Response.<UserDto>builder().addMessage("").addResponse(userMapper.to(user)).build());
  }
}
