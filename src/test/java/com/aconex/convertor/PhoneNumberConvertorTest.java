package com.aconex.convertor;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneNumberConvertorTest {
    private PhoneNumberConvertor classUnderTest;
    @Before
    public void setUp() throws Exception {
        classUnderTest = new PhoneNumberConvertor();
    }

    @Test
    public void shouldGetExpectedMessageByDefault() throws Exception {
        //given

        //when
        List<String> allPossibleMatch = classUnderTest.getAllPossibleMatch("2255");
        //then
        assertThat(allPossibleMatch.contains("CALL"), is(true));
    }

    @Test
    public void shouldGetExpectedMessageByDefaultWith2Words() throws Exception {
        //given

        //when
        List<String> allPossibleMatch = classUnderTest.getAllPossibleMatch("225563");
        //then
        assertThat(allPossibleMatch.contains("CALL-ME"), is(true));
    }


    @Test
    public void shouldGetExpectedMessageByDefaultWith2WordsAndSeparator() throws Exception {
        //given

        //when
        List<String> allPossibleMatch = classUnderTest.getAllPossibleMatch("2255.63");
        //then
        assertThat(allPossibleMatch.contains("CALL-ME"), is(true));
    }
}