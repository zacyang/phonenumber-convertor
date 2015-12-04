package com.aconex.convertor.query;

public interface Query<T> {
    T getItemStartsWith(Criteria criteria);
}
