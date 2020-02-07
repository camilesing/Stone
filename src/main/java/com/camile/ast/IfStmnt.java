package com.camile.ast;

import com.camile.Environment;

import java.util.List;

import static javassist.compiler.TokenId.FALSE;

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

    @Override
    public Object evaluate(Environment env) {
        Object c = (condition()).evaluate(env);
        if (c instanceof Integer && (Integer) c != FALSE) {
            return (thenBlock()).evaluate(env);
        } else {
            AbstractTree b = elseBlock();
            if (b == null) {
                return 0;
            } else {
                return (b).evaluate(env);
            }
        }
    }
}
