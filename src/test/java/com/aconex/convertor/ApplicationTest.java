package com.aconex.convertor;

import org.junit.Test;

import java.io.IOException;

public class ApplicationTest {
    @Test
    public void willNotThrowAnyExceptionWhenMandatoryParameterIsGiven() throws IOException {
        String[] args = {"-p","225563"};
        Application.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void willThrowIllegalArgumentExceptionWhenMandatoryParameterNotGiven() throws IOException {
        String[] args = {"-p"};
        Application.main(args);
    }

    @Test
    public void willNotThrowAnyExceptionWhenOptionalParameterIsGiven() throws IOException {
        final String dir = System.getProperty("user.dir");
        String passedInDictionary = dir + "/src/test/resources/dic-test.txt";
        String[] args = {"-p","225563", "-d", passedInDictionary};
        Application.main(args);
    }
}
