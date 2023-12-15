package org.voice9.cc.configration;

import org.voice9.FsApiApplication;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by caoliang on 2020/8/23
 */
@Component
public class HandlerProcessor implements BeanFactoryPostProcessor {

    private String handlerPackage = FsApiApplication.class.getPackage().getName();

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, Class> handlerMap = new HashMap<>();
        ClassScanerUtil.scan(handlerPackage, HandlerType.class).forEach(clazz -> {
            String type = clazz.getAnnotation(HandlerType.class).value();
            handlerMap.put(type, clazz);
        });
        HandlerContext context = new HandlerContext(handlerMap);
        beanFactory.registerSingleton(HandlerContext.class.getName(), context);
    }
}
