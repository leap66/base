package com.leap.base.mgr;

import android.content.Context;

import com.leap.base.data.BaseEntity;

/**
 * BaseMgr : Base组件管理类
 * <p>
 * </> Created by ylwei on 2018/2/24.
 */
public class BaseMgr {
  private static String Key_Entity;
  private static BaseEntity entity;

  public static void init(Context context) {
    ContextMgr.init(context);
    Key_Entity = context.getPackageName() + ".BaseEntity";
    StorageMgr.init();
    entity = StorageMgr.get(Key_Entity, BaseEntity.class, StorageMgr.LEVEL_GLOBAL);
  }

  public static void updateEntity(BaseEntity baseEntity) {
    entity = baseEntity;
    StorageMgr.set(Key_Entity, entity, StorageMgr.LEVEL_GLOBAL);
  }

  public static BaseEntity getBaseEntity() {
    return entity;
  }
}
