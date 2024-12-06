package com.nadaletti.impl.core;

import java.util.List;

import com.nadaletti.impl.discovery.AnnotationProcessor;
import com.nadaletti.impl.discovery.ClassScanner;

public class BeanContainer {
    private static final BeanRegistry registry = new BeanRegistry();

    public static void initialize(String basePackage) {
        ClassScanner scanner = new ClassScanner();
        AnnotationProcessor processor = new AnnotationProcessor();

        List<Class<?>> beanClasses = scanner.scanBeanAnnotatedClasses(basePackage);
        for (Class<?> clazz : beanClasses) {
            BeanDefinition definition = null;
            definition = processor.process(clazz);
            registry.register(definition);
        }

        registry.initialize();
    }

    public static Object getBean(Class<?> clazz) {
        return registry.getBean(clazz);
    }
}
