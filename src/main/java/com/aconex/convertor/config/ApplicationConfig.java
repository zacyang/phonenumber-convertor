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
    private static final String APPLICATION_PROPERTIES_NAME = "application.properties";
    private final Map<String, String> encodingConfig;
    private final Properties encodingProperties;
    private final Properties applicationProperties;
    private String separator = "-";

    public ApplicationConfig() {
        encodingProperties = new Properties();
        applicationProperties = new Properties();
        this.encodingConfig = new HashMap<>();
        readPropertiesFile(ENCODING_PROPERTIES_NAME, this.encodingProperties);
        readPropertiesFile(APPLICATION_PROPERTIES_NAME, this.applicationProperties);
        initProperties();
    }

    private void readPropertiesFile(String encodingPropertiesName, Properties encodingProperties) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream in = classLoader.getResourceAsStream(encodingPropertiesName);
        try {
            encodingProperties.load(in);
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }

    private void initProperties() {
        encodingProperties.forEach((k, v) ->
                        encodingConfig.put(k.toString(), v.toString())
        );
        Object separatorToken = applicationProperties.get("separatorToken");
        this.separator = separatorToken == null? separator: separatorToken.toString();
    }

    public Map<String, String> getEncodingConfig() {
        return Collections.unmodifiableMap(encodingConfig);
    }

    public String getSeparator() {
        return this.separator;
    }

}
