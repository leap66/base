package com.leap.base.mgr.logger;

/**
 * Created by weiyl on 2017/4/15.
 */

public interface BaseDestination {
  void send(LogLevel level, String msg, String thread, String file, String function, int line);
}
