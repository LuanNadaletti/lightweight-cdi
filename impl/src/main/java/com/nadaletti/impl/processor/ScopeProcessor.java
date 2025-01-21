package com.nadaletti.impl.processor;

import java.lang.annotation.Annotation;
import java.util.Map;

import com.nadaletti.impl.definition.BeanDefinition;
import com.nadaletti.impl.definition.Scope;
import com.nadaletti.impl.lifecycle.Singleton;

public class ScopeProcessor {

    private static final Map<Class<? extends Annotation>, Scope> scopeMapping = Map.of(Singleton.class, Scope.SINGLETON);

    public static void processScope(Class<?> clazz, BeanDefinition definition) {
        scopeMapping.forEach((annotation, scope) -> {
            if (clazz.isAnnotationPresent(annotation)) {
                definition.setScope(scope);
            }
        });
    }
}
