package com.camile.ast;

import com.camile.Environment;
import com.camile.exception.StoneException;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * 操作符节点
 */
public class BinaryExpr extends AbstractList {
    public BinaryExpr(List<AbstractTree> c) {
        super(c);
    }

    public AbstractTree left() {
        return child(0);
    }

    public String operator() {
        return ((AbstractLeaf) child(1)).token().getText();
    }

    public AbstractTree right() {
        return child(2);
    }

    @Override
    public Object evaluate(Environment env) {
        String op = operator();
        if ("=".equals(op)) {
            Object right = right().evaluate(env);
            return computeAssign(env, right);
        } else {
            Object left = left().evaluate(env);
            Object right = right().evaluate(env);
            return computeOp(left, op, right);
        }
    }

    /**
     * 遇到 = 运算符时将做特殊处理。
     *
     * 赋值表达式的右侧的值能够由 eval 方法计算得到，左侧则不行。
     * 左侧的值需要由一种名为左值（L-value）的特殊表达式计算。
     * 左值是右侧的值的赋值对象，无法通过 eval 方法算得。例如，赋值表达式 a=7 中，对左侧表达式调用 eval 方法的结果是变量 a 的当前值。
     * 该结果不同于左值，并不是表达式新赋给 a 的值。
     * */
    protected Object computeAssign(Environment env, Object rvalue) {
        AbstractTree l = left();
        if (l instanceof Name) {
            env.put(((Name) l).name(), rvalue);
            return rvalue;
        } else {
            throw new StoneException("bad assignment");
        }
    }

    protected Object computeOp(Object left, String op, Object right) {
        if (left instanceof Integer && right instanceof Integer) {
            return computeNumber((Integer) left, op, (Integer) right);
        } else if (op.equals("+")) {
            return String.valueOf(left) + String.valueOf(right);
        } else if (op.equals("==")) {
            if (left == null) {
                return right == null ? TRUE : FALSE;
            } else {
                return left.equals(right) ? TRUE : FALSE;
            }
        } else {
            throw new StoneException("bad type");
        }
    }

    protected Object computeNumber(Integer left, String op, Integer right) {
        int a = left.intValue();
        int b = right.intValue();
        if (op.equals("+")) {
            return a + b;
        } else if (op.equals("-")) {
            return a - b;
        } else if (op.equals("*")) {
            return a * b;
        } else if (op.equals("/")) {
            return a / b;
        } else if (op.equals("%")) {
            return a % b;
        } else if (op.equals("==")) {
            return a == b ? TRUE : FALSE;
        } else if (op.equals(">")) {
            return a > b ? TRUE : FALSE;
        } else if (op.equals("<")) {
            return a < b ? TRUE : FALSE;
        } else {
            throw new StoneException("bad operator");
        }
    }
}