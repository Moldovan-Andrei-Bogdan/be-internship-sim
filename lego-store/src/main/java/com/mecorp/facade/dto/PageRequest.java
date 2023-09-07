package com.mecorp.facade.dto;

import com.mecorp.enums.SortType;

public class PageRequest {
    private SortType sortType;

    public PageRequest(
            SortType sortType
    ) {
        this.sortType = sortType;
    }

    public PageRequest() {}

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }
}
