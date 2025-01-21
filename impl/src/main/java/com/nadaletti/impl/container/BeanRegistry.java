package com.nadaletti.impl.container;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.nadaletti.impl.definition.BeanDefinition;
import com.nadaletti.impl.definition.FieldDependency;
import com.nadaletti.impl.definition.Scope;
import com.nadaletti.impl.exception.BeanCreationException;
import com.nadaletti.impl.exception.BeanDefinitionNotFoundException;
import com.nadaletti.impl.lifecycle.Initializable;

public class BeanRegistry {

    private final Map<Class<?>, BeanDefinition> beanDefinitions = new HashMap<>();
    private final Map<Class<?>, Object> singletonBeans = new HashMap<>();
    private final Map<Class<?>, Object> earlySingletonObjects = new HashMap<>();

    public void initialize() {
        for (BeanDefinition definition : beanDefinitions.values()) {
            if (definition.getScope() == Scope.SINGLETON) {
                getBean(definition.getBeanClass());
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

        if (earlySingletonObjects.containsKey(clazz)) {
            return earlySingletonObjects.get(clazz);
        }

        if (beanDefinitions.containsKey(clazz)) {
            return createBean(beanDefinitions.get(clazz));
        }

        throw new BeanDefinitionNotFoundException("No bean definition found for class: " + clazz.getName());
    }

    private Object createBean(BeanDefinition definition) {
        Class<?> beanClass = definition.getBeanClass();

        try {
            Object partialInstance = createEmptyInstance(beanClass);
            earlySingletonObjects.put(beanClass, partialInstance);

            injectFieldDependencies(partialInstance, definition);

            if (partialInstance instanceof Initializable) {
                ((Initializable) partialInstance).afterPropertiesSet();
            }

            singletonBeans.put(beanClass, partialInstance);
            earlySingletonObjects.remove(beanClass);

            return partialInstance;
        } catch (Exception e) {
            earlySingletonObjects.remove(beanClass);
            throw new BeanCreationException("Failed to create bean: " + beanClass.getName());
        }
    }

    private Object createEmptyInstance(Class<?> beanClass) throws Exception {
        Constructor<?> constructor = beanClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

    private void injectFieldDependencies(Object bean, BeanDefinition definition) throws Exception {
        for (FieldDependency fieldDependency : definition.getFieldDependencies()) {
            Field field = fieldDependency.getField();
            field.setAccessible(true);
            field.set(bean, getBean(fieldDependency.getType()));
        }
    }
}
