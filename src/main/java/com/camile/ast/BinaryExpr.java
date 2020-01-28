package com.camile.ast;

import java.util.List;

/**
 * 操作符节点
 * */
public class BinaryExpr extends AbstractList {
    public BinaryExpr(List<AbstractTree> c) {
        super(c);
    }

    public AbstractTree left() {
        return child(0);
    }

    public String operator() {
        return ((AbstractLeaf) child(1)).token().getText();
    }

    public AbstractTree right() {
        return child(2);
    }
}