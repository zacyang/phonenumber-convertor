package com.aconex.convertor.query;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by twer on 12/7/15.
 */
public class CriteriaInterpreterTest {
    private CriteriaInterpreter classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new CriteriaInterpreter();
    }

    @Test
    public void shouldGenerateAllPossibleCombinationForGivenWords() throws Exception {
        //given
        //2255 should to transfer to list contains "CALL"
        List<String> word1 = asList("A", "B", "C");
        List<String> word2 = asList("A", "B", "C");
        List<String> word3 = asList("J", "K", "L");
        List<String> word4 = asList("J", "K", "L");
        List<List<String>> lists = asList(word1, word2, word3, word4);
        Criteria c = new Criteria()
                .mayStartWith(word1)
                .nextWordMayBe(word2)
                .nextWordMayBe(word3)
                .nextWordMayBe(word4);

        //when

        List<String> allCombos = classUnderTest.getAllCombos(c);

        //then
        assertThat(allCombos.contains("CALL"), is(true));
    }
}