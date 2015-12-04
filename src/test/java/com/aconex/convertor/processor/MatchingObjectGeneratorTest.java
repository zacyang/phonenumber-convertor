package com.aconex.convertor.processor;

import com.aconex.convertor.config.ApplicationConfig;
import com.aconex.convertor.generator.MatchingObjectGenerator;
import com.aconex.convertor.model.MatchingObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class MatchingObjectGeneratorTest {
    private MatchingObjectGenerator classUnderTest;
    @Mock
    private ApplicationConfig config;
    private Map<String, List<String>> configMap;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        prepareConfigData();
        classUnderTest = new MatchingObjectGenerator(config);
    }

    @Test
    public void shouldGetMatchingObjectWithAllDigitalToWordsTable() throws Exception {
        String inputPhoneNumber = "25";
        List<String> expectedReplacementWordsForTwo = new ArrayList<>();
        expectedReplacementWordsForTwo.add("A");
        expectedReplacementWordsForTwo.add("B");
        expectedReplacementWordsForTwo.add("C");
        given(config.getDigitalWordingMap()).willReturn(configMap);

        MatchingObject matchingObject = classUnderTest.getMatchingObject(inputPhoneNumber);

        assertThat(matchingObject, notNullValue());
        List<String> replacementWordAt = matchingObject.getReplacementWordAt(0);
        assertThat(replacementWordAt, notNullValue());


        replacementWordAt.stream().forEach(
                (s) -> assertThat(expectedReplacementWordsForTwo.contains(s), is(true)));

    }

    private void prepareConfigData() {
        configMap = new HashMap<>();
        generateReplacementPair("2", "A", "B", "C");
        generateReplacementPair("5", "J", "K", "L");
    }


    private void generateReplacementPair(String key, String a, String b, String c) {
        List<String> replacementForTwo = new ArrayList<>();
        replacementForTwo.add(a);
        replacementForTwo.add(b);
        replacementForTwo.add(c);
        configMap.put(key, replacementForTwo);
    }
}