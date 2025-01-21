package com.nadaletti.impl.processor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.nadaletti.impl.definition.BeanDefinition;
import com.nadaletti.impl.definition.FieldDependency;
import com.nadaletti.impl.inject.Inject;

public class FieldProcessor {

    public static void processFields(Class<?> clazz, BeanDefinition definition) {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                    throw new RuntimeException("Field " + field.getName() + " in class " + clazz.getName() +
                        " cannot be static or final for dependency injection.");
                }
                definition.addFieldDependency(new FieldDependency(field, field.getType()));
            }
        }
    }
}