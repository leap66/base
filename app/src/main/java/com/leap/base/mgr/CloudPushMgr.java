package com.leap.base.mgr;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.alibaba.sdk.android.push.notification.BasicCustomPushNotification;
import com.alibaba.sdk.android.push.notification.CustomNotificationBuilder;
import com.leap.base.util.IsEmpty;

public class CloudPushMgr {
  private static final String TAG = CloudPushMgr.class.getSimpleName();

  public static void initCloudChannel(final int icon) {
    Context context = ContextMgr.getInstance();
    PushServiceFactory.init(context);
    CloudPushService pushService = PushServiceFactory.getCloudPushService();
    pushService.register(context, new CustomCallback() {
      @Override
      public void onSuccess(String s) {
        super.onSuccess(s);
        setCustomNotification(icon);
      }
    });
  }

  public static void binds(String account, String alias) {
    CloudPushMgr.bindAccount(account);
    CloudPushMgr.bindAlias(alias);
  }

  public static void unBinds() {
    CloudPushMgr.unBindAccount();
    CloudPushMgr.unBindAlias();
  }

  public static void bindAccount(String account) {
    if (!IsEmpty.string(account)) {
      PushServiceFactory.getCloudPushService().bindAccount(account, new CustomCallback());
    }
  }

  public static void unBindAccount() {
    PushServiceFactory.getCloudPushService().unbindAccount(new CustomCallback());
  }

  public static void bindAlias(String alias) {
    if (!IsEmpty.string(alias)) {
      PushServiceFactory.getCloudPushService().addAlias(alias, new CustomCallback());
    }
  }

  public static void unBindAlias() {
    PushServiceFactory.getCloudPushService().removeAlias(null, new CustomCallback());
  }

  private static void setCustomNotification(int icon) {
    BasicCustomPushNotification notification = new BasicCustomPushNotification();
    notification.setRemindType(BasicCustomPushNotification.REMIND_TYPE_SOUND);
    notification.setStatusBarDrawable(icon);
    notification.setBuildWhenAppInForeground(false);
    CustomNotificationBuilder.getInstance().setCustomNotification(1, notification);
  }

  private static class CustomCallback implements CommonCallback {
    @Override
    public void onSuccess(String s) {
      Log.e(TAG, "成功---" + s);
    }

    @Override
    public void onFailed(String s, String s1) {
      Log.e(TAG, "init cloudchannel failed -- errorcode:" + s + " -- errorMessage:" + s1);
    }
  }

}
