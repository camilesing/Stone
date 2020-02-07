package com.camile;


import com.camile.ast.AbstractTree;
import com.camile.ast.NullStmnt;
import com.camile.exception.ParseException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.Reader;

public class InterpreterTest {

    Reader mockReader = new Reader() {
        private String buffer = null;
        private int pos = 0;

        @Override
        public int read(@NotNull char[] cbuf, int off, int len) {
            if (buffer == null) {
                String in = "sum = 0\n" +
                        "i = 1\n" +
                        "while i < 10 {\n" +
                        "    sum = sum + i\n" +
                        "    i = i + 1\n" +
                        "}\n" +
                        "sum";
                System.out.println(in);
                buffer = in + "\n";
                pos = 0;

            }

            int size = 0;
            int length = buffer.length();
            while (pos < length && size < len)
                cbuf[off + size++] = buffer.charAt(pos++);

            if (pos == length)
                buffer = null;

            return size;
        }

        @Override
        public void close() {

        }
    };


    @Test
    public void testLexer() throws ParseException {
        BasicParser bp = new BasicParser();
        BasicEnv env = new BasicEnv();
        Lexer lexer = new Lexer(mockReader);
        while (lexer.peek(0) != Token.EOF) {
            AbstractTree t = bp.parse(lexer);
            if (!(t instanceof NullStmnt)) {
                Object r = t.evaluate(env);
                System.out.println("=> " + r);
            }
        }
    }
}
