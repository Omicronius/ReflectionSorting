package com.klimov.entity;

public enum SortingType {
    ASC, DESC, UNKNOWN;

    public static SortingType stringToEnum(String str) {
        SortingType style;
        try {
            style = SortingType.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            style = UNKNOWN;
        }
        return style;
    }
}
