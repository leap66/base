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
  private String middle;
  private String high;
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

  public String getMiddle() {
    return middle;
  }

  public void setMiddle(String middle) {
    this.middle = middle;
  }

  public String getHigh() {
    return high;
  }

  public void setHigh(String high) {
    this.high = high;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
