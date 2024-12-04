package cdi.core;

import java.util.List;

import cdi.lifecycle.Scope;

public class BeanDefinition {
    protected Class<?> clazz;
    protected Scope scope;
    protected List<Class<?>> dependencies;
}
