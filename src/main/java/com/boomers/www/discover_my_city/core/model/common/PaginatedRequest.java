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

  public abstract static class Builder<T extends PaginatedRequest> {
    protected T request;

    public Builder() {
      reset();
    }

    public PaginatedRequest.Builder<T> addPageSize(Integer pageSize) {
      this.request.setPageSize(pageSize);
      return this;
    }

    public PaginatedRequest.Builder<T> addPageNumber(Integer pageNumber) {
      this.request.setPageNumber(pageNumber);
      return this;
    }

    public abstract PaginatedRequest.Builder<T> reset();

    public T build() {
      return request;
    }
  }
}
