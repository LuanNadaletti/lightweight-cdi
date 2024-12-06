package com.nadaletti.impl.core;

import java.lang.reflect.Field;

public class FieldDependency {
    private final Field field;
    private final Class<?> type;

    public FieldDependency(Field field, Class<?> type) {
        this.field = field;
        this.type = type;
    }

    public Field getField() {
        return field;
    }

    public Class<?> getType() {
        return type;
    }
}
