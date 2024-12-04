package cdi.discovery;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cdi.inject.Inject;
import cdi.inject.Injectable;

public class ClassScanner {

    private static List<Class<?>> BEAN_ANNOTATIONS = Arrays.asList(Injectable.class);
    private static List<Class<?>> DEPENDENCY_ANNOTATIONS = Arrays.asList(Inject.class);



    public List<Class<?>> scanBeanAnnotatedClasses(String packageName) {
        return scan(packageName).stream()
                .filter(clazz -> isClassContainsBeanAnnotation(clazz))
                .collect(Collectors.toList());
    }

    public List<Class<?>> scanClassesWithDependencies(String packageName) {
        return scan(packageName).stream()
                .filter(clazz -> isClassContainsDependencyAnnotation(clazz))
                .collect(Collectors.toList());
    }

    private static Set<Class<?>> scan(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }


    private static Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + className + " not founded: ");
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static boolean isClassContainsBeanAnnotation(Class<?> clazz) {
        for (Class<?> annotationClass : BEAN_ANNOTATIONS) {
            if (clazz.isAnnotationPresent((Class<? extends Annotation>) annotationClass)) return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private static boolean isClassContainsDependencyAnnotation(Class<?> clazz) {
        for (Class<?> annotationClass : DEPENDENCY_ANNOTATIONS) {
            for (Field field : clazz.getFields()) {
                if (field.isAnnotationPresent((Class<? extends Annotation>) annotationClass)) return true;
            }
        }
        return false;
    }
}
