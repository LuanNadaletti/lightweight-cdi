package com.nadaletti.impl.processor;

import java.lang.annotation.Annotation;
import java.util.Map;

import com.nadaletti.impl.annotation.Singleton;
import com.nadaletti.impl.definition.ComponentDefinition;
import com.nadaletti.impl.definition.Scope;

public class ScopeProcessor {

    private static final Map<Class<? extends Annotation>, Scope> scopeMapping = Map.of(Singleton.class, Scope.SINGLETON);

    public static void processScope(Class<?> clazz, ComponentDefinition definition) {
        scopeMapping.forEach((annotation, scope) -> {
            if (clazz.isAnnotationPresent(annotation)) {
                definition.setScope(scope);
            }
        });
    }
}
