package com.nadaletti.impl.processor;

import com.nadaletti.impl.definition.BeanDefinition;

public class BeanDefinitionProcessor {

    public BeanDefinition process(Class<?> clazz) {
        BeanDefinition definition = new BeanDefinition(clazz);

        ScopeProcessor.processScope(clazz, definition);
        ConstructorProcessor.processConstructor(clazz, definition);
        FieldProcessor.processFields(clazz, definition);

        return definition;
    }
}
