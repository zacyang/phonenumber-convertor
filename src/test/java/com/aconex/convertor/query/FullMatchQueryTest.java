package com.aconex.convertor.query;

import com.aconex.convertor.model.MatchingMetaInfo;
import com.aconex.convertor.model.MatchingResult;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FullMatchQueryTest {
    private FullMatchQuery classUnderTest;
    private Map<String, List<String>> dictionary;

    @Before
    public void setUp() throws Exception {
        dictionary = new HashMap<>();
        classUnderTest = new FullMatchQuery(dictionary);
    }

    @Test
    public void shouldReturnExpectedMatch() throws Exception {
        //given
        dictionary.put("22563", asList("CALL"));
        //when
        MatchingMetaInfo criteria = new MatchingMetaInfo("22563");
        List<MatchingResult> matched = classUnderTest.getMatched(criteria);
        //then
        assertThat(matched.size(), is(1));
        List<List<String>> wordSequence = matched.get(0).getWordSequence();
        assertThat(wordSequence.get(0).get(0), is("CALL"));
    }

    @Test
    public void shouldReturnNoMatch() throws Exception {
        //given
        dictionary.put("22563", asList("CALL"));
        //when
        MatchingMetaInfo criteria = new MatchingMetaInfo("11111");
        List<MatchingResult> matched = classUnderTest.getMatched(criteria);
        //then
        assertThat(matched.size(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRaiseExceptionWhenTryQueryForNullOrEmpty() throws Exception {
        //given
        dictionary.put("22563", asList("CALL"));
        //when
        MatchingMetaInfo criteria = new MatchingMetaInfo(null);
        List<MatchingResult> matched = classUnderTest.getMatched(criteria);
        //then
        assertThat(matched.size(), is(0));
    }
}