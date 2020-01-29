package com.camile.ast;

import java.util.List;

public class WhileStmnt extends AbstractList {

    public WhileStmnt(List<AbstractTree> c) {
        super(c);
    }

    public AbstractTree condition() {
        return child(0);
    }

    public AbstractTree body() {
        return child(1);
    }

    @Override
    public String toString() {
        return "(while " + condition() + " " + body() + ")";
    }
}
