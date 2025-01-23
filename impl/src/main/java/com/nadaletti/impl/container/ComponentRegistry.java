package com.nadaletti.impl.container;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.nadaletti.impl.definition.ComponentDefinition;
import com.nadaletti.impl.definition.FieldDependency;
import com.nadaletti.impl.definition.Scope;
import com.nadaletti.impl.exception.ComponentCreationException;
import com.nadaletti.impl.exception.ComponentDefinitionNotFoundException;
import com.nadaletti.impl.lifecycle.Initializable;

public class ComponentRegistry {

    private final Map<Class<?>, ComponentDefinition> componentDefinitions = new HashMap<>();
    private final Map<Class<?>, Object> singletonComponents = new HashMap<>();
    private final Map<Class<?>, Object> earlySingletonComponents = new HashMap<>();

    public void initialize() {
        for (ComponentDefinition definition : componentDefinitions.values()) {
            if (definition.getMetadata().getScope() == Scope.SINGLETON) {
                getComponent(definition.getComponentClass());
            }
        }
    }

    public void register(ComponentDefinition definition) {
        componentDefinitions.put(definition.getComponentClass(), definition);
    }

    public Object getComponent(Class<?> clazz) {
        if (singletonComponents.containsKey(clazz)) {
            return singletonComponents.get(clazz);
        }

        if (earlySingletonComponents.containsKey(clazz)) {
            return earlySingletonComponents.get(clazz);
        }

        if (componentDefinitions.containsKey(clazz)) {
            return createComponent(componentDefinitions.get(clazz));
        }

        ComponentDefinition candidate = findCandidateComponent(clazz);
        if (candidate != null) {
            return createComponent(candidate);
        }

        throw new ComponentDefinitionNotFoundException("No component definition found for class: " + clazz.getName());
    }

    private Object createComponent(ComponentDefinition definition) {
        Class<?> componentClass = definition.getComponentClass();

        try {
            Object partialInstance = instantiateComponent(definition);
            earlySingletonComponents.put(componentClass, partialInstance);

            injectFieldDependencies(partialInstance, definition);

            if (partialInstance instanceof Initializable) {
                ((Initializable) partialInstance).afterPropertiesSet();
            }

            singletonComponents.put(componentClass, partialInstance);
            earlySingletonComponents.remove(componentClass);

            return partialInstance;
        } catch (Exception e) {
            earlySingletonComponents.remove(componentClass);
            throw new ComponentCreationException(
                    "Failed to create component: " + componentClass.getName() + "\n " + e.getMessage());
        }
    }

    private Object instantiateComponent(ComponentDefinition definition) throws Exception {
        if (definition.getConstructor() != null) {
            int parametersNumber = definition.getConstructorDependencies().length;
            Object[] initialParametersInstances = new Object[parametersNumber];

            for (int i = 0; i < parametersNumber; i++) {
                Object parameterInstance = getComponent(definition.getConstructorDependencies()[i]);
                initialParametersInstances[i] = parameterInstance;
                earlySingletonComponents.put(parameterInstance.getClass(), parameterInstance);
            }

            Constructor<?> constructor = definition.getConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance(initialParametersInstances);
        }

        return instantiateComponent(findCandidateComponent(definition.getComponentClass()));
    }

    private ComponentDefinition findCandidateComponent(Class<?> clazz) {
        List<ComponentDefinition> probableCandidates = componentDefinitions.entrySet().stream()
                .filter(entry -> clazz.isAssignableFrom(entry.getValue().getComponentClass()))
                .map(entry -> entry.getValue()).collect(Collectors.toList());

        if (probableCandidates == null) {
            throw new ComponentCreationException("No possible candidate component found to inject into: " + clazz.getSimpleName());
        }

        return probableCandidates.stream().filter(candidate -> candidate.getMetadata().isPrimary()).findFirst()
                .orElse(probableCandidates.get(0));
    }

    private Object createEmptyInstance(Class<?> componentClass) throws Exception {
        Constructor<?> constructor = componentClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

    private void injectFieldDependencies(Object component, ComponentDefinition definition) throws Exception {
        for (FieldDependency fieldDependency : definition.getFieldDependencies()) {
            Field field = fieldDependency.getField();
            field.setAccessible(true);
            field.set(component, getComponent(fieldDependency.getType()));

            System.out.println("Injected " + fieldDependency.getType().getSimpleName() + " field dependency at: "
                    + definition.getComponentClass().getSimpleName());
        }
    }
}
