package com.aconex.convertor.generator;

import com.aconex.convertor.config.ApplicationConfig;
import com.aconex.convertor.model.MatchingMetaInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class MatchingObjectGenerator {
    private ApplicationConfig config;

    public MatchingObjectGenerator(final ApplicationConfig config) {
        this.config = config;
    }

    public MatchingMetaInfo getMatchingObject(final String phoneNumber) {
        List<List<String>> possibleWords = new LinkedList<>();
        final int len = phoneNumber.length();
        Map<String, List<String>> digitalWordingMap = config.getDigitalWordingMap();
        for (int i = 0; i < len; i++) {
            char c = phoneNumber.charAt(i);
            List<String> e = digitalWordingMap.get(String.valueOf(c));
            possibleWords.add(e);
        }

        return new MatchingMetaInfo(phoneNumber, possibleWords);
    }
}
