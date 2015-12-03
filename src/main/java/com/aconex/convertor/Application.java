package com.aconex.convertor;


import com.aconex.convertor.builder.Converter;
import com.aconex.convertor.builder.ConverterBuilder;

import java.util.List;
import java.util.stream.Stream;

public class Application {

    public static void main(String[] args) {
        String inputNumber =
                parseCommandLineArgs(args);
        Converter converter = ConverterBuilder.getInstance().config().build();
        List<String> result = converter.getMatchingPhraseFor(inputNumber);
        printOutResult(result);

    }

    private static void printOutResult(List<String> result) {

    }

    private static String parseCommandLineArgs(String[] args) {
        return null;
    }
}
