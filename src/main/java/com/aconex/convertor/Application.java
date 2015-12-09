package com.aconex.convertor;

import com.aconex.convertor.config.ApplicationConfig;
import com.aconex.convertor.dictionary.DictionarySource;
import com.aconex.convertor.dictionary.TextSourceDictionary;
import com.aconex.convertor.encode.DictionaryEncoder;
import com.aconex.convertor.model.MatchingMetaInfo;
import com.aconex.convertor.model.MatchingResult;
import com.aconex.convertor.query.FullMatchQuery;
import com.aconex.convertor.query.MatchingResultInterpreter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Application {
    private static ApplicationConfig applicationConfig =new ApplicationConfig();

    public static void main(String[] args) throws IOException {

        String originalNumbers = "2255637";
        DictionarySource textSourceDictionary = new TextSourceDictionary(null);
        DictionaryEncoder encoder = new DictionaryEncoder(applicationConfig);
        Map<String, List<String>> map = encoder.encodeDictionary(textSourceDictionary);

        FullMatchQuery query = new FullMatchQuery(map);
        List<MatchingResult> matched = query.getMatched(new MatchingMetaInfo(originalNumbers));
        getMathcingResult(matched);
    }

    private static void getMathcingResult(List<MatchingResult> matched) {
        List<String> result = new ArrayList<>();
        for (MatchingResult chunk : matched) {
            List<String> allCombos = new MatchingResultInterpreter(applicationConfig).getAllCombos(chunk);
            result.addAll(allCombos);
        }
        System.out.println("-----------------");
        System.out.println(Arrays.toString(result.toArray()));
    }

}