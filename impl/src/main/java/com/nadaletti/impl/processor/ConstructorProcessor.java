package com.nadaletti.impl.processor;

import java.lang.reflect.Constructor;

import com.nadaletti.impl.annotation.Inject;
import com.nadaletti.impl.definition.ConstructorDependency;

public class ConstructorProcessor {

    public static ConstructorDependency process(Class<?> clazz) {
        if (clazz.isInterface()) {
            return null;
        }

        Constructor<?> injectConstructor = findInjectConstructor(clazz);

        if (injectConstructor != null) {
            return new ConstructorDependency(injectConstructor, injectConstructor.getParameterTypes());
        }

        try {
            Constructor<?> defaultConstructor = clazz.getDeclaredConstructor();
            return new ConstructorDependency(defaultConstructor, new Class<?>[0]);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private static Constructor<?> findInjectConstructor(Class<?> clazz) {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                return constructor;
            }
        }
        return null;
    }
}