package pl.put.swolarz.application.common.util;

import java.security.SecureRandom;

public class TokenGenerator {

    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_LETTERS = LOWERCASE_LETTERS.toUpperCase();
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = LOWERCASE_LETTERS + UPPERCASE_LETTERS + DIGITS;

    private final SecureRandom random = new SecureRandom();
    private int tokenLength;


    public TokenGenerator(int tokenLength) {
        this.tokenLength = tokenLength;
    }

    public String generate() {

        char[] buffer = new char[tokenLength];

        for (int i = 0; i < tokenLength; ++i)
            buffer[i] = SYMBOLS.charAt(random.nextInt(SYMBOLS.length()));

        return new String(buffer);
    }
}
