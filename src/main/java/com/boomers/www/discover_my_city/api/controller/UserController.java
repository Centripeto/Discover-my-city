package com.boomers.www.discover_my_city.api.controller;

import com.boomers.www.discover_my_city.api.dto.Response;
import com.boomers.www.discover_my_city.api.dto.UserDto;
import com.boomers.www.discover_my_city.core.exception.AlreadyExistsException;
import com.boomers.www.discover_my_city.core.exception.NotFoundException;
import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.handler.AuthFacade;
import com.boomers.www.discover_my_city.core.handler.UserFacade;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.service.UserSecurity;
import com.boomers.www.discover_my_city.utils.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final AuthFacade authFacade;
  private final UserFacade userFacade;
  private final Mapper<User, UserDto> userMapper;

  @Autowired
  public UserController(
      AuthFacade authFacade, UserFacade userFacade, Mapper<User, UserDto> userMapper) {
    this.authFacade = authFacade;
    this.userFacade = userFacade;
    this.userMapper = userMapper;
  }

  @GetMapping("/whoami")
  public ResponseEntity<Response<UserDto>> whoami(Principal principal) {
    UserSecurity security =
        (UserSecurity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    if (Objects.isNull(security.getUser())) {
      return ResponseEntity.status(404)
          .body(Response.<UserDto>builder().addMessage("User not found").build());
    }
    return ResponseEntity.ok(
        Response.<UserDto>builder()
            .addMessage("")
            .addResponse(userMapper.to(security.getUser()))
            .build());
  }

  @PostMapping("/")
  public ResponseEntity<Response<UserDto>> create(Principal principal, @RequestBody UserDto user) {
    UserSecurity security =
        (UserSecurity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    User created = null;
    try {
      created = userFacade.createUser(security.getUser(), userMapper.from(user));
    } catch (UnauthorizedException e) {
      return ResponseEntity.status(401)
          .body(
              Response.<UserDto>builder()
                  .addMessage("You are not authorized to create user")
                  .build());
    } catch (AlreadyExistsException e) {
      return ResponseEntity.status(409)
          .body(Response.<UserDto>builder().addMessage(e.getMessage()).build());
    } catch (NotFoundException e) {
      return ResponseEntity.status(404)
          .body(Response.<UserDto>builder().addMessage(e.getMessage()).build());
    }
    return ResponseEntity.ok(
        Response.<UserDto>builder()
            .addMessage("success")
            .addResponse(userMapper.to(created))
            .build());
  }
}
