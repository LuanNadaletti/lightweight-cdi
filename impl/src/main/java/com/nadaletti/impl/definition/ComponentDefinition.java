package com.nadaletti.impl.definition;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ComponentDefinition {

    private final Class<?> componentClass;
    private Constructor<?> constructor;
    private Class<?>[] constructorDependencies;
    private List<FieldDependency> fieldDependencies = new ArrayList<>();
    private ComponentMetadataDefinition metadata;

    public ComponentDefinition(Class<?> componentClass) {
        this.componentClass = componentClass;
    }

    public boolean hasConstructor() {
        return constructor != null;
    }

    public Class<?> getComponentClass() {
        return componentClass;
    }

    public Constructor<?> getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor<?> constructor) {
        this.constructor = constructor;
    }

    public Class<?>[] getConstructorDependencies() {
        return constructorDependencies;
    }

    public void setConstructorDependencies(Class<?>[] constructorDependencies) {
        this.constructorDependencies = constructorDependencies;
    }

    public List<FieldDependency> getFieldDependencies() {
        return fieldDependencies;
    }

    public void addFieldDependency(FieldDependency dependency) {
        this.fieldDependencies.add(dependency);
    }

    public ComponentMetadataDefinition getMetadata() {
        return metadata;
    }

    public void setMetadata(ComponentMetadataDefinition metadata) {
        this.metadata = metadata;
    }
}
