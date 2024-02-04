package com.boomers.www.discover_my_city.core.model.common;

public abstract class PaginatedRequest {
  private Integer pageSize = Integer.MAX_VALUE;
  private Integer pageNumber = 0;

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }
}
