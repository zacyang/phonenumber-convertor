package com.aconex.convertor;


import com.aconex.convertor.config.ApplicationConfig;
import com.aconex.convertor.encode.DictionaryEncoder;
import com.aconex.convertor.model.MatchingMetaInfo;
import com.aconex.convertor.model.MatchingResult;
import com.aconex.convertor.query.FullMatchQuery;
import com.aconex.convertor.query.MatchingResultInterpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Application {

    public static final String DIC_TXT = "dic.txt";
    private static ApplicationConfig applicationConfig =new ApplicationConfig();

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
            List<String> allCombos = new MatchingResultInterpreter(applicationConfig).getAllCombos(chunk);
            result.addAll(allCombos);
        }
        System.out.println("-----------------");
        System.out.println(Arrays.toString(result.toArray()));
    }

    private static Map<String, List<String>> encodeDictionary() throws IOException {
        Map<String, List<String>> map = new HashMap<>();
        ClassLoader classLoader = FullMatchQuery.class.getClassLoader();
        File file = new File(classLoader.getResource(DIC_TXT).getFile());
        FileReader in1 = new FileReader(file);
        BufferedReader in = new BufferedReader(in1);
        String line = "";
        DictionaryEncoder encoder = new DictionaryEncoder(applicationConfig);
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
