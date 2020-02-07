package com.camile;

import java.util.HashMap;

/**
 * @author camile
 */
public class BasicEnv implements Environment {
    /**
     * k变量名
     * v 变量值
     */
    protected HashMap<String, Object> values = new HashMap<>();

    @Override
    public void put(String name, Object value) {
        values.put(name, value);
    }

    @Override
    public Object get(String name) {
        return values.get(name);
    }
}
