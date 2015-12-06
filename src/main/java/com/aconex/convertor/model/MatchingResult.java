package com.aconex.convertor.model;

import java.util.HashSet;
import java.util.Set;

public final class MatchingResult {
    private final MatchingMetaInfo matchingObject;
    private Set<String> allPossibleMatching = new HashSet<>();

    public MatchingResult(final MatchingMetaInfo matchingObject) {
        this.matchingObject = matchingObject;
    }

    public void addMatchingWord(final String word) {
        this.allPossibleMatching.add(word);
    }
}
