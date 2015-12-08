package com.aconex.convertor.query.datasource;

import com.aconex.convertor.model.MatchingMetaInfo;
import com.aconex.convertor.query.MatchingChunk;
import com.aconex.convertor.query.Query;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextSourceDictionary implements Query<String> {
    private final Map<String, List<String>> dictionary;
    private final Map<String, Integer> encodingConfig;

    public TextSourceDictionary(Map<String, List<String>> dictionary, Map<String, Integer> e) {
        this.dictionary = dictionary;
        this.encodingConfig = new HashMap<>();
        encodingConfig.put("A", 2);
        encodingConfig.put("B", 2);
        encodingConfig.put("C", 2);
        encodingConfig.put("D", 3);
        encodingConfig.put("E", 3);
        encodingConfig.put("F", 3);
        encodingConfig.put("G", 4);
        encodingConfig.put("H", 4);
        encodingConfig.put("I", 4);
        encodingConfig.put("J", 5);
        encodingConfig.put("K", 5);
        encodingConfig.put("L", 5);
        encodingConfig.put("M", 6);
        encodingConfig.put("N", 6);
        encodingConfig.put("O", 6);
        encodingConfig.put("P", 7);
        encodingConfig.put("Q", 7);
        encodingConfig.put("R", 7);
        encodingConfig.put("S", 7);
        encodingConfig.put("T", 8);
        encodingConfig.put("U", 8);
        encodingConfig.put("V", 8);
        encodingConfig.put("W", 9);
        encodingConfig.put("X", 9);
        encodingConfig.put("Y", 9);
        encodingConfig.put("Z", 9);
    }

    @Override
    public List<MatchingChunk> getMatched(MatchingMetaInfo criteria) {
        List<MatchingChunk> result = new ArrayList<>();
        lookUp(criteria.getOriginalNumber(),null, result);
        return result;
    }

    private void lookUp(final String originalNumber,
                                       final MatchingChunk chunk,
                                       final List<MatchingChunk> result) {
        dictionary.forEach((k,words)->{
                String partialMatchedReminder = matches(originalNumber, k);
                    if (partialMatchedReminder == null) {
                       return;
                    }
                    MatchingChunk newChunk;
                    if(chunk == null){
                        newChunk = new MatchingChunk();
                    } else {
                        newChunk = chunk;
                    }
                    newChunk.addNextWordPossiableMathcing(words);
                    if(partialMatchedReminder.equals("")){
                        result.add(newChunk);
                        return;
                    }else{
                        lookUp(partialMatchedReminder,newChunk, result);
                    }
                }
//
        );
//        Set<String> keyset = dictionary.keySet();
//        for(String k : keyset){
//            String partialMatchedReminder = matches(originalNumber, k);
//            if (partialMatchedReminder == null) {
//                continue;
//            }
//
//            List<String> v = dictionary.get(k);
//            MatchingChunk matchingChunk = new MatchingChunk(v);
//            if (partialMatchedReminder == "") {
//                result.add(matchingChunk);
//            } else {
//                result.addAll(lookUp(partialMatchedReminder, result));
//            }
//        }

//        dictionary.forEach((k, v) ->
//                {
//                    String partialMatchedReminder = matches(originalNumber, k);
//                    if (partialMatchedReminder == null) {
//                       return;
//                    }
//
//                    MatchingChunk matchingChunk = new MatchingChunk(v);
//                    if (partialMatchedReminder.equals("")) {
//                        result.add(matchingChunk);
//                    } else {
//                        result.addAll(lookUp(partialMatchedReminder, result));
//                    }
//                }
//        );
//        return result;
    }

    private String matches(String number, String digitals) {
//        if (number.contains(digitals)) {
        if (number.startsWith(digitals)) {
            return number.substring(digitals.length() , number.length() );
        } else {
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        ClassLoader classLoader = TextSourceDictionary.class.getClassLoader();
        File file = new File(classLoader.getResource("dic.txt").getFile());
        FileReader in1 = new FileReader(file);
        BufferedReader in = new BufferedReader(in1);
        String line = "";
        TextSourceDictionary dic = new TextSourceDictionary(null, null);
        while ((line = in.readLine()) != null) {
            if (line.length() > 0) {

                String key = dic.encodeForWord(line);
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
        System.out.println(map.toString());
        TextSourceDictionary dictionaryTOquery = new TextSourceDictionary(map, null);
        List<MatchingChunk> matched = dictionaryTOquery.getMatched(new MatchingMetaInfo("2255", null));
        System.out.println(matched.stream().allMatch(s-> s.equals("call")));

        System.out.println(matched);
    }

    private String encodeForWord(String line) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            String s = String.valueOf(c);
            Integer obj = encodingConfig.get(s.toUpperCase());
            if (obj != null) {

                result.append(obj);
            } else {
                System.out.println("----------");
                System.out.println(s);
                System.out.println(obj);
            }
        }
        return result.toString();
    }


}
