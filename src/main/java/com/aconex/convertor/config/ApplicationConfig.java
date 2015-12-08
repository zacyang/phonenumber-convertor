package com.aconex.convertor.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApplicationConfig {
    private static final String ENCODING_PROPERTIES = "encoding.properties";
    private final Map<String, String> encodingConfig;
    private final Properties configProp;
    private String separator = "-";

    public ApplicationConfig() {
        configProp = new Properties();
        this.encodingConfig = new HashMap<>();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(ENCODING_PROPERTIES);
        try {
            configProp.load(in);
        } catch (IOException e) {
            throw new IllegalStateException();
        }
        initProperties();
    }

    private void initProperties() {
        configProp.forEach((k, v) ->
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
