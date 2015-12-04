package com.aconex.convertor.builder;

import com.aconex.convertor.generator.MatchingObjectGenerator;
import com.aconex.convertor.generator.WordLengthCombinationGenerator;
import com.aconex.convertor.query.QueryExecutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyList;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by twer on 12/3/15.
 */
public class ConverterTest {
    private Converter classUnderTest;
    @Mock
    private QueryExecutor executor;
    @Mock
    private MatchingObjectGenerator reader;
    @Mock
    private WordLengthCombinationGenerator generator;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        classUnderTest = new Converter(executor, reader, generator);
    }

    @After
    public void tearDown() throws Exception {
        classUnderTest = null;
    }

    @Test
    public void returnValueShouldContainsExpectedWording() throws Exception {

        final String expectedResult = "CALL-ME";
        given(executor.getPossibleMatches(anyList())).willReturn(asList(expectedResult));
        List<String> matchingPhraseFor = classUnderTest.getMatchingPhraseFor("2255.63");

        assertTrue(matchingPhraseFor.contains(expectedResult));
    }

}