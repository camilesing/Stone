package com.camile.ast;

import com.camile.Environment;

import java.util.Iterator;

/**
 * @author camile
 */
public abstract class AbstractTree implements Iterable<AbstractTree> {
    /**
     * return the child of the index on children
     */
    public abstract AbstractTree child(int i);

    /**
     * return count of children
     */
    public abstract int numChildren();

    /**
     * 返回一个用来遍历子节点的iterator
     */
    public abstract Iterator<AbstractTree> children();

    public abstract String location();

    public abstract Object evaluate(Environment e);

    public Iterator<AbstractTree> iterator() {
        return children();
    }
}