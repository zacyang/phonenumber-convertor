package com.aconex.convertor.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ApplicationConfig {
    private final Map<String, Integer> encodingConfig;
    private String separator = "-";

    public Map<String, Integer> getEncodingConfig() {
        return Collections.unmodifiableMap(encodingConfig);
    }

    public ApplicationConfig() {
        this.encodingConfig = new HashMap<>();
        encodingConfig.put("A", 2);
        encodingConfig.put("B", 2);
        encodingConfig.put("C", 2);
        encodingConfig.put("D", 3);
        encodingConfig.put("E", 3);
        encodingConfig.put("F", 3);
        encodingConfig.put("G", 4);
        encodingConfig.put("H", 4);
        encodingConfig.put("I", 4);
        encodingConfig.put("J", 5);
        encodingConfig.put("K", 5);
        encodingConfig.put("L", 5);
        encodingConfig.put("M", 6);
        encodingConfig.put("N", 6);
        encodingConfig.put("O", 6);
        encodingConfig.put("P", 7);
        encodingConfig.put("Q", 7);
        encodingConfig.put("R", 7);
        encodingConfig.put("S", 7);
        encodingConfig.put("T", 8);
        encodingConfig.put("U", 8);
        encodingConfig.put("V", 8);
        encodingConfig.put("W", 9);
        encodingConfig.put("X", 9);
        encodingConfig.put("Y", 9);
        encodingConfig.put("Z", 9);
    }

    public String getSeparator() {
        return separator;
    }

}
