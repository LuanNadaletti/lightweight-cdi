package cdi.core;

import java.util.List;

import cdi.discovery.AnnotationProcessor;
import cdi.discovery.ClassScanner;

public class BeanContainer {

    private BeanRegistry registry = new BeanRegistry();

    void initialize() {
        List<Class<?>> annotatedClasses = AnnotationProcessor.getInjectableAnnotatedClasses(ClassScanner.scan("test"));

        for (Class<?> clazz : annotatedClasses) {
            registry.registry(clazz);
        }
    }
}
