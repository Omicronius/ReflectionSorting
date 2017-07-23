package com.klimov.entity;

public class SortingRule {
    public String field;
    public SortingType sortingType;

    public SortingRule(String field, SortingType sortingType) {
        this.field = field;
        this.sortingType = sortingType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public SortingType getSortingType() {
        return sortingType;
    }

    public void setSortingType(SortingType sortingType) {
        this.sortingType = sortingType;
    }
}
