package com.aconex.convertor.query;

import com.aconex.convertor.config.ApplicationConfig;
import com.aconex.convertor.model.MatchingResult;
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
        MatchingResult c = getDefaultCriteria();

        //when
        List<String> allCombos = classUnderTest.getAllCombos(c);

        //then
        assertThat(allCombos.contains("CALL"), is(true));
        assertThat(allCombos.size(), is(81));
    }

    @Test
    public void shouldBeOnlyOneCombinationWhenEachWordListIsSingle() throws Exception {
        //given
        MatchingResult c = getSingleWordCriteria();

        //when
        List<String> allCombos = classUnderTest.getAllCombos(c);

        //then
        assertThat(allCombos.size(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRaiseExceptionWhenInputCriteriaIsNull() throws Exception {
        //given
        MatchingResult c = null;

        //when
        List<String> allCombos = classUnderTest.getAllCombos(c);

        //then
    }

    @Test
    public void getRegexFilterOutPunctuationDigitalInResult() throws Exception {
        //given
        MatchingResult c = getMatchingResultContainsPunctuationDigitals();

        //when
        List<String> allCombos = classUnderTest.getAllCombos(c);

        //then
        assertThat(allCombos.size(), is(63));
        allCombos.forEach(word -> {
            assertThat(word.contains("3-1"), is(false));
            assertThat(word.contains("1-1"), is(false));
        });
    }

    private MatchingResult getSingleWordCriteria() {
        List<String> word1 = asList("A");
        List<String> word2 = asList("B");
        List<String> word3 = asList("C");
        MatchingResult matchingResult = new MatchingResult();
        matchingResult
                .addNextWordPossibleMatching(word1);
        matchingResult.addNextWordPossibleMatching(word2);
        matchingResult.addNextWordPossibleMatching(word3);
        return matchingResult;
    }


    private MatchingResult getDefaultCriteria() {
        List<String> word1 = asList("A", "B", "C");
        List<String> word2 = asList("A", "B", "C");
        List<String> word3 = asList("J", "K", "L");
        List<String> word4 = asList("J", "K", "L");
        MatchingResult matchingResult = new MatchingResult();

        matchingResult
                .addNextWordPossibleMatching(word1);
        matchingResult.addNextWordPossibleMatching(word2);
        matchingResult.addNextWordPossibleMatching(word3);
        matchingResult.addNextWordPossibleMatching(word4);
        return matchingResult;
    }


    private MatchingResult getMatchingResultContainsPunctuationDigitals() {
        List<String> word1 = asList("1", "B", "C");
        List<String> word2 = asList("A", "B", "C");
        List<String> word3 = asList("1", "3", "L");
        List<String> word4 = asList("1", "K", "L");
        MatchingResult matchingResult = new MatchingResult();

        matchingResult
                .addNextWordPossibleMatching(word1);
        matchingResult.addNextWordPossibleMatching(word2);
        matchingResult.addNextWordPossibleMatching(word3);
        matchingResult.addNextWordPossibleMatching(word4);
        return matchingResult;
    }
}