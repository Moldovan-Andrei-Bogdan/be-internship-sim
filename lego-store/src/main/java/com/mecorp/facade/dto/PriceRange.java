package com.mecorp.facade.dto;

import java.util.Objects;

public class PriceRange {

    private String name;

    private Double minValue;

    private Double maxValue;

    public PriceRange(String name, Double minValue, Double maxValue) {
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceRange that = (PriceRange) o;
        return Objects.equals(name, that.name) && Objects.equals(minValue, that.minValue) && Objects.equals(maxValue, that.maxValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, minValue, maxValue);
    }
}
