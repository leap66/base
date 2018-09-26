package com.leap.base.network;

import com.leap.base.mgr.TokenMgr;
import com.leap.base.mgr.log.ErrorLogInterceptor;
import com.leap.base.network.util.NullOnEmptyConverterFactory;
import com.leap.base.network.util.HttpUtil;
import com.leap.base.network.util.RequestErrorInterceptor;
import com.leap.base.util.IsEmpty;
import com.leap.base.util.gsonconverter.GsonConverterFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * ApiClient : 基础API 网络访问客户端
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class ApiClient {
  private static String baseUrl;
  private static Retrofit platformClient;

  // 获取用户Cookie并设置Cookie到header
  private static Interceptor setUserCookie = new Interceptor() {
    @Override
    public Response intercept(Chain chain) throws IOException {
      Request request;
      String token = TokenMgr.getUserToken();
      if (!IsEmpty.string(token)) {
        request = chain.request().newBuilder().addHeader("Cookie", token)
            .addHeader("trace_id", HttpUtil.getTraceId()).build();
      } else {
        request = chain.request().newBuilder().addHeader("trace_id", HttpUtil.getTraceId()).build();
      }
      return chain.proceed(request);
    }
  };

  // 保存用户Cookie
  private static Interceptor getUserCookie = new Interceptor() {
    @Override
    public Response intercept(Chain chain) throws IOException {
      Request request = chain.request();
      Response response = chain.proceed(request);
      String cookie = response.headers().get("Set-Cookie");
      if (!IsEmpty.string(cookie) && cookie.contains("jwt=")) {
        TokenMgr.update(cookie);
      }
      return response;
    }
  };

  // 未登陆之前使用该对象 没有Token
  static Retrofit platformClient() {
    if (null != platformClient) {
      return platformClient;
    }
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient okClient = new OkHttpClient.Builder().retryOnConnectionFailure(true)
        .addInterceptor(logging)//
        .addInterceptor(getUserCookie).addInterceptor(setUserCookie)
        .addInterceptor(new ErrorLogInterceptor())//
        .addInterceptor(new RequestErrorInterceptor()).build();
    platformClient = new Retrofit.Builder().baseUrl(baseUrl).client(okClient)
        .addConverterFactory(new NullOnEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create(HttpUtil.getHttpGson()))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

    return platformClient;
  }

  public static void init(String baseUrl) {
    ApiClient.baseUrl = baseUrl;
  }

}
