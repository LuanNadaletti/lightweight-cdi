package com.nadaletti.impl.processor;

import java.lang.annotation.Annotation;
import java.util.Map;

import com.nadaletti.impl.annotation.Singleton;
import com.nadaletti.impl.definition.ComponentMetadataDefinition;
import com.nadaletti.impl.definition.Scope;

public class ComponentMetadataProcessor {

    private static final Map<Class<? extends Annotation>, Scope> scopeMapping = Map.of(Singleton.class, Scope.SINGLETON);

    public static ComponentMetadataDefinition process(Class<?> component) {
        ComponentMetadataDefinition metadata = new ComponentMetadataDefinition();

        processScope(component, metadata);

        return metadata;
    }

    private static void processScope(Class<?> clazz, ComponentMetadataDefinition metadata) {
        scopeMapping.forEach((annotation, scope) -> {
            if (clazz.isAnnotationPresent(annotation)) {
                metadata.setScope(scope);
            }
        });
    }
}
