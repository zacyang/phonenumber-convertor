package com.aconex.convertor.model;

import java.util.List;

public final class MatchingObject {
    private final String originalNumber;
    private final List<List<String>> allocatedNumbers;

    public MatchingObject(final String originalNumbers,
                          final List<List<String>> allocatedNumbers) {
        assert (allocatedNumbers != null && !allocatedNumbers.isEmpty());
        this.originalNumber = originalNumbers;
        this.allocatedNumbers = allocatedNumbers;
    }

    public List<String> getReplacementWordAt(int i) {
        if (allocatedNumbers.size() > i) {

            return allocatedNumbers.get(i);
        }
        throw new IllegalArgumentException("the index query for word replacement exceeds the boundary!");
    }

    public String getOriginalNumber() {
        return originalNumber;
    }
}
