package com.nadaletti.impl.definition;

import java.lang.reflect.Constructor;

public class ConstructorDependency {

    private Constructor<?> constructor;
    private Class<?>[] constructorDependencies;

    public ConstructorDependency(Constructor<?> constructor, Class<?>[] constructorDependencies) {
        this.constructor = constructor;
        this.constructorDependencies = constructorDependencies;
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

}
