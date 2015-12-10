package com.aconex.convertor;

import com.aconex.convertor.config.ApplicationConfig;
import com.aconex.convertor.dictionary.TextSourceDictionary;
import com.aconex.convertor.encode.DictionaryEncoder;
import com.aconex.convertor.model.MatchingMetaInfo;
import com.aconex.convertor.model.MatchingResult;
import com.aconex.convertor.query.FullMatchQuery;
import com.aconex.convertor.query.MatchingResultInterpreter;
import com.aconex.convertor.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.aconex.convertor.helper.WordEscapeHelper.removeSpaceAndPunctuation;

public class PhoneNumberConvertor {
    private final static ApplicationConfig APPLICATION_CONFIG = new ApplicationConfig();
    private final Query query;

    public PhoneNumberConvertor(final String dictionaryPath) {
        DictionaryEncoder encoder = new DictionaryEncoder(APPLICATION_CONFIG);
        Map<String, List<String>> map = encoder.encodeDictionary(new TextSourceDictionary(dictionaryPath));
        query = new FullMatchQuery(map);
    }

    public PhoneNumberConvertor() {
        this(null);
    }

    public List<String> getAllPossibleMatch(final String number) {
        MatchingResultInterpreter matchingResultInterpreter = new MatchingResultInterpreter(APPLICATION_CONFIG);
        String numberWithSpaceAndPunctuationRemoved = removeSpaceAndPunctuation(number);
        List<MatchingResult> matched = query.getMatched(new MatchingMetaInfo(numberWithSpaceAndPunctuationRemoved));
        List<String> result = new ArrayList<>();
        matched.forEach(chunk -> result.addAll(matchingResultInterpreter.getAllCombos(chunk)));
        return result;
    }
}
