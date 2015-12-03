package com.aconex.convertor.builder;

import com.aconex.convertor.model.MatchingObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by twer on 12/3/15.
 */
public class Converter {
    public List<String> getMatchingPhraseFor(String phoneNumber) {
        List<MatchingObject> objects = shuff(phoneNumber);
        ArrayList arrayList = new ArrayList();
        arrayList.add("CALL-ME");

        return arrayList;
    }

    private List<MatchingObject> shuff(String phoneNumber) {
        return null;
    }
}
