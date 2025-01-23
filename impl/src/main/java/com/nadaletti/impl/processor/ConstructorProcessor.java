package com.nadaletti.impl.processor;

import java.lang.reflect.Constructor;

import com.nadaletti.impl.annotation.Inject;
import com.nadaletti.impl.definition.BeanDefinition;

public class ConstructorProcessor {

    public static void processConstructor(Class<?> clazz, BeanDefinition definition) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        Constructor<?> injectConstructor = null;

        for (Constructor<?> constructor : constructors) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                injectConstructor = constructor;
                break;
            }
        }

        if (injectConstructor != null) {
            definition.setConstructor(injectConstructor);
            definition.setConstructorDependencies(injectConstructor.getParameterTypes());
        } else {
            try {
                Constructor<?> defaultConstructor = clazz.getDeclaredConstructor();
                definition.setConstructor(defaultConstructor);
                definition.setConstructorDependencies(new Class<?>[0]);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("No suitable constructor found for class: " + clazz.getName(), e);
            }
        }
    }
}
