package com.aconex.convertor.encode;

import com.aconex.convertor.config.ApplicationConfig;
import com.aconex.convertor.dictionary.DictionarySource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class DictionaryEncoder {
    private final Map<String, String> encodeConfig;
    private final List<String> ALL_DIGITAL = asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");

    public DictionaryEncoder(final ApplicationConfig config) {
        this.encodeConfig = config.getEncodingConfig();
    }

    public Map<String, List<String>> encodeDictionary(final DictionarySource sourceDictionary)  {
        Map<String, List<String>> map = new HashMap<>();
        List<String> dictionary = sourceDictionary.getWords();
        dictionary.forEach(word -> updateEncodedMap(map, word));
        ALL_DIGITAL.forEach(digital -> addDefaultDigitalEncodingToItself(map, digital));
        return map;
    }

    private void addDefaultDigitalEncodingToItself(final Map<String, List<String>> map,
                                                   final String encode) {
        updateMap(map, encode, encode);
    }

    private void updateMap(final Map<String, List<String>> map,
                           final String encode,
                           final String encode1) {
        List<String> associatedWords = map.get(encode);
        List<String> newWords = associatedWords == null ? new ArrayList<String>() : associatedWords;
        newWords.add(encode1);
        map.put(encode, newWords);
    }

    private void updateEncodedMap(final Map<String, List<String>> map,
                                  final String word) {
        if (word.length() > 0) {
            String key = encodeForWord(word);
            updateMap(map, key, word);
        }
    }

    public String encodeForWord(final String line) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            String originalCharAtPosition = String.valueOf(c);
            String associatedEncodeValue = encodeConfig.get(originalCharAtPosition.toUpperCase());
            if (associatedEncodeValue != null) {
                result.append(associatedEncodeValue);
            }
        }
        return result.toString();
    }

}
