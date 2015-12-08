package com.aconex.convertor.generator;

import com.aconex.convertor.model.MatchingMetaInfo;
import com.aconex.convertor.query.Criteria;
import com.aconex.convertor.query.MatchingChunk;

import java.util.List;

public class CriteriaGenerator {
    public List<MatchingChunk> generateCriteria(final MatchingMetaInfo matchingObject) {
        int length = matchingObject.getOriginalNumber().length();
//        Criteria criteria = new Criteria().mayStartWith(matchingObject.getReplacementWords().get(0));
//        for (List<String> target : matchingObject.getReplacementWords()) {
//            criteria.nextWordMayBe(target);
//        }
//        List<List<Integer>> allPossiableWordLengthCombination = new ArrayList<>();
//        for(int i=1;i<length;i++){
//            List<Integer> combinationWithFirstLengthI = new ArrayList<>();
//            combinationWithFirstLengthI.add(i);
//        }
//        List<Criteria> collect = matchingObject.getReplacementWords().stream().map(strings ->
//                        creteCriteriaBy(strings)
//        ).collect(Collectors.toList());


//        return asList(new MatchingChunk(asList(
        return null;
    }

    public Criteria generateCriteria(final MatchingMetaInfo matchingObject,
                                     final List<String> cuurentMatchedWords) {

        int originalNumberLength = matchingObject.getOriginalNumberLength();
        return null;
    }
}
