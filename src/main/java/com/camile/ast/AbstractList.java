package com.camile.ast;

import com.camile.Environment;
import com.camile.exception.StoneException;

import java.util.Iterator;
import java.util.List;

/**
 * 非叶节点对象的类。可能包含多个子节点
 */
public class AbstractList extends AbstractTree {
    protected List<AbstractTree> children;

    public AbstractList(List<AbstractTree> list) {
        children = list;
    }

    @Override
    public AbstractTree child(int i) {
        return children.get(i);
    }

    @Override
    public int numChildren() {
        return children.size();
    }

    @Override
    public Iterator<AbstractTree> children() {
        return children.iterator();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        String sep = "";
        for (AbstractTree t : children) {
            builder.append(sep);
            sep = " ";
            builder.append(t.toString());
        }
        return builder.append(')').toString();
    }

    @Override
    public String location() {
        for (AbstractTree t : children) {
            String s = t.location();
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    @Override
    public Object evaluate(Environment e) {
        throw new StoneException("cannot eval: " + toString());
    }
}
