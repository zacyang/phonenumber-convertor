package com.aconex.convertor;


import com.aconex.convertor.config.ApplicationConfig;
import com.aconex.convertor.encode.DictionaryEncoder;
import com.aconex.convertor.model.MatchingMetaInfo;
import com.aconex.convertor.model.MatchingResult;
import com.aconex.convertor.query.Criteria;
import com.aconex.convertor.query.FullMatchQuery;
import com.aconex.convertor.query.MatchingResultInterpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Application {

    public static void main(String[] args) throws IOException {
//TODO: dictionary int
//        String originalNumbers = "225563";
        String originalNumbers = "2255637";
        Map<String, List<String>> map = encodeDictionary();
        FullMatchQuery dictionaryTOquery = new FullMatchQuery(map);
        List<MatchingResult> matched = dictionaryTOquery.getMatched(new MatchingMetaInfo(originalNumbers));
//TODO: interpreter
        getMathcingResult(matched);
    }

    private static void getMathcingResult(List<MatchingResult> matched) {
        List<String> result = new ArrayList<>();
        for (MatchingResult chunk : matched) {
            Criteria criteria = new Criteria();
            List<List<String>> wordsSequance = chunk.getWordSequence();
            for (List<String> words : wordsSequance) {
                criteria.nextWordMayBe(words);
            }

            List<String> allCombos = new MatchingResultInterpreter(new ApplicationConfig()).getAllCombos(criteria);
            result.addAll(allCombos);
        }
        System.out.println("-----------------");
        System.out.println(Arrays.toString(result.toArray()));
    }

    private static Map<String, List<String>> encodeDictionary() throws IOException {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        ClassLoader classLoader = FullMatchQuery.class.getClassLoader();
        File file = new File(classLoader.getResource("dic.txt").getFile());
        FileReader in1 = new FileReader(file);
        BufferedReader in = new BufferedReader(in1);
        String line = "";
        FullMatchQuery dic = new FullMatchQuery(null);
        DictionaryEncoder encoder = new DictionaryEncoder(new ApplicationConfig().getEncodingConfig());
        while ((line = in.readLine()) != null) {
            if (line.length() > 0) {

                String key = encoder.encodeForWord(line);
                List<String> associatedWords = map.get(key);
                if (associatedWords == null) {
                    List<String> newLine = new ArrayList<>();
                    newLine.add(line);
                    map.put(key, newLine);
                } else {
                    associatedWords.add(new String(line));
                    map.put(key, associatedWords);
                }

            }
        }
        in.close();
        return map;
    }

}
