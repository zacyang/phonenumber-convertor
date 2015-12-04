package com.aconex.convertor.query;

import java.util.Set;

public interface Query<T> {
    Set<String> getItemStartsWith(Criteria criteria);
}
