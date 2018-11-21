package com.leap.base.network.subscriber;

import android.content.Context;

import com.leap.base.mgr.log.LogUtil;
import com.leap.base.network.date.Response;
import com.leap.base.network.util.HttpUtil;
import com.leap.base.util.IsEmpty;
import com.leap.base.widget.LoadingDialog;

/**
 * HttpSubscriber : 网络返回数据解析
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public abstract class HttpSubscriber<T> extends rx.Subscriber<Response<T>> {
  private LoadingDialog loadingDialog;

  protected HttpSubscriber(Context context) {
    loadingDialog = new LoadingDialog(context);
  }

  protected HttpSubscriber() {
  }

  @Override
  public void onStart() {
    if (!IsEmpty.object(loadingDialog))
      loadingDialog.show();
    super.onStart();
  }

  @Override
  public void onCompleted() {
    if (isUnsubscribed())
      unsubscribe();
  }

  @Override
  public void onError(Throwable throwable) {
    if (!IsEmpty.object(loadingDialog) && loadingDialog.isShowing())
      loadingDialog.dismiss();
    onFailure(HttpUtil.parseThrowable(throwable), null);
    LogUtil.e(getClass().getSimpleName(), "error", throwable);
  }

  @Override
  public void onNext(Response<T> t) {
    if (!IsEmpty.object(loadingDialog) && loadingDialog.isShowing())
      loadingDialog.dismiss();
    if (IsEmpty.object(t)) {
      // 短信或阅读消息时response可能为null
      onSuccess(null);
      return;
    }
    if (t.isSuccess()) {
      onSuccess(t);
    } else {
      if (!IsEmpty.list(t.getMessage())) {
        onFailure(t.getMessage().get(0), t);
      } else {
        onFailure(null, t);
      }
    }
  }

  // 失败
  public abstract void onFailure(String errorMsg, Response<T> response);

  // 成功
  public abstract void onSuccess(Response<T> response);

}
