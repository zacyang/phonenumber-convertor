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
        lookUp(originalNumber, null, result);
        return result;
    }

    private void lookUp(final String originalNumber,
                        final MatchingResult chunk,
                        final List<MatchingResult> result) {
        dictionary.forEach((k, words) -> {
                    //TODO: result to a objects
                    String partialMatchedReminder = matches(originalNumber, k);
                    if (partialMatchedReminder == null) {
                        return;
                    }
                    MatchingResult newChunk = getMatchingChunk(chunk);
                    newChunk.addNextWordPossibleMatching(words);
                    if (partialMatchedReminder.equals("")) {
                        result.add(newChunk);
                    } else {
                        lookUp(partialMatchedReminder, newChunk, result);
                    }
                }
        );
    }

    private MatchingResult getMatchingChunk(final MatchingResult chunk) {
        MatchingResult newChunk;
        if (chunk == null) {
            newChunk = new MatchingResult();
        } else {
            newChunk = new MatchingResult(chunk);
        }
        return newChunk;
    }

    private String matches(final String number,
                           final String digi) {
        if (number.startsWith(digi)) {
            return number.substring(digi.length(), number.length());
        } else {
            return null;
        }
    }

}
