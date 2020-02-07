package com.camile.ast;


import com.camile.Environment;
import com.camile.exception.StoneException;

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

    @Override
    public Object evaluate(Environment env) {
        Object v = operand().evaluate(env);
        if (v instanceof Integer) {
            return new Integer(-((Integer) v).intValue());
        } else {
            throw new StoneException("bad type for -");
        }
    }
}
