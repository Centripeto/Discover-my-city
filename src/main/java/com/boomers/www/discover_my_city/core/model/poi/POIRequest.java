package com.boomers.www.discover_my_city.core.model.poi;

import com.boomers.www.discover_my_city.core.model.common.PaginatedRequest;
import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.model.user.User;

public class POIRequest extends PaginatedRequest {
  private Integer id;
  private User creator;
  private Municipality municipality;
  private Status status;

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Municipality getMunicipality() {
    return municipality;
  }

  public void setMunicipality(Municipality municipality) {
    this.municipality = municipality;
  }

  public static Builder builder() {
    return new POIRequest.Builder();
  }

  public static class Builder extends PaginatedRequest.Builder<POIRequest> {

    public PaginatedRequest.Builder<POIRequest> addId(Integer id) {
      this.request.setId(id);
      return this;
    }

    public PaginatedRequest.Builder<POIRequest> addCreator(User creator) {
      this.request.setCreator(creator);
      return this;
    }

    public PaginatedRequest.Builder<POIRequest> addStatus(Status status) {
      this.request.setStatus(status);
      return this;
    }

    @Override
    public PaginatedRequest.Builder<POIRequest> reset() {
      this.request = new POIRequest();
      return this;
    }
  }
}
