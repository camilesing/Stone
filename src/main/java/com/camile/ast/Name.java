package com.camile.ast;

import com.camile.Environment;
import com.camile.Token;
import com.camile.exception.StoneException;

/**
 * 表示变量的叶子
 */
public class Name extends AbstractLeaf {

    public Name(Token t) {
        super(t);
    }

    public String name() {
        return token().getText();
    }

    @Override
    public Object evaluate(Environment env) {
        Object value = env.get(name());
        if (value == null) {
            throw new StoneException("undefined name: " + name());
        } else {
            return value;
        }
    }
}