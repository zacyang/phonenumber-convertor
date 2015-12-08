package com.aconex.convertor.encode;

import java.util.Map;

/**
 * Created by twer on 12/8/15.
 */
public class DictionaryEncoder {
    private final Map<String, Integer> encodeConfig;

    public DictionaryEncoder(final Map<String, Integer> encodeConfig) {
        this.encodeConfig = encodeConfig;
    }

    public String encodeForWord(final String line) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            String s = String.valueOf(c);
            Integer obj = encodeConfig.get(s.toUpperCase());
            if (obj != null) {
                result.append(obj);
            }
        }
        return result.toString();
    }

}
