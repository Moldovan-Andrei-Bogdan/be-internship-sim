package com.mecorp.facade.dto;

import com.mecorp.enums.SortType;

public class PageRequest {
    private SortType sortType;

    private Integer pageNumber;

    private Integer pageSize;

    public PageRequest(
            SortType sortType,
            Integer pageNumber,
            Integer pageSize
    ) {
        this.sortType = sortType;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public PageRequest() {}

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
