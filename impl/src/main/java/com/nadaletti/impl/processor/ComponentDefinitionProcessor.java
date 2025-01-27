package com.nadaletti.impl.processor;

import com.nadaletti.impl.definition.ComponentDefinition;

public class ComponentDefinitionProcessor {

    public static ComponentDefinition process(Class<?> clazz) {
        ComponentDefinition definition = new ComponentDefinition(clazz);

        definition.setMetadata(ComponentMetadataProcessor.process(clazz));
        definition.setConstructorDependency(ConstructorProcessor.process(clazz));
        definition.setFieldDependencies(FieldProcessor.process(clazz));

        return definition;
    }
}
