package com.boomers.www.discover_my_city.core.model.poi;

import com.boomers.www.discover_my_city.core.model.common.PaginatedRequest;
import com.boomers.www.discover_my_city.core.model.user.User;

public class POIRequest extends PaginatedRequest {
  private User creator;

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }
}
