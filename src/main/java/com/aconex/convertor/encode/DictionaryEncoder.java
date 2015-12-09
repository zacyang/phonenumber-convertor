package com.aconex.convertor.encode;

import com.aconex.convertor.config.ApplicationConfig;
import com.aconex.convertor.dictionary.DictionarySource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryEncoder {
    private final Map<String, String> encodeConfig;
    private static final String PUNCTUATION_OR_SPACE = " " ;

    public DictionaryEncoder(final ApplicationConfig config) {
        this.encodeConfig = config.getEncodingConfig();
    }

    private  Map<String, List<String>> encodeDictionary(final DictionarySource sourceDictionary) throws IOException {
        Map<String, List<String>> map = new HashMap<>();
        List<String> dictionary = sourceDictionary.getWords();
        dictionary.stream().filter(word -> word == null || word.contains(PUNCTUATION_OR_SPACE))
                .forEach(word -> updateEncodingResult(map, word));
        return map;
    }

    private void updateEncodingResult(final Map<String, List<String>> map,
                                      final String line) {
        String key = encodeForWord(line);
        List<String> associatedWords = map.get(key);
        if (associatedWords == null) {
            List<String> newLine = new ArrayList<>();
            newLine.add(line);
            map.put(key, newLine);
        } else {
            associatedWords.add(new String(line));
            map.put(key, associatedWords);
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
