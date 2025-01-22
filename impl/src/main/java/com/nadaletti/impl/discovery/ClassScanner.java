package com.nadaletti.impl.discovery;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.nadaletti.impl.inject.Inject;
import com.nadaletti.impl.inject.Injectable;

public class ClassScanner {

    private static final List<Class<? extends Annotation>> BEAN_ANNOTATIONS = Arrays.asList(Injectable.class);
    private static final List<Class<? extends Annotation>> DEPENDENCY_ANNOTATIONS = Arrays.asList(Inject.class);

    public List<Class<?>> scanAnnotatedClasses(String packageName, Class<? extends Annotation> annotation) {
        return scan(packageName).stream()
                .filter(clazz -> clazz.isAnnotationPresent(annotation))
                .collect(Collectors.toList());
    }

    public List<Class<?>> scanBeanAnnotatedClasses(String packageName) {
        return scan(packageName).stream()
                .filter(this::containsBeanAnnotation)
                .collect(Collectors.toList());
    }

    public List<Class<?>> scanClassesWithDependencies(String packageName) {
        return scan(packageName).stream()
                .filter(this::containsDependencyAnnotation)
                .collect(Collectors.toList());
    }

    private Set<Class<?>> scan(String packageName) {
        Set<Class<?>> classes = new HashSet<>();
        String path = packageName.replace('.', '/');
        URL resource = ClassLoader.getSystemClassLoader().getResource(path);

        if (resource == null) {
            throw new RuntimeException("Package not found: " + packageName);
        }

        File directory = new File(resource.getFile());
        if (directory.exists()) {
            findClasses(directory, packageName, classes);
        }

        return classes;
    }

    private void findClasses(File directory, String packageName, Set<Class<?>> classes) {
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                findClasses(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().lastIndexOf('.'));
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    System.err.println("Class not found: " + className);
                }
            }
        }
    }

    private boolean containsBeanAnnotation(Class<?> clazz) {
        return BEAN_ANNOTATIONS.stream().anyMatch(clazz::isAnnotationPresent);
    }

    private boolean containsDependencyAnnotation(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) { // Inclui campos privados
            if (DEPENDENCY_ANNOTATIONS.stream().anyMatch(field::isAnnotationPresent)) {
                return true;
            }
        }
        return false;
    }
}
