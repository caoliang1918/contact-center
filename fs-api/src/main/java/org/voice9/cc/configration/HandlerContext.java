package org.voice9.cc.configration;

import java.util.Map;

/**
 * Create by caoliang on 2020/8/23
 */
public class HandlerContext {

    private Map<String, Class> handlerMap;

    public HandlerContext(Map<String, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }


    public Handler getInstance(String type) {
        Class clazz = handlerMap.get(type);
        if (clazz == null) {
            return null;
        }
        return (Handler) BeanUtil.getBean(clazz);
    }



}
