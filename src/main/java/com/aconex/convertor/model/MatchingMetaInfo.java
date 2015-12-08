package com.aconex.convertor.model;

import java.util.List;

import static java.util.Collections.*;

public final class MatchingMetaInfo {
    private final String originalNumber;
    private final List<List<String>> replacementWords;

    public MatchingMetaInfo(final String originalNumbers,
                            final List<List<String>> wordReplacementList) {
        assert (wordReplacementList != null && !wordReplacementList.isEmpty());
        this.originalNumber = originalNumbers;
        this.replacementWords = wordReplacementList;
    }


    public String getOriginalNumber() {
        return originalNumber;
    }

    public int getOriginalNumberLength(){
        return originalNumber.length();
    }

    public List<List<String>> getReplacementWords() {
        return unmodifiableList(this.replacementWords);
    }
}
