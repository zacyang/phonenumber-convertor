package com.aconex.convertor.query;

import com.aconex.convertor.model.MatchingResult;
import com.aconex.convertor.model.MatchingMetaInfo;

import java.util.List;

public interface Query<T> {
    List<MatchingResult> getMatched(MatchingMetaInfo criteria);
}
