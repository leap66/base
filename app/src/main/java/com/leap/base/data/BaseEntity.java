package com.leap.base.data;

import java.io.Serializable;

/**
 * BaseEntity : 基础实体类
 * <p>
 * </> Created by ylwei on 2018/2/24.
 */
public class BaseEntity implements Serializable {
  private String id;
  private String user;
  private String name;
  private String url;
  private String middle;
  private String middleUrl;
  private String high;
  private String highUrl;
  private String remark;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getMiddle() {
    return middle;
  }

  public void setMiddle(String middle) {
    this.middle = middle;
  }

  public String getMiddleUrl() {
    return middleUrl;
  }

  public void setMiddleUrl(String middleUrl) {
    this.middleUrl = middleUrl;
  }

  public String getHigh() {
    return high;
  }

  public void setHigh(String high) {
    this.high = high;
  }

  public String getHighUrl() {
    return highUrl;
  }

  public void setHighUrl(String highUrl) {
    this.highUrl = highUrl;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
