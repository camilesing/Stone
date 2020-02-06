package com.camile;

/**
 * @author camile
 * 记录变量名称与值对应的数据结构
 */
public interface Environment {
    void put(String name, Object value);

    Object get(String name);
}
