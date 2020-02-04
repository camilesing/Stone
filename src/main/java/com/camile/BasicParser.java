package com.camile;

import com.camile.Parser.Operators;
import com.camile.ast.AbstractTree;
import com.camile.ast.BinaryExpr;
import com.camile.ast.BlockStatement;
import com.camile.ast.IfStmnt;
import com.camile.ast.Name;
import com.camile.ast.NegativeExpr;
import com.camile.ast.NullStmnt;
import com.camile.ast.NumberLiteral;
import com.camile.ast.PrimaryExpr;
import com.camile.ast.StringLiteral;
import com.camile.ast.WhileStmnt;
import com.camile.exception.ParseException;

import java.util.HashSet;

import static com.camile.Parser.rule;

/**
 * @author camile
 * <p>
 * 语法分析器
 * EBNF（Extended Backus-Naur Form，扩展巴科斯范式）
 * primary    : "(" expr ")" | NUMBER | IDENTIFIER | STRING         (非终结符 primary（基本构成元素）用于表示括号括起的表达式、整型字面量、标识符（即变量名）或字符串字面量。这些是最基本的表达式构成元素。)
 * factor     : "-" primary | primary   (非终结符 factor（因子）或表示一个 primary，或表示 primary 之前再添加一个 - 号的组合)
 * expr       : factor { OP factor }                                (expr（表达式）用于表示两个 factor 之间夹有一个双目运算符的组合)
 * block      : "{" [ statement ] {(";" | EOL) [ statement ]} "}"   （block（代码块）指的是由 {} 括起来的 statement（语句）序列）
 * simple     : expr                                (简单表达式语句是仅由表达式（expr）构成的语句)
 * statement  : "if" expr block [ "else" block ]    (statement 之间需要用分号或换行符（EOL）分隔。由于 Stone 语言支持空语句，因此规则中的 statement 两侧写有中括号 [])
 * | "while" expr block
 * | simple
 * program    : [ statement ] (";" | EOL)   (program 既可以是处于代码块之外的一条语句，也可以是一行完整的程序)
 */
public class BasicParser {
    /**
     * 变量名符号
     */
    HashSet<String> reserved = new HashSet<String>();
    Parser.Operators operators = new Parser.Operators();
    Parser expr0 = rule();
    Parser primary = rule(PrimaryExpr.class)
            .or(rule().sep("(").ast(expr0).sep(")"),
                    rule().number(NumberLiteral.class),
                    rule().identifier(Name.class, reserved),
                    rule().string(StringLiteral.class));
    Parser factor = rule().or(rule(NegativeExpr.class).sep("-").ast(primary), primary);
    Parser expr = expr0.expression(BinaryExpr.class, factor, operators);

    Parser statement0 = rule();
    Parser block = rule(BlockStatement.class)
            .sep("{").option(statement0)
            .repeat(rule().sep(";", Token.EOL).option(statement0))
            .sep("}");
    Parser simple = rule(PrimaryExpr.class).ast(expr);
    Parser statement = statement0.or(
            rule(IfStmnt.class).sep("if").ast(expr).ast(block)
                    .option(rule().sep("else").ast(block)),
            rule(WhileStmnt.class).sep("while").ast(expr).ast(block), simple);

    Parser program = rule().or(statement, rule(NullStmnt.class))
            .sep(";", Token.EOL);

    public BasicParser() {
        reserved.add(";");
        reserved.add("}");
        reserved.add(Token.EOL);

        operators.add("=", 1, Operators.RIGHT);
        operators.add("==", 2, Operators.LEFT);
        operators.add(">", 2, Operators.LEFT);
        operators.add("<", 2, Operators.LEFT);
        operators.add("+", 3, Operators.LEFT);
        operators.add("-", 3, Operators.LEFT);
        operators.add("*", 4, Operators.LEFT);
        operators.add("/", 4, Operators.LEFT);
        operators.add("%", 4, Parser.Operators.LEFT);
    }

    public AbstractTree parse(Lexer lexer) throws ParseException {
        return program.parse(lexer);
    }
}
