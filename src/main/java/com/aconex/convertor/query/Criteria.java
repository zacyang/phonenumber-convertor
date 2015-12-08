package com.aconex.convertor.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Criteria {
    private final List<List<String>> criteriaList;

    public Criteria() {
        this.criteriaList = new ArrayList<>();
    }

    public Criteria mayStartWith(final List<String> words) {
        this.criteriaList.add(words);
        return this;
    }

    public Criteria nextWordMayBe(final List<String> words) {
        this.criteriaList.add(words);
        return this;
    }

    List<List<String>> getCriteriaList() {
        return Collections.unmodifiableList(this.criteriaList);
    }


}
