package com.aconex.convertor.query;

import com.aconex.convertor.model.MatchingMetaInfo;
import com.aconex.convertor.model.MatchingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FullMatchQuery implements Query<String> {
    private static final String QUERY_NUMBER_SHOULD_NOT_BE_NULL_OR_EMPTY = "Query number should not be null or empty";
    private final Map<String, List<String>> dictionary;

    public FullMatchQuery(final Map<String, List<String>> dictionary) {
        this.dictionary = dictionary;

    }

    @Override
    public List<MatchingResult> getMatched(final MatchingMetaInfo criteria) {
        String originalNumber = criteria.getOriginalNumber();
        if (null == originalNumber || originalNumber.isEmpty()) {
            throw new IllegalArgumentException(QUERY_NUMBER_SHOULD_NOT_BE_NULL_OR_EMPTY);
        }
        List<MatchingResult> result = new ArrayList<>();
        lookUp(originalNumber, new MatchingResult(), result);
        return result;
    }

    private void lookUp(final String originalNumber,
                        final MatchingResult chunk,
                        final List<MatchingResult> result) {
        dictionary.forEach((k, words) -> {
                    final NumberMatchingResult numberMatchingResult = matches(originalNumber, k);
                    if (numberMatchingResult.notMatchAtAll) {
                        return;
                    }
                    MatchingResult newChunk =new MatchingResult(chunk);
                    newChunk.addNextWordPossibleMatching(words);
                    if (numberMatchingResult.isFullMatch) {
                        result.add(newChunk);
                    } else {
                        lookUp(numberMatchingResult.rest, newChunk, result);
                    }
                }
        );
    }

    private NumberMatchingResult matches(final String number,
                                         final String digitals) {
        if (number.startsWith(digitals)) {
            return new NumberMatchingResult(number.substring(digitals.length(), number.length()));
        } else {
            return new NumberMatchingResult(null);
        }
    }

    private static class NumberMatchingResult {
        private final String rest;
        boolean isFullMatch;
        boolean isPartialMatch;
        boolean notMatchAtAll;

        NumberMatchingResult(final String rest) {
            this.rest = rest;
            notMatchAtAll = rest == null;
            isFullMatch = "".equals(rest);
            isPartialMatch = !isFullMatch && !notMatchAtAll;
        }
    }

}
