package org.zhongweixian.cc.configration;

/**
 * Create by caoliang on 2020/8/23
 */
public interface Handler<T> {

    void handleEvent(T event);

}
