package cdi.core;

import cdi.discovery.AnnotationProcessor;
import cdi.discovery.ClassScanner;

import java.util.List;

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
