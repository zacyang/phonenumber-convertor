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

public class TextSourceDictionary implements DictionarySource {
    private final String dictionaryPath;

    public TextSourceDictionary(String specifiedDicPath) {
        this.dictionaryPath = specifiedDicPath;
    }

    public List<String> getWords() {
        return isDictionaryPathSpecified() ? getWordsFromDefaultDictionary() : getWordsFromSpecificDictionary();
    }

    private boolean isDictionaryPathSpecified() {
        return dictionaryPath == null || "".equals(dictionaryPath);
    }

    private List<String> getWordsFromDefaultDictionary() {
        InputStream in = getClass().getResourceAsStream("/" + ApplicationConfig.DEFAULT_DICT);
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        return input.lines().collect(Collectors.toList());
    }

    private List<String> getWordsFromSpecificDictionary() {
        try (Stream<String> stream = Files.lines(Paths.get(dictionaryPath))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
