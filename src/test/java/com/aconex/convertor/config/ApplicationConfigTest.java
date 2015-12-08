package com.aconex.convertor.config;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class ApplicationConfigTest {
    private ApplicationConfig classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new ApplicationConfig();
    }

    @Test
    public void wontBlowUpWhenTryReadingProperties() throws Exception {
        //given
        //when
        Map<String, String> encodingConfig = classUnderTest.getEncodingConfig();

        //then
        assertThat(encodingConfig, is(notNullValue()));
        assertThat(encodingConfig.get("A"), is("2"));
    }

    @Test
    public void sanityForAllMatchesInDefaultConfig() throws Exception {
        //given
        Map<String, String> expectedEncode = generateDefaultEncodeMap();
        //when
        Map<String, String> result = classUnderTest.getEncodingConfig();

        //then
        assertThat(expectedEncode.size(), is(result.size()));
        result.forEach((k, v)
                ->
                assertThat(expectedEncode.get(k), is(v))
        );
    }

    private Map<String, String> generateDefaultEncodeMap() {
        Map<String, String> encodingConfig = new HashMap<>();
        encodingConfig.put("A", "2");
        encodingConfig.put("B", "2");
        encodingConfig.put("C", "2");
        encodingConfig.put("D", "3");
        encodingConfig.put("E", "3");
        encodingConfig.put("F", "3");
        encodingConfig.put("G", "4");
        encodingConfig.put("H", "4");
        encodingConfig.put("I", "4");
        encodingConfig.put("J", "5");
        encodingConfig.put("K", "5");
        encodingConfig.put("L", "5");
        encodingConfig.put("M", "6");
        encodingConfig.put("N", "6");
        encodingConfig.put("O", "6");
        encodingConfig.put("P", "7");
        encodingConfig.put("Q", "7");
        encodingConfig.put("R", "7");
        encodingConfig.put("S", "7");
        encodingConfig.put("T", "8");
        encodingConfig.put("U", "8");
        encodingConfig.put("V", "8");
        encodingConfig.put("W", "9");
        encodingConfig.put("X", "9");
        encodingConfig.put("Y", "9");
        encodingConfig.put("Z", "9");
        return encodingConfig;
    }
}