package com.aconex.convertor.model;

public final class MatchingMetaInfo {
    private final String originalNumber;

    public MatchingMetaInfo(final String originalNumbers) {
        this.originalNumber = originalNumbers;
    }

    public String getOriginalNumber() {
        return originalNumber;
    }

}
