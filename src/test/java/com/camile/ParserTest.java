package com.camile;


import com.camile.ast.AbstractTree;
import com.camile.exception.ParseException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.Reader;

public class ParserTest {

    Reader mockReader = new Reader() {
        private String buffer = null;
        private int pos = 0;

        @Override
        public int read(@NotNull char[] cbuf, int off, int len) {
            if (buffer == null) {
                String in = "even = 0\n" +
                        "odd = 0\n" +
                        "i = 1\n" +
                        "while i < 10 {\n" +
                        "   if i % 2 == 0 {       // even number?\n" +
                        "      even = even + i\n" +
                        "   } else {\n" +
                        "      odd = odd + i\n" +
                        "   }\n" +
                        "   i = i + 1\n" +
                        "}\n" +
                        "even + odd";
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
        Lexer lexer = new Lexer(mockReader);
        BasicParser bp = new BasicParser();
        while (lexer.peek(0) != Token.EOF) {
            AbstractTree ast = bp.parse(lexer);
            System.out.println("=> " + ast.toString());
        }
    }
}
