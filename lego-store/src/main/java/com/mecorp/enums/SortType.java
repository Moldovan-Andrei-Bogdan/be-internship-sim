package com.mecorp.enums;

public enum SortType {
    PRICE_ASC("price ASC"),
    PRICE_DESC("price DESC"),
    NAME_ASC("name ASC"),
    NAME_DESC("name DESC");

    private final String value;

    SortType(String value) {
        this.value = value;
    }

    public static SortType getOrDefault(String value, SortType defaultValue) {
        SortType sortType;
        try {
            sortType = SortType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            sortType = defaultValue;
        }

        return sortType;
    }

    public String getValue() {
        return value;
    }
}
