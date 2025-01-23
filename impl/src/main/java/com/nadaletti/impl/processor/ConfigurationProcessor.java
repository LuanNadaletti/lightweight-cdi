package com.nadaletti.impl.processor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.nadaletti.impl.annotation.ConfigProperty;
import com.nadaletti.impl.annotation.Configuration;
import com.nadaletti.impl.container.ConfigurationRegistry;
import com.nadaletti.impl.discovery.ClassScanner;

public class ConfigurationProcessor {

    public static void processConfigurations(String basePackage) {
        ClassScanner scanner = new ClassScanner();
        List<Class<?>> configClasses = scanner.scanAnnotatedClasses(basePackage, Configuration.class);

        for (Class<?> configClass : configClasses) {
            processConfigurationClass(configClass, ConfigurationRegistry.getInstance());
        }
    }

    private static void processConfigurationClass(Class<?> configClass, ConfigurationRegistry registry) {
        try {
            Object configInstance = configClass.getDeclaredConstructor().newInstance();
            processConfigPropertyFields(configClass, configInstance, registry);
            processConfigPropertyMethods(configClass, configInstance, registry);
        } catch (Exception e) {
            throw new RuntimeException("Error processing the configuration: " + configClass.getName(), e);
        }
    }

    private static void processConfigPropertyFields(Class<?> configClass, Object configInstance, ConfigurationRegistry registry) throws IllegalArgumentException, IllegalAccessException {
        for (Field field : configClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(ConfigProperty.class)) {
                field.setAccessible(true);
                try {
                    ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
                    String key = annotation.key().isEmpty() ? field.getName() : annotation.key();
                    Object value = field.get(configInstance);
                    registry.register(key, value);
                } catch (IllegalAccessException e) {
                    System.err.println("Failed to process field: " + field.getName() + " in " + configClass.getName());
                }
            }
        }
    }

    private static void processConfigPropertyMethods(Class<?> configClass, Object configInstance, ConfigurationRegistry registry) throws InvocationTargetException, IllegalAccessException, IllegalArgumentException {
        for (Method method : configClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ConfigProperty.class)) {
                try {
                    ConfigProperty annotation = method.getAnnotation(ConfigProperty.class);
                    String key = annotation.key().isEmpty() ? method.getName() : annotation.key();
                    Object value = method.invoke(configInstance);
                    registry.register(key, value);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    System.err.println("Failed to process method: " + method.getName() + " in " + configClass.getName());
                }
            }
        }
    }
}
