package com.leap.base.adapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jakewharton.rxbinding.view.RxView;
import com.leap.base.R;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * AppBindingAdapter : 用于处理自定义的xml中的属性操作
 * <p>
 * </> Created by ylwei on 2018/2/26.
 */
public class AppBindingAdapter {

  @BindingAdapter("resUrl")
  public static void loadImageUrl(ImageView view, Object resourceUrl) {
    RequestOptions options = new RequestOptions();
    options.placeholder(R.mipmap.icon_placeholder);
    options.error(R.mipmap.icon_placeholder);
    Glide.with(view.getContext()).load(resourceUrl).apply(options).into(view);
  }

  @BindingAdapter("layoutManager")
  public static void setLayoutManager(RecyclerView view, RecyclerView.LayoutManager manager) {
    view.setLayoutManager(manager);
  }

  @BindingAdapter("adapter")
  public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
    view.setAdapter(adapter);
  }

  /**
   * 注意，单独设置 throttleTime 是没有意义的
   */
  @BindingAdapter(value = {
      "throttleClick", "throttleTime" }, requireAll = false)
  public static void throttleClick(final View view, final View.OnClickListener clickListener,
      Integer throttleTime) {
    // 如果没有设置 throttleTime， 默认间隔为 defaultTime
    int defaultTime = 5;
    if (throttleTime != null) {
      defaultTime = throttleTime;
    }
    // 如果只设置了 throttleTime, clickListener 为 null
    if (clickListener != null) {
      RxView.clicks(view).throttleFirst(defaultTime, TimeUnit.SECONDS)
          .subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
              clickListener.onClick(view);
            }
          });
    }
  }
}
