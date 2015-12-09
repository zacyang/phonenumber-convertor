package com.aconex.convertor.dictionary;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class TextSourceDictionaryTest {
    private TextSourceDictionary classUnderTest;
    private static final Pattern UNDESIRABLES = Pattern.compile("[\\]\\[(){},.;!?<>%\\s]");

    @Before
    public void setUp() throws Exception {
        classUnderTest = new TextSourceDictionary(null);
    }

    @Test
    public void shouldGetAllWordsOnDefaultTextDictionary() throws Exception {
        //given
        //when
        List<String> words = classUnderTest.getWords();

        //then
        assertThat(words, is(notNullValue()));
        assertThat(words.size(), is(109583));
    }

    @Test
    public void sanityShouldContainsNoSpaceNorPunctuationInDefaultDic() throws Exception {
        //given
        //when
        List<String> words = classUnderTest.getWords();

        //then
        assertThat(words, is(notNullValue()));
        words.forEach(word -> assertThat("word :" + word, UNDESIRABLES.matcher(word).find(), is(false)));
    }

    @Test
    public void sanityShouldRemovePunctuationInSpecifidDic() throws Exception {
        //given
        classUnderTest = new TextSourceDictionary(getPathForTestDictionary("dic-with-space-punctuation.txt"));
        //when
        List<String> words = classUnderTest.getWords();

        //then
        assertThat(words, is(notNullValue()));
        words.forEach(word -> assertThat("word :" + word, UNDESIRABLES.matcher(word).find(), is(false)));
    }


    @Test
    public void shouldGetAllWordsOnDefaultTextDictionaryWhenSpecificPathIsEmpty() throws Exception {
        //given
        classUnderTest = new TextSourceDictionary("");
        //when
        List<String> words = classUnderTest.getWords();

        //then
        assertThat(words, is(notNullValue()));
        assertThat(words.size(), is(109583));
    }

    @Test
    public void shouldGetAllWordsOnDefaultTextDictionaryWhenSpecificPathIsNull() throws Exception {
        //given
        classUnderTest = new TextSourceDictionary(null);
        //when
        List<String> words = classUnderTest.getWords();

        //then
        assertThat(words, is(notNullValue()));
        assertThat(words.size(), is(109583));
    }

    @Test
    public void shouldBeAbleToReadDictionaryFromSpecificPath() throws Exception {
        //given
        classUnderTest = new TextSourceDictionary(getPathForTestDictionary("dic-test.txt"));
        //when
        List<String> words = classUnderTest.getWords();

        //then
        assertThat(words, is(notNullValue()));
        assertThat(words.size(), is(8));
        assertThat(words.contains("wordWill-0notappearOnRealdic"), is(true));
    }

    private String getPathForTestDictionary(String testDic) {
        final String dir = System.getProperty("user.dir");
        return dir + "/src/test/resources/"+testDic;
    }

    @Test
    public void shouldReturnNullWhenEncounterProblemOfReadingDict() throws Exception {
        //given
        classUnderTest = new TextSourceDictionary("dic-test-not-exists.txt");
        //when
        List<String> words = classUnderTest.getWords();

        //then
        assertThat(words, is(nullValue()));
    }
}