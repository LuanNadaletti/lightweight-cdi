package cdi.core;

import java.util.HashMap;
import java.util.Map;

public class BeanRegistry {

    private Map<Class<?>, Object> beans = new HashMap<>();

    public void registry(Class<?> clazz) {
        beans.put(clazz, null);
    }
}
