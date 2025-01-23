package com.nadaletti.impl.container;

import java.util.List;

import com.nadaletti.impl.discovery.ClassScanner;
import com.nadaletti.impl.exception.ComponentException;
import com.nadaletti.impl.processor.ComponentDefinitionProcessor;
import com.nadaletti.impl.processor.ConfigurationProcessor;

public class ApplicationContainer {

    private static final ComponentRegistry componentRegistry = new ComponentRegistry();
    private static final ConfigurationRegistry configurationRegistry = ConfigurationRegistry.getInstance();

    public static void initialize(String basePackage) {
        ConfigurationProcessor.processConfigurations(basePackage);

        ClassScanner scanner = new ClassScanner();
        ComponentDefinitionProcessor processor = new ComponentDefinitionProcessor();

        List<Class<?>> componentClasses = scanner.scanComponentAnnotatedClasses(basePackage);
        for (Class<?> clazz : componentClasses) {
            componentRegistry.register(processor.process(clazz));
        }

        componentRegistry.initialize();
    }

    public static <T> T getComponent(Class<T> clazz) throws ComponentException {
        return clazz.cast(componentRegistry.getComponent(clazz));
    }

    public static <T> T getConfigValue(String key, Class<T> type) {
        return configurationRegistry.get(key, type);
    }

    public static Object getConfigValue(String key) {
        return configurationRegistry.get(key);
    }
}
