package com.aconex.convertor.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MatchingChunk  implements Cloneable{
    private final List<List<String>> words;


    public MatchingChunk() {
        this.words = new ArrayList<>();
    }

    public MatchingChunk(MatchingChunk chunk) {
        this.words = new ArrayList<>(chunk.getWordsSequance());
        }


    public <T> ArrayList<ArrayList<T>> deepCopy(List<List<String>> source) {
        ArrayList<ArrayList<T>> dest = new ArrayList<ArrayList<T>>();
        for(List<String> innerList : source) {
            dest.add(new ArrayList<T>((Collection<? extends T>) innerList));
        }
        return dest;
    }
    public List<List<String>> getWordsSequance() {
        return this.words;
    }

    public void addNextWordPossiableMathcing(List<String> strings) {
        //append on size
        words.add(strings);
    }

    public int getNumberOfWords() {
        return 1;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
