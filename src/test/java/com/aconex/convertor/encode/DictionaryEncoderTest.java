package com.aconex.convertor.encode;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DictionaryEncoderTest {
    private DictionaryEncoder classUnderTest;
    private Map<String, Integer> encodingConfig;

    @Before
    public void setUp() throws Exception {
        encodingConfig = new HashMap<>();
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
        classUnderTest = new DictionaryEncoder(encodingConfig);
    }

    @Test
    public void shouldReturnMatchedEncodedNumber() throws Exception {
        //given
        //when
        String result = classUnderTest.encodeForWord("AB");
        //then
        assertThat(result, is("22"));
    }

    @Test
    public void shouldReturnMatchedNumberForWordCallMe() throws Exception {
        //given
        //when
        String result = classUnderTest.encodeForWord("Callme");
        //then
        assertThat(result, is("225563"));
    }

    @Test
    public void shouldReturnMatchedNumberForWordCallMeIgnoreCase() throws Exception {
        //given
        //when
        String result = classUnderTest.encodeForWord("CaLlMe");
        //then
        assertThat(result, is("225563"));
    }

    @Test
    public void shouldReturnMatchedNumberForWordCallMeIgnoreCaseAndIgnorePunctuation() throws Exception {
        //given
        //when
        String result = classUnderTest.encodeForWord("CaL.lMe");
        //then
        assertThat(result, is("225563"));
    }


    @Test
    public void shouldReturnMatchedNumberForWordCallMeIgnoreCaseAndIgnorePunctuationAndIgnoreWhitSpace() throws Exception {
        //given
        //when
        String result = classUnderTest.encodeForWord("C a L.l Me");
        //then
        assertThat(result, is("225563"));
    }
}