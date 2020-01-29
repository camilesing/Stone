package com.camile.ast;

import java.util.List;

public class PrimaryExpr extends AbstractList {

    public PrimaryExpr(List<AbstractTree> c) {
        super(c);
    }

    public static AbstractTree create(List<AbstractTree> c) {
        return c.size() == 1 ? c.get(0) : new PrimaryExpr(c);
    }
}
