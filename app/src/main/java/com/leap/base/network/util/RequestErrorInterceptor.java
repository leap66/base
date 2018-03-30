package com.leap.base.network.util;

import com.leap.base.R;
import com.leap.base.mgr.ContextMgr;
import com.leap.base.network.date.ApiException;
import com.leap.base.util.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * RequestErrorInterceptor : 网络请求服务端错误解析 -- 处理请求的status code
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class RequestErrorInterceptor implements Interceptor {

  @Override
  public Response intercept(Chain chain) throws IOException {
    if (!NetworkUtil.isConnected())
      throw new ApiException(0, ContextMgr.getInstance().getString(R.string.network_err_0));
    Request request = chain.request();
    Response response = chain.proceed(request);
    HttpUtil.parse(response);
    return response;
  }
}
