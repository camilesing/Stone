package com.camile.ast;

import java.util.Iterator;

/**
 * @author camile
 */
abstract class AbstractTree implements Iterable<AbstractTree> {
    /**
     * return the child of the index on children
     * */
    public abstract AbstractTree child(int i);
    /**
     * return count of children
     * */
    public abstract int numChildren();
    /**
     * 返回一个用来遍历子节点的iterator
     * */
    public abstract Iterator<AbstractTree> children();
    public abstract String location();
    public Iterator<AbstractTree> iterator() { return children(); }
}