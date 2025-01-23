package com.nadaletti.impl.container;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationRegistry {

    private static final ConfigurationRegistry instance = new ConfigurationRegistry();
    private final Map<String, Object> configurations = new HashMap<>();

    private ConfigurationRegistry() {
    }

    public static ConfigurationRegistry getInstance() {
        return instance;
    }

    public void register(String key, Object value) {
        configurations.put(key, value);
    }

    public Object get(String key) {
        return configurations.get(key);
    }

    public <T> T get(String key, Class<T> type) {
        return type.cast(configurations.get(key));
    }
}
