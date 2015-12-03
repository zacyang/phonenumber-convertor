package com.aconex.convertor.matcher;

/**
 * Created by twer on 12/3/15.
 */
public interface Matcher<S,T>{
    T getMatch(S source);
}
