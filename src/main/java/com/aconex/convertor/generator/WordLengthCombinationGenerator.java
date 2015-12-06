package com.aconex.convertor.generator;

import com.aconex.convertor.model.MatchingMetaInfo;
import com.aconex.convertor.query.Criteria;
import com.aconex.convertor.query.MatchingChunk;

import java.util.List;

import static java.util.Arrays.asList;

public class WordLengthCombinationGenerator {
    public List<MatchingChunk> generateCriterias(final MatchingMetaInfo matchingObject) {
        return asList(new MatchingChunk(1, asList(new Criteria())));
    }
}
