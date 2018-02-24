package com.leap.base.mgr;

import android.content.Context;

import com.leap.base.data.BaseEntity;

/**
 * BaseMgr : Base组件管理类
 * <p>
 * </> Created by ylwei on 2018/2/24.
 */
public class BaseMgr {
  private static BaseEntity entity;

  public static void init(Context context, BaseEntity baseEntity) {
    entity = baseEntity;
    ContextMgr.init(context);
  }

  public static void updateEntity(BaseEntity baseEntity) {
    entity = baseEntity;
  }

  public static BaseEntity getBaseEntity() {
    return entity;
  }
}
