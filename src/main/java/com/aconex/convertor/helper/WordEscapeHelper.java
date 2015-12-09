package com.aconex.convertor.helper;

import java.util.regex.Pattern;

public class WordEscapeHelper {
    private static final Pattern UNDESIRABLES = Pattern.compile("[\\]\\[(){},.;!?<>%\\s]");
    private static final String EMPTY_STRING = "";

    public static String removeSpaceAndPunctuation(String number) {
        String numberWithSpaceAndPunctuationRemoved = number;
        if (number != null && !EMPTY_STRING.equals(number)) {
            numberWithSpaceAndPunctuationRemoved = UNDESIRABLES.matcher(number).replaceAll(EMPTY_STRING);
        }
        return numberWithSpaceAndPunctuationRemoved;
    }
}
