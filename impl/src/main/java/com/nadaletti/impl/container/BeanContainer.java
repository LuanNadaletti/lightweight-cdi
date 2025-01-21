package com.nadaletti.impl.container;

import java.util.List;

import com.nadaletti.impl.definition.BeanDefinition;
import com.nadaletti.impl.discovery.ClassScanner;
import com.nadaletti.impl.exception.BeanException;
import com.nadaletti.impl.processor.BeanDefinitionProcessor;

public class BeanContainer {

    private static final BeanRegistry registry = new BeanRegistry();

    public static void initialize(String basePackage) {
        ClassScanner scanner = new ClassScanner();
        BeanDefinitionProcessor processor = new BeanDefinitionProcessor();

        List<Class<?>> beanClasses = scanner.scanBeanAnnotatedClasses(basePackage);
        for (Class<?> clazz : beanClasses) {
            BeanDefinition definition = null;
            definition = processor.process(clazz);
            registry.register(definition);
        }

        registry.initialize();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) throws BeanException {
        return (T) registry.getBean(clazz);
    }
}
