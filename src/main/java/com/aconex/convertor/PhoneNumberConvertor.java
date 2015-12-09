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
    private static ApplicationConfig applicationConfig = new ApplicationConfig();
     private final Query query;

    public PhoneNumberConvertor(String dictionaryPath) {
        DictionaryEncoder encoder = new DictionaryEncoder(applicationConfig);
        Map<String, List<String>> map = encoder.encodeDictionary(new TextSourceDictionary(dictionaryPath));
        query = new FullMatchQuery(map);
    }

    public PhoneNumberConvertor() {
        this(null);
    }

    public List<String> getAllPossibleMatch(String number) {
        String numberWithSpaceAndPunctuationRemoved = removeSpaceAndPunctuation(number);
        List<MatchingResult> matched = query.getMatched(new MatchingMetaInfo(numberWithSpaceAndPunctuationRemoved));
        List<String> result = new ArrayList<>();
        for (MatchingResult chunk : matched) {
            List<String> allCombos = new MatchingResultInterpreter(applicationConfig).getAllCombos(chunk);
            result.addAll(allCombos);
        }
        return result;
    }
}
