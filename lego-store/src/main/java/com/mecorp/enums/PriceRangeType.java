package com.mecorp.enums;

public enum PriceRangeType {
    PRICE_RANGE_UNDER_10("under $10", 0, 10),
    PRICE_RANGE_BETWEEN_10_20("$10 - $20", 10, 20),
    PRICE_RANGE_BETWEEN_20_50("$20 - $50", 20, 50),
    PRICE_RANGE_BETWEEN_50_75("$50 - $75", 50, 75),
    PRICE_RANGE_BETWEEN_75_100("$75- $100", 75, 100),
    PRICE_RANGE_OVER_100("$100 +", 100, 9999999);

    public final String name;
    public final double minValue;
    public final double maxValue;

    PriceRangeType(String name, double minValue, double maxValue) {
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
}
