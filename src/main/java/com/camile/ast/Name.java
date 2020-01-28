package com.camile.ast;

import com.camile.Token;

/**
 * 表示变量的叶子
 * */
public class Name extends AbstractLeaf {

    public Name(Token t) {
        super(t);
    }

    public String name() { return token().getText(); }
}