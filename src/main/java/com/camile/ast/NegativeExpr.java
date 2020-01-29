package com.camile.ast;


import java.util.List;

public class NegativeExpr extends AbstractList {
    public NegativeExpr(List<AbstractTree> c) {
        super(c);
    }

    public AbstractTree operand() {
        return child(0);
    }

    @Override
    public String toString() {
        return "-" + operand();
    }
}
