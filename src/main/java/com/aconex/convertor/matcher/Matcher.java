package com.aconex.convertor.matcher;

public interface Matcher<S,T>{
    T getMatch(S source);
}
