package com.nadaletti.impl.definition;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class BeanDefinition {

    private final Class<?> beanClass;
    private Scope scope;
    private Constructor<?> constructor;
    private Class<?>[] constructorDependencies;
    private List<FieldDependency> fieldDependencies = new ArrayList<>();
    private String qualifier;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
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

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

}
