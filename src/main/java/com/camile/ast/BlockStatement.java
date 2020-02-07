package com.camile.ast;

import com.camile.Environment;

import java.util.List;

public class BlockStatement extends AbstractList {

    public BlockStatement(List<AbstractTree> list) {
        super(list);
    }

    @Override
    public Object evaluate(Environment env) {
        Object result = 0;
        for (AbstractTree t : this) {
            if (!(t instanceof NullStmnt)) {
                result = t.evaluate(env);
            }
        }
        return result;
    }
}
