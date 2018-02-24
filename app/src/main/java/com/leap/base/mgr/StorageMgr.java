package com.leap.base.mgr;

import android.content.Context;
import android.content.SharedPreferences;

import com.leap.base.data.BaseEntity;
import com.leap.base.util.GsonUtil;
import com.leap.base.util.IsEmpty;

/**
 * StorageMgr : 物理缓存管理
 * <p>
 * </> Created by ylwei on 2018/2/24.
 */
public class StorageMgr {
  public static final String LEVEL_USER = "user";// 用户级别（必需登录后使用）（默认级别）
  public static final String LEVEL_MIDDLE = "middle";// 中级缓存（必需选择门店后使用）
  public static final String LEVEL_HIGH = "high";// 中级缓存（必需选择门店后使用）
  public static final String LEVEL_GLOBAL = "global";// 全局级别
  private static SharedPreferences storage;

  // 初始化缓存管理
  public static void init() {
    Context context = ContextMgr.getInstance();
    storage = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
  }

  /**
   * 缓存数据 t 级别 user
   */
  public static <T> void set(String key, T t) throws RuntimeException {
    set(key, t, StorageMgr.LEVEL_USER);
  }

  /**
   * 缓存数据 t 级别 level
   */
  public static <T> void set(String key, T t, String level) throws RuntimeException {
    set(key, GsonUtil.toJson(t), level);
  }

  /**
   * 缓存数据 t 级别 level
   */
  public static void set(String key, String value, String level) throws RuntimeException {
    String k = "";
    if (IsEmpty.string(level)) {
      setStorage(k, value);
    } else {
      BaseEntity temp = BaseMgr.getBaseEntity();
      switch (level) {
      case LEVEL_GLOBAL:
        break;
      case LEVEL_USER:
        k += temp.getUser();
        break;
      case LEVEL_MIDDLE:
        k += temp.getMiddle();
        break;
      case LEVEL_HIGH:
        k += temp.getHigh();
        break;
      }
      k += key;
      setStorage(k, value);
    }
  }

  /**
   * 获取对应key的缓存
   */
  public static <T> T get(String key, Class<T> c) {
    String value = get(key, StorageMgr.LEVEL_USER);
    if (value == null) {
      return null;
    }
    return GsonUtil.parse(value, c);
  }

  /**
   * 获取对应key的缓存
   */
  public static <T> T get(String key, Class<T> c, String level) {
    String value = get(key, level);
    if (value == null) {
      return null;
    }
    return GsonUtil.parse(value, c);
  }

  /**
   * 获取对应key的缓存
   */
  public static String get(String key, String level) {
    String k = "";
    if (IsEmpty.string(level)) {
      return getStorage(k);
    } else {
      BaseEntity temp = BaseMgr.getBaseEntity();
      switch (level) {
      case LEVEL_GLOBAL:
        break;
      case LEVEL_USER:
        k += temp.getUser();
        break;
      case LEVEL_MIDDLE:
        k += temp.getMiddle();
        break;
      case LEVEL_HIGH:
        k += temp.getHigh();
        break;
      }
      k += key;
      return getStorage(k);
    }
  }

  // 设置缓存信息
  private static void setStorage(String key, String value) {
    SharedPreferences.Editor editor = storage.edit();
    editor.putString(key, value);
    editor.apply(); // 先提交内存，再异步提交硬盘
    // editor.commit(); //同步提交内存及硬盘
  }

  // 获取缓存信息
  private static String getStorage(String key) {
    return storage.getString(key, null);
  }

}
