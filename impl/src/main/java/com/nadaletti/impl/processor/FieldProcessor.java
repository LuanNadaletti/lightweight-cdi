package com.nadaletti.impl.processor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.nadaletti.impl.annotation.Inject;
import com.nadaletti.impl.definition.FieldDependency;
import com.nadaletti.impl.exception.DependencyInjectionException;

public class FieldProcessor {

    public static List<FieldDependency> process(Class<?> clazz) {
        List<FieldDependency> dependencies = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                    throw new DependencyInjectionException("Field " + field.getName() + " in class " + clazz.getName() +
                        " cannot be static or final for dependency injection.");
                }
                dependencies.add(new FieldDependency(field, field.getType()));
            }
        }

        return dependencies;
    }

}