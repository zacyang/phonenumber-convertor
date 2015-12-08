package com.aconex.convertor.builder;

import com.aconex.convertor.generator.MatchingObjectGenerator;
import com.aconex.convertor.generator.CriteriaGenerator;
import com.aconex.convertor.model.MatchingMetaInfo;
import com.aconex.convertor.query.MatchingChunk;
import com.aconex.convertor.query.QueryExecutor;

import java.util.List;

public class Converter {
    private final QueryExecutor executor;
    private final MatchingObjectGenerator reader;
    private final CriteriaGenerator generator;

    public Converter(final QueryExecutor executor,
                     final MatchingObjectGenerator reader,
                     final CriteriaGenerator generator) {
        this.executor = executor;
        this.reader = reader;
        this.generator = generator;
    }

    public List<String> getMatchingPhraseFor(String phoneNumber) {
        MatchingMetaInfo matchingObject = reader.getMatchingObject(phoneNumber);
        List<MatchingChunk> matchingChunks = generator.generateCriteria(matchingObject);
        List<String> possibleMatches = executor.getPossibleMatches(matchingChunks);
        //TODO:filter
//        List<MatchingMetaInfo> objects = shuff(phoneNumber);
//        ArrayList arrayList = new ArrayList();
//        arrayList.add("CALL-ME");
//
//        return arrayList;
        return possibleMatches;
    }
}
