package com.leap.base.listener;

/**
 * ServiceCallback : 服务请求接口回调
 * <p>
 * </> Created by ylwei on 2018/5/18.
 */
public interface ServiceCallback {

  void failure(String errorMsg);

  void success(String jsonStr);
}
