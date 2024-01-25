package com.boomers.www.discover_my_city.utils.mapper;

public interface Mapper<T, E> {
  T from(E model);

  E to(T model);
}
