package com.leap.base.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.leap.base.R;

import java.util.HashMap;
import java.util.Map;

/**
 * LoadingLayout : 加载layout
 * <p>
 * </> Created by ylwei on 2018/3/30.
 */
public class LoadingLayout extends FrameLayout {
  public static final Integer LOADING = 0;
  public static final Integer CONTENT = 1;
  private AnimationDrawable animationDrawable;
  private Integer currentIndex = 2;
  private boolean lock = true;
  private View emptyView;

  @SuppressLint("UseSparseArrays")
  protected Map<Integer, View> viewMap = new HashMap<>();

  public LoadingLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    initView();
    show(CONTENT);
  }

  @Override
  protected void onDetachedFromWindow() {
    animationDrawable.stop();
    super.onDetachedFromWindow();
  }

  /**
   * 初始化View
   */
  private void initView() {
    int childCount = getChildCount();
    if (childCount != 1)
      throw new RuntimeException("LoadingLayout didn't allow more than one child.");
    emptyView = View.inflate(getContext(), R.layout.dialog_base_loading, null);
    ImageView imageView = (ImageView) emptyView.findViewById(R.id.base_loading);
    animationDrawable = (AnimationDrawable) imageView.getDrawable();
    animationDrawable.start();
    emptyView.setClickable(lock);
    viewMap.put(CONTENT, getChildAt(0));
    viewMap.put(LOADING, emptyView);
    addView(viewMap.get(LOADING));
  }

  /**
   * 展示某一种View
   */
  public void show(@NonNull Integer state) {
    if (currentIndex.equals(state))
      return;
    viewMap.get(LOADING).setVisibility(LOADING.equals(state) ? VISIBLE : GONE);
    // viewMap.get(CONTENT).setClickable(!lock || !LOADING.equals(state));
    currentIndex = state;
  }

  public void setLock(boolean lock) {
    this.lock = lock;
    if (emptyView != null)
      emptyView.setClickable(lock);
  }
}
