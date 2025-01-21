package com.nadaletti.impl.processor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import com.nadaletti.impl.definition.BeanDefinition;
import com.nadaletti.impl.definition.FieldDependency;
import com.nadaletti.impl.definition.Scope;
import com.nadaletti.impl.inject.Inject;
import com.nadaletti.impl.lifecycle.Singleton;

public class BeanDefinitionProcessor {

    public BeanDefinition process(Class<?> clazz) {
        BeanDefinition definition = new BeanDefinition(clazz);

        if (clazz.isAnnotationPresent(Singleton.class)) {
            definition.setScope(Scope.SINGLETON);
        } else {
            definition.setScope(Scope.PROTOTYPE);
        }

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

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                definition.addFieldDependency(new FieldDependency(field, field.getType()));
            }
        }

        return definition;
    }
}
