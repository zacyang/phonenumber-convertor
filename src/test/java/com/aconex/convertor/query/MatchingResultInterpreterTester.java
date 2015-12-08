package com.aconex.convertor.query;

import com.aconex.convertor.config.ApplicationConfig;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;


public class MatchingResultInterpreterTester {
    private MatchingResultInterpreter classUnderTest;
    @Mock
    private ApplicationConfig applicationConfig;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        given(applicationConfig.getSeparator()).willReturn("");
        classUnderTest = new MatchingResultInterpreter(applicationConfig);
    }

    @Test
    public void shouldGenerateAllPossibleCombinationForGivenWords() throws Exception {
        //given
        //2255 should to transfer to list contains "CALL"
        Criteria c = getDefaultCriteria();

        //when
        List<String> allCombos = classUnderTest.getAllCombos(c);

        //then
        assertThat(allCombos.contains("CALL"), is(true));
        assertThat(allCombos.size(), is(81));
    }

    @Test
    public void shouldBeOnlyOneCombinationWhenEachWordListIsSingle() throws Exception {
        //given
        Criteria c = getSingleWordCriteria();

        //when
        List<String> allCombos = classUnderTest.getAllCombos(c);

        //then
        assertThat(allCombos.size(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRaiseExceptionWhenInputCriteriaIsNull() throws Exception {
        //given
        Criteria c = null;

        //when
        List<String> allCombos = classUnderTest.getAllCombos(c);

        //then
    }


    private Criteria getSingleWordCriteria() {
        List<String> word1 = asList("A");
        List<String> word2 = asList("B");
        List<String> word3 = asList("C");
        List<List<String>> lists = asList(word1, word2, word3);
        return new Criteria()
                .nextWordMayBe(word1)
                .nextWordMayBe(word2)
                .nextWordMayBe(word3);
    }
    private Criteria getDefaultCriteria() {
        List<String> word1 = asList("A", "B", "C");
        List<String> word2 = asList("A", "B", "C");
        List<String> word3 = asList("J", "K", "L");
        List<String> word4 = asList("J", "K", "L");
        List<List<String>> lists = asList(word1, word2, word3, word4);
        return new Criteria()
                .nextWordMayBe(word1)
                .nextWordMayBe(word2)
                .nextWordMayBe(word3)
                .nextWordMayBe(word4);
    }
}