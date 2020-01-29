package com.camile.ast;

import java.util.List;

public class IfStmnt extends AbstractList {
    public IfStmnt(List<AbstractTree> c) {
        super(c);
    }

    public AbstractTree condition() {
        return child(0);
    }

    public AbstractTree thenBlock() {
        return child(1);
    }

    public AbstractTree elseBlock() {
        return numChildren() > 2 ? child(2) : null;
    }

    @Override
    public String toString() {
        return "(if " + condition() + " " + thenBlock()
                + " else " + elseBlock() + ")";
    }
}
