package com.leap.base.network.subscriber;

import android.content.Context;

import com.leap.base.network.date.SummaryResponse;
import com.leap.base.network.util.HttpUtil;
import com.leap.base.util.IsEmpty;

/**
 * SummarySubscriber :
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public abstract class SummarySubscriber<T, S> extends rx.Subscriber<SummaryResponse<T, S>> {
  private Context context;

  public SummarySubscriber(Context context) {
    this.context = context;
  }

  @Override
  public void onCompleted() {
    if (isUnsubscribed())
      unsubscribe();
  }

  @Override
  public void onError(Throwable throwable) {
    throwable.printStackTrace();
    onFailure(HttpUtil.parseThrowable(throwable), null);
  }

  @Override
  public void onNext(SummaryResponse<T, S> t) {
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

  public abstract void onFailure(String errorMsg, SummaryResponse<T, S> response);

  public abstract void onSuccess(SummaryResponse<T, S> resp);

}
