package cdi.discovery;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cdi.inject.Injectable;

public class AnnotationProcessor {

    public static List<Class<?>> getInjectableAnnotatedClasses(Set<Class<?>> classes) {
        List<Class<?>> annotatedClasses = new ArrayList<>();

        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Injectable.class)) {
                annotatedClasses.add(clazz);
            }
        }

        return annotatedClasses;
    }
}
