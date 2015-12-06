package com.aconex.convertor.generator;

import com.aconex.convertor.model.MatchingMetaInfo;
import com.aconex.convertor.query.MatchingChunk;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WordLengthCombinationGeneratorTest {
    private WordLengthCombinationGenerator classUnderTest;
    @Before
    public void setUp() throws Exception {
        classUnderTest = new WordLengthCombinationGenerator();
    }

    @Test
    public void shouldReturnOneCombinationWhenQueryLengthIsOne() throws Exception {
        //given
        String originalNumber = "2";
        List<List<String>> sequenceForWordReplacement = asList(
                asList("A","B","C")
        );
        MatchingMetaInfo matchingObject =new MatchingMetaInfo(originalNumber, sequenceForWordReplacement);
        //when
        List<MatchingChunk> lists = classUnderTest.generateCriterias(matchingObject);
        //then
        assertThat(lists.size(), is(1));
    }
}