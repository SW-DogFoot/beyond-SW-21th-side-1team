package com.mini.timedeal.config;


import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class AppContext {
    private static final AppContext instance = new AppContext();
    private final Map<Class<?>, Object> beans = new HashMap<Class<?>, Object>();

    private AppContext() {

    }

    public static synchronized AppContext getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> beanClass) {
        Object bean = beans.get(beanClass);
        if (bean == null) {
            throw new RuntimeException("등록되지 않은 빈: " + beanClass.getName());
        }
        return (T) bean;
    }

    public void registerBean(Class<?> beanClass, Class<?>... dependencies) {
        try {

            Object object = null;
            if (dependencies.length == 0) {
                object = beanClass.getConstructor().newInstance();
            } else {
                Object[] dependencyInstances = new Object[dependencies.length];
                for (int i = 0; i < dependencies.length; i++) {
                    dependencyInstances[i] = getBean(dependencies[i]);
                }
                Constructor<?> constructor = beanClass.getDeclaredConstructor(dependencies);
                object = constructor.newInstance(dependencyInstances);
            }

            beans.put(beanClass, object);

            for (Class<?> interfaceClass : beanClass.getInterfaces()) {
                beans.put(interfaceClass, object);
            }

        } catch (Exception e) {
            throw new RuntimeException("빈 생성 실패: " + beanClass.getName(), e);
        }
    }

    public void registerBean(Object object) {
        Class<?> beanClass = object.getClass();
        beans.put(beanClass, object);

        for (Class<?> interfaceClass : beanClass.getInterfaces()) {
            beans.put(interfaceClass, object);
        }
    }
}
