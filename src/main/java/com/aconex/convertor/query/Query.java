package com.aconex.convertor.query;

/**
 * Created by twer on 12/3/15.
 */
public interface Query<T> {
    T getItemStartsWith(Criteria criteria);
}
