package com.boomers.www.discover_my_city.api.dto;

import java.util.List;

public class Paged<T> {
  Integer pageNumber;
  Integer pageSize;
  Integer totalPages;
  Long totalSize;
  List<T> list;

  public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public Long getTotalSize() {
    return totalSize;
  }

  public void setTotalSize(Long totalSize) {
    this.totalSize = totalSize;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  public static <T> Paged.Builder<T> builder() {
    return new Builder<T>();
  }

  public static class Builder<T> {
    private Paged<T> paged;

    public Builder() {
      reset();
    }

    public Builder<T> reset() {
      paged = new Paged<T>();
      return this;
    }

    public Builder<T> addPagenumber(Integer pageNumber) {
      paged.setPageNumber(pageNumber);
      return this;
    }

    public Builder<T> addTotalSize(Long totalSize) {
      paged.setTotalSize(totalSize);
      return this;
    }

    public Builder<T> addPageSize(Integer pageSize) {
      paged.setPageSize(pageSize);
      return this;
    }

    public Builder<T> addTotalPages(Integer totalPages) {
      paged.setTotalPages(totalPages);
      return this;
    }

    public Builder<T> addList(List<T> list) {
      paged.setList(list);
      return this;
    }

    public Paged<T> build() {
      return paged;
    }
  }
}
