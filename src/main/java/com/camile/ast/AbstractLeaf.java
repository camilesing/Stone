package com.camile.ast;

import com.camile.Token;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 叶节点类。
 * 没有子节点
 * */
public class AbstractLeaf extends AbstractTree {
    private static ArrayList<AbstractTree> empty = new ArrayList<AbstractTree>();
    protected Token token;

    public AbstractLeaf(Token t) {
        token = t;
    }

    @Override
    public AbstractTree child(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int numChildren() {
        return 0;
    }

    @Override
    public Iterator<AbstractTree> children() {
        return empty.iterator();
    }

    @Override
    public String toString() {
        return token.getText();
    }

    @Override
    public String location() {
        return "at line " + token.getLineNumber();
    }

    public Token token() {
        return token;
    }
}
