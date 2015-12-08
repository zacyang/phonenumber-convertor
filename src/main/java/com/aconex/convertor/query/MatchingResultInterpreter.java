package com.aconex.convertor.query;

import com.aconex.convertor.config.ApplicationConfig;

import java.util.ArrayList;
import java.util.List;

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
                result.add(new String(x + this.seperator + tobeAppend));
            }
        }
        return getAllPossibleCombination(result, rest, ++i);
    }

    public List<String> getAllCombos(final Criteria criteria) {
        if (null == criteria || criteria.getCriteriaList().isEmpty()) {
            throw new IllegalArgumentException(CRITERIA_CAN_NOT_BE_EMPTY);
        }
        List<List<String>> lists = criteria.getCriteriaList();
        List<String> result = getAllPossibleCombination(lists.get(0), lists, 1);
        return result;
    }
}
