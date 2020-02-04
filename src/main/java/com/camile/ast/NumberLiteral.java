package com.camile.ast;

import com.camile.Token;

/**
 * 字面量叶子
 */
public class NumberLiteral extends AbstractLeaf {
    public NumberLiteral(Token t) {
        super(t);
    }

    public int value() {
        return token().getNumber();
    }
}
