package com.aconex.convertor.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApplicationConfig {
    public static final String DEFAULT_DICT = "dic.txt";
    private static final String ENCODING_PROPERTIES_NAME = "encoding.properties";
    private final Map<String, String> encodingConfig;
    private final Properties encodingProperties;
    private String separator = "-";

    public ApplicationConfig() {
        encodingProperties = new Properties();
        this.encodingConfig = new HashMap<>();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(ENCODING_PROPERTIES_NAME);
        try {
            encodingProperties.load(in);
        } catch (IOException e) {
            throw new IllegalStateException();
        }
        initProperties();
    }

    private void initProperties() {
        encodingProperties.forEach((k, v) ->
                        encodingConfig.put(k.toString(), v.toString())
        );
    }

    public Map<String, String> getEncodingConfig() {
        return Collections.unmodifiableMap(encodingConfig);
    }

    public String getSeparator() {
        return separator;
    }

}
