package com.leap.base.data.base;

import java.util.Date;

/**
 * StandardEntity : 标准继承类
 * <p>
 * </> Created by ylwei on 2018/3/30.
 */
public class StandardEntity extends VersionedEntity {
  private Date created;
  private BUcn creator;
  private Date lastModified;
  private BUcn lastModifier;

  public BUcn getCreator() {
    return creator;
  }

  public void setCreator(BUcn creator) {
    this.creator = creator;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public BUcn getLastModifier() {
    return lastModifier;
  }

  public void setLastModifier(BUcn lastModifier) {
    this.lastModifier = lastModifier;
  }
}
