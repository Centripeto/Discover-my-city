package com.boomers.www.discover_my_city.api.dto;

public class Response<T> {
  private String message;
  private T response;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getResponse() {
    return response;
  }

  public void setResponse(T response) {
    this.response = response;
  }

  public static <K> Builder<K> builder() {
    return new Builder<K>();
  }

  public static class Builder<R> {
    private Response<R> response;

    public Builder() {
      reset();
    }

    public Builder<R> addResponse(R response) {
      this.response.setResponse(response);
      return this;
    }

    public Builder<R> addMessage(String message) {
      this.response.setMessage(message);
      return this;
    }

    public Builder<R> reset() {
      response = new Response<R>();
      return this;
    }

    public Response<R> build() {
      return response;
    }
  }
}
