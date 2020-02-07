package com.camile.ast;

import com.camile.Environment;

import java.util.List;

import static javassist.compiler.TokenId.FALSE;

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

    @Override
    public Object evaluate(Environment env) {
        Object result = 0;
        for (; ; ) {
            Object c = condition().evaluate(env);
            if (c instanceof Integer && (Integer) c == FALSE) {
                return result;
            } else {
                result = (body()).evaluate(env);
            }
        }
    }
}
