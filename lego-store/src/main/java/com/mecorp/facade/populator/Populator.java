package com.mecorp.facade.populator;

public interface Populator<SOURCE, TARGET> {
    void populate(SOURCE source, TARGET target);
}
