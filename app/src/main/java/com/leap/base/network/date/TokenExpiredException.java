package com.leap.base.network.date;

import java.io.IOException;

/**
 * TokenExpiredException : Token过期异常实体类
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class TokenExpiredException extends IOException {
  private int code;
  private String message;

  public TokenExpiredException(int code, String message) {
    super(message);
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
