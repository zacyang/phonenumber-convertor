package com.aconex.convertor.query;

import com.aconex.convertor.config.ApplicationConfig;
import com.aconex.convertor.model.MatchingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class will generate all the possible combination for given criteria
 */
public class MatchingResultInterpreter {
    private static final String CRITERIA_CAN_NOT_BE_EMPTY = "Criteria can not be empty";
    private final String seperator;

    public MatchingResultInterpreter(final ApplicationConfig applicationConfig) {
        final String separator = applicationConfig.getSeparator();
        this.seperator = separator == null ? "-" : separator;
    }

    private List<String> getAllPossibleCombination(final List<String> current,
                                                   final List<List<String>> rest,
                                                   int i) {
        List<String> result = new ArrayList<>();
        if (i >= rest.size()) {
            return current;
        }
        List<String> strings = rest.get(i);
        for (final String x : current) {
            for (final String tobeAppend : strings) {
                String e = new String(x + this.seperator + tobeAppend);
                result.add(e);
            }
        }
        return getAllPossibleCombination(result, rest, ++i);
    }

    public List<String> getAllCombos(final MatchingResult matchingResult) {
        if (null == matchingResult || matchingResult.getWordSequence().isEmpty()) {
            throw new IllegalArgumentException(CRITERIA_CAN_NOT_BE_EMPTY);
        }
        List<List<String>> lists = matchingResult.getWordSequence();
        List<String> result = getAllPossibleCombination(lists.get(0), lists, 1);
        return result.stream()
                .filter(word ->
                        shouldNotContainsPunctuationDigitals(word))
                .map(word ->
                        word.toUpperCase())
                .collect(Collectors.toList());
    }

    private boolean shouldNotContainsPunctuationDigitals(String word) {
        Pattern pattern = Pattern.compile("(\\d{1}"+this.seperator+"\\d{1})|\\d{2}");
        Matcher matcher = pattern.matcher(word);
        return !matcher.find();
    }
}
