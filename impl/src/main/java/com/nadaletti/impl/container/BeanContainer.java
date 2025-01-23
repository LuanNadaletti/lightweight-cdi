package com.nadaletti.impl.container;

import java.util.List;

import com.nadaletti.impl.discovery.ClassScanner;
import com.nadaletti.impl.exception.BeanException;
import com.nadaletti.impl.processor.BeanDefinitionProcessor;
import com.nadaletti.impl.processor.ConfigurationProcessor;

public class BeanContainer {

    private static final BeanRegistry registry = new BeanRegistry();
    private static ConfigurationRegistry configurationRegistry = ConfigurationRegistry.getInstance();

    public static void initialize(String basePackage) {
        ConfigurationProcessor.processConfigurations(basePackage);

        ClassScanner scanner = new ClassScanner();
        BeanDefinitionProcessor processor = new BeanDefinitionProcessor();

        List<Class<?>> beanClasses = scanner.scanBeanAnnotatedClasses(basePackage);
        for (Class<?> clazz : beanClasses) {
            registry.register(processor.process(clazz));
        }

        registry.initialize();
    }

    public static <T> T getBean(Class<T> clazz) throws BeanException {
        return clazz.cast(registry.getBean(clazz));
    }

    public static <T> T getConfigValue(String key, Class<T> type) {
        return configurationRegistry.get(key, type);
    }

    public static Object getConfigValue(String key) {
        return configurationRegistry.get(key);
    }
}
