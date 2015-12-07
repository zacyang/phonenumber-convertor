package com.aconex.convertor.query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class will generate all the possible combination for given criteria
 */
public class CriteriaInterpreter {
    private List<String> recLoopAllPossibleCombination(final List<String> current,
                                                       final List<List<String>> rest,
                                                       int i) {
        List<String> result = new ArrayList<>();
        if (i >= rest.size()) {
            return current;
        }
        List<String> strings = rest.get(i);
        for (final String x : current) {
            for (final String tobeAppend : strings) {
                String e = x + tobeAppend;
                result.add(new String(e));
            }
        }
        return recLoopAllPossibleCombination(result, rest, ++ i);
    }

    public List<String> getAllCombos(final Criteria criteria) {
        List<List<String>> lists = criteria.getCriteriaList();
        List<String> result = recLoopAllPossibleCombination(lists.get(0), lists, 1);
        return result.stream().filter(s -> s.length() < lists.size() + 1).collect(Collectors.toList());
    }
}
