package com.aconex.convertor.encode;

import com.aconex.convertor.config.ApplicationConfig;

import java.util.Map;

public class DictionaryEncoder {
    private final Map<String, Integer> encodeConfig;

    public DictionaryEncoder(final ApplicationConfig config) {
        this.encodeConfig = config.getEncodingConfig();
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
