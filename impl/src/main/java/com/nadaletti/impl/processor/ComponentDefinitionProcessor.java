package com.nadaletti.impl.processor;

import com.nadaletti.impl.definition.ComponentDefinition;

public class ComponentDefinitionProcessor {

    public ComponentDefinition process(Class<?> clazz) {
        ComponentDefinition definition = new ComponentDefinition(clazz);

        ScopeProcessor.processScope(clazz, definition);
        ConstructorProcessor.processConstructor(clazz, definition);
        FieldProcessor.processFields(clazz, definition);

        return definition;
    }
}
