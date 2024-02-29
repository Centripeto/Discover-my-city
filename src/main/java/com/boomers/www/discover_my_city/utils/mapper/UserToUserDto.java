package com.boomers.www.discover_my_city.utils.mapper;

import com.boomers.www.discover_my_city.api.dto.MunicipalityDto;
import com.boomers.www.discover_my_city.api.dto.UserDto;
import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDto implements Mapper<User, UserDto> {
  private final Mapper<Municipality, MunicipalityDto> municipalityMapper;

  @Autowired
  public UserToUserDto(Mapper<Municipality, MunicipalityDto> municipalityMapper) {
    this.municipalityMapper = municipalityMapper;
  }

  @Override
  public User from(UserDto model) {
    User user = new User();
    user.setName(model.getName());
    user.setUsername(model.getUsername());
    user.setLastname(model.getLastname());
    return user;
  }

  @Override
  public UserDto to(User model) {
    UserDto user = new UserDto();
    user.setName(model.getName());
    user.setUsername(model.getUsername());
    user.setLastname(model.getLastname());
    user.setRole(model.getRole().toString());
    user.setMunicipalityDto(municipalityMapper.to(model.getMunicipality()));
    return user;
  }
}
