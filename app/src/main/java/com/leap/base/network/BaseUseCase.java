package com.leap.base.network;

import com.leap.base.network.date.AuthEvent;
import com.leap.base.network.date.TokenExpiredException;
import com.trello.rxlifecycle.LifecycleProvider;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * BaseUseCase : BaseUseCase : 用户级基础请求实例
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public abstract class BaseUseCase<T> {
  private Subscription subscription = Subscriptions.empty();

  // 构建UseCase
  protected abstract Observable buildUseCaseObservable();

  public void subscribe(LifecycleProvider provider, Subscriber useCaseSubscriber) {
    Observable temp = buildUseCaseObservable()//
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .onErrorResumeNext(new Func1<Throwable, Observable>() {
          @Override
          public Observable call(Throwable throwable) {
            if (throwable instanceof TokenExpiredException) {
              EventBus.getDefault().post(new AuthEvent(AuthEvent.TOKEN_EXPIRED));
              unSubscribe();
              return Observable.empty();
            } else {
              return Observable.error(throwable);
            }
          }
        });
    if (provider != null)
      temp.compose(provider.bindToLifecycle());
    this.subscription = temp.subscribe(useCaseSubscriber);
  }

  /**
   * 取消订阅
   */
  public void unSubscribe() {
    if (subscription != null && !subscription.isUnsubscribed())
      subscription.unsubscribe();
  }

  protected T platformApiClient() {
    return ApiClient.platformClient().create(getType());
  }

  public T updateClient() {
    return UpdateClient.updateClient().create(getType());
  }

  protected Class<T> getType() {
    Class<T> entityClass;
    Type t = getClass().getGenericSuperclass();
    Type[] p = ((ParameterizedType) t).getActualTypeArguments();
    entityClass = (Class<T>) p[0];
    return entityClass;
  }
}
