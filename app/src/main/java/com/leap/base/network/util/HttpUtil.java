package com.leap.base.network.util;

import android.content.Context;

import com.google.gson.Gson;
import com.leap.base.R;
import com.leap.base.mgr.BaseMgr;
import com.leap.base.mgr.ContextMgr;
import com.leap.base.network.date.ApiException;
import com.leap.base.network.date.AuthEvent;
import com.leap.base.network.date.TokenExpiredException;
import com.leap.base.util.GsonUtil;
import com.leap.base.util.IsEmpty;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import okhttp3.Response;

/**
 * HttpUtil : Http工具类
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class HttpUtil {

  // 网络请求返回参数第一层解析
  public static void parse(Response response) throws IOException {
    Context context = ContextMgr.getInstance();
    ApiException e = null;
    if (401 == response.code()) {
      throw new TokenExpiredException(401, context.getString(R.string.network_request_err_401));
    } else if (403 == response.code()) {
      e = new ApiException(403, context.getString(R.string.network_request_err_403));
    } else if (503 == response.code()) {
      e = new ApiException(503, context.getString(R.string.network_request_err_503));
    } else if (500 == response.code()) {
      e = new ApiException(500, context.getString(R.string.network_request_err_500));
    } else if (404 == response.code()) {
      e = new ApiException(404, context.getString(R.string.network_request_err_404));
    }
    if (!IsEmpty.object(e)) {
      throw e;
    }
  }

  // 网络请求返回参数第二层解析--客户端问题解析
  public static String parseThrowable(Throwable throwable) {
    Context context = ContextMgr.getInstance();
    String errorMessage;
    if (throwable instanceof TokenExpiredException) {
      // 使用EventBus通知跳转到登陆页面
      EventBus.getDefault().post(new AuthEvent(AuthEvent.TOKEN_EXPIRED));
      errorMessage = throwable.getMessage();
    } else if (throwable instanceof SocketTimeoutException) {
      errorMessage = context.getString(R.string.network_err_timeout);
    } else if (throwable instanceof SocketException) {
      errorMessage = context.getString(R.string.network_err_internet);
    } else if (throwable instanceof JSONException) {
      errorMessage = context.getString(R.string.network_err_parse);
    } else if (throwable instanceof ApiException) {
      errorMessage = throwable.getMessage();
    } else {
      errorMessage = context.getString(R.string.network_err_unknow);
    }
    return errorMessage;
  }

  public static Gson getHttpGson() {
    return GsonUtil.getInstance();
  }

  public static String getTraceId() {
    if (!IsEmpty.object(BaseMgr.getBaseEntity())
        && !IsEmpty.string(BaseMgr.getBaseEntity().getUser())) {
      return BaseMgr.getBaseEntity().getUser() + "-" + System.currentTimeMillis();
    } else {
      return String.valueOf(System.currentTimeMillis());
    }
  }
}
