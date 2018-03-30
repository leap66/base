package com.leap.base.data.base;

/**
 * BUcn : 多功能继承类
 * <p>
 * </> Created by ylwei on 2018/3/30.
 */
public class BUcn extends BEntity {
  private String code;
  private String name;
  private boolean newUcn;

  public BUcn() {
  }

  public BUcn(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public BUcn(String code, String name, boolean newUcn) {
    this.code = code;
    this.name = name;
    this.newUcn = newUcn;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isNewUcn() {
    return newUcn;
  }

  public void setNewUcn(boolean newUcn) {
    this.newUcn = newUcn;
  }
}
