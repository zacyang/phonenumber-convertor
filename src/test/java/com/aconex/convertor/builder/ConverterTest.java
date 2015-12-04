package com.aconex.convertor.builder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by twer on 12/3/15.
 */
public class ConverterTest {
    private Converter classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new Converter();
    }

    @After
    public void tearDown() throws Exception {
        classUnderTest = null;
    }

    @Test
    public void returnValueShouldContainsExpectedWording() throws Exception {
        final String expectedResult = "CALL-ME";
        List<String> matchingPhraseFor = classUnderTest.getMatchingPhraseFor("2255.63");

        assertTrue(matchingPhraseFor.contains(expectedResult));
    }

}