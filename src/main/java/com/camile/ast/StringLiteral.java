package com.camile.ast;

import com.camile.Token;

public class StringLiteral extends AbstractLeaf {

    public StringLiteral(Token t) {
        super(t);
    }

    public String value() {
        return token().getText();
    }
}
