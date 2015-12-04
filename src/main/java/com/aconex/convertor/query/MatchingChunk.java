package com.aconex.convertor.query;

import java.util.List;

public class MatchingChunk {
    private int numberOfWords;
    private List<Criteria> criterias;

    public MatchingChunk(int numberOfWords, List<Criteria> criterias) {
        this.numberOfWords = numberOfWords;
        this.criterias = criterias;
    }
}
