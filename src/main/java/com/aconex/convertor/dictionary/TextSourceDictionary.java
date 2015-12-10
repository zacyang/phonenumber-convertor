package com.aconex.convertor.dictionary;

import com.aconex.convertor.config.ApplicationConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.aconex.convertor.helper.WordEscapeHelper.removeSpaceAndPunctuation;

public class TextSourceDictionary implements DictionarySource {
    private final String dictionaryPath;
    private final ApplicationConfig applicationConfig;


    public TextSourceDictionary(String specifiedDicPath, ApplicationConfig applicationConfig) {
        this.dictionaryPath = specifiedDicPath;
        this.applicationConfig = applicationConfig;
    }

    public List<String> getWords() {
        return isDictionaryPathSpecified() ? getWordsFromDefaultDictionary() : getWordsFromSpecificDictionary();
    }

    private boolean isDictionaryPathSpecified() {
        return dictionaryPath == null || "".equals(dictionaryPath);
    }

    private List<String> getWordsFromDefaultDictionary() {
        InputStream in = getClass().getResourceAsStream("/" + applicationConfig.getDefaultDict());
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        return input.lines().collect(Collectors.toList());
    }

    private List<String> getWordsFromSpecificDictionary() {
        try (Stream<String> stream = Files.lines(Paths.get(dictionaryPath))) {
            return stream.map(word -> removeSpaceAndPunctuation(word)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
