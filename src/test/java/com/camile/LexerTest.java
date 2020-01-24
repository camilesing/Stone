package com.camile;


import com.camile.exception.ParseException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.Reader;

public class LexerTest {

    Reader mockReader = new Reader() {
        private String buffer = null;
        private int pos = 0;

        @Override
        public int read(@NotNull char[] cbuf, int off, int len) {
            if (buffer == null) {
                String in = "while i<={" +
                        "sum = sum + i" +
                        "i=i+1" +
                        "}" +
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
        Lexer lexer = new Lexer(mockReader);
        for (Token t; (t = lexer.read()) != Token.EOF; ) {
            System.out.println("=> " + t.getText());
        }
    }
}
