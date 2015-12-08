package com.aconex.convertor.query;

import com.aconex.convertor.model.MatchingMetaInfo;

import java.util.List;

public interface Query<T> {
    List<MatchingChunk> getMatched(MatchingMetaInfo criteria);
}
