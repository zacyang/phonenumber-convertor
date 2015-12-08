package com.aconex.convertor.model;

import java.util.ArrayList;
import java.util.List;

public final class MatchingResult implements Cloneable {
    private final List<List<String>> words;

    public MatchingResult() {
        this.words = new ArrayList<>();
    }

    public MatchingResult(MatchingResult chunk) {
        this.words = new ArrayList<>(chunk.getWordSequence());
    }

    public List<List<String>> getWordSequence() {
        return this.words;
    }

    public void addNextWordPossibleMatching(List<String> strings) {
        this.words.add(strings);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
