package com.klimov.entity;

public enum SortingType {
    ASC, DESC;

    public static SortingType stringToEnum(String str) {
        SortingType style;
        try {
            style = SortingType.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            style = ASC;
        }
        return style;
    }
}
