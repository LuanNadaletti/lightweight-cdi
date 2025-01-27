package com.nadaletti.impl.definition;

import java.util.ArrayList;
import java.util.List;

public class ComponentDefinition {

    private final Class<?> componentClass;
    private ConstructorDependency constructorDependency;
    private List<FieldDependency> fieldDependencies = new ArrayList<>();
    private ComponentMetadataDefinition metadata;

    public ComponentDefinition(Class<?> componentClass) {
        this.componentClass = componentClass;
    }

    public boolean hasConstructor() {
        return constructorDependency != null;
    }

    public void addFieldDependency(FieldDependency dependency) {
        this.fieldDependencies.add(dependency);
    }

    public Class<?> getComponentClass() {
        return componentClass;
    }

    public ConstructorDependency getConstructorDependency() {
        return constructorDependency;
    }

    public void setConstructorDependency(ConstructorDependency constructorDependency) {
        this.constructorDependency = constructorDependency;
    }

    public List<FieldDependency> getFieldDependencies() {
        return fieldDependencies;
    }

    public void setFieldDependencies(List<FieldDependency> fieldDependencies) {
        this.fieldDependencies = fieldDependencies;
    }

    public ComponentMetadataDefinition getMetadata() {
        return metadata;
    }

    public void setMetadata(ComponentMetadataDefinition metadata) {
        this.metadata = metadata;
    }
}
