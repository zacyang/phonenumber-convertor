package com.aconex.convertor.model;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by twer on 12/3/15.
 */
public final class MatchingObject {
    private final String originalNumbers;
    private final String allocatedNumbers;
    private final Set<String> matchingResults;

    public MatchingObject(final String originalNumbers,
                          final String allocatedNumbers) {
        this.originalNumbers = originalNumbers;
        this.allocatedNumbers = allocatedNumbers;
        this.matchingResults = new LinkedHashSet<>();
    }


    void addMatch(final String result) {
        matchingResults.add(result);
    }
}
