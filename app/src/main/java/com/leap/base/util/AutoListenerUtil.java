package com.leap.base.util;

import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.leap.base.listener.OnChangedListener;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 自动延迟监听工具类
 * <p>
 * </> Created by ylwei on 2017/11/21.
 */
public class AutoListenerUtil {

  /**
   * 自动监听
   * 
   * @param textViews
   *          监听的可变化TextView
   * @param time
   *          延迟搜索time
   * @param listener
   *          字段改变回调监听
   */
  public static void textChange(final OnChangedListener<String> listener, long time,
      TextView... textViews) {
    for (TextView textView : textViews)
      textChanges(textView, time).subscribe(new Action1<CharSequence>() {
        @Override
        public void call(CharSequence charSequence) {
          listener.onChange(charSequence.toString(), charSequence.toString());
        }
      });
  }

  /**
   * 延迟监听
   */
  private static Observable<CharSequence> textChanges(TextView textView, long time) {
    return RxTextView.textChanges(textView).debounce(time, TimeUnit.MILLISECONDS)
        .subscribeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
