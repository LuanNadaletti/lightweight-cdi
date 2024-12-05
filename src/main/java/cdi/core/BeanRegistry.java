package cdi.core;

import cdi.lifecycle.Scope;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanRegistry {

    private final Map<Class<?>, Object> singletonBeans = new HashMap<>();
    private final Map<Class<?>, BeanDefinition> beanDefinitions = new HashMap<>();

    public void initialize() {
        for (BeanDefinition definition : beanDefinitions.values()) {
            if (definition.getScope() == Scope.SINGLETON) {
                try {
                    Object bean = createBean(definition);
                    singletonBeans.put(definition.getBeanClass(), bean);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to initialize bean: " + definition.getBeanClass().getName(), e);
                }
            }
        }
    }

    public void register(BeanDefinition definition) {
        beanDefinitions.put(definition.getBeanClass(), definition);
    }

    public Object getBean(Class<?> clazz) {
        if (singletonBeans.containsKey(clazz)) {
            return singletonBeans.get(clazz);
        }

        if (beanDefinitions.containsKey(clazz)) {
            try {
                return createBean(beanDefinitions.get(clazz));
            } catch (Exception e) {
                throw new RuntimeException("Failed to create bean: " + clazz.getName(), e);
            }
        }

        throw new RuntimeException("No bean definition found for class: " + clazz.getName());
    }

    private Object createBean(BeanDefinition definition) {
        try {
            Object bean = createInstance(definition);
            injectFieldDependencies(bean, definition);

            return bean;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create bean: " + definition.getBeanClass().getName(), e);
        }
    }

    private Object createInstance(BeanDefinition definition) throws Exception {
        Constructor<?> constructor = definition.getConstructor();
        Class<?>[] dependencies = definition.getConstructorDependencies();

        Object[] resolvedDependencies = new Object[dependencies.length];
        for (int i = 0; i < dependencies.length; i++) {
            resolvedDependencies[i] = getBean(dependencies[i]);
        }

        return constructor.newInstance(resolvedDependencies);
    }

    private void injectFieldDependencies(Object bean, BeanDefinition definition) throws IllegalAccessException {
        for (FieldDependency fieldDependency : definition.getFieldDependencies()) {
            Field field = fieldDependency.getField();
            field.setAccessible(true);
            field.set(bean, getBean(fieldDependency.getType()));
        }
    }
}
