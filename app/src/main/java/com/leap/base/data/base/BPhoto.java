package com.leap.base.data.base;

/**
 * BPhoto : 头像、照片
 * <p>
 * </> Created by ylwei on 2018/6/13.
 */
public class BPhoto extends BEntity {
  private String name;
  private String url;
  private boolean temp;

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

  public boolean isTemp() {
    return temp;
  }

  public void setTemp(boolean temp) {
    this.temp = temp;
  }
}
