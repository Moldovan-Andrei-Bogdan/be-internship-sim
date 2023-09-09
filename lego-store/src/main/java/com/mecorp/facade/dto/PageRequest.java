package com.mecorp.facade.dto;

import com.mecorp.enums.SortType;

import java.util.List;
import java.util.Set;

public class PageRequest {
    private SortType sortType;

    private Integer pageNumber;

    private Integer pageSize;

    private Set<String> categoryNames;

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

    public Set<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(Set<String> categoryNames) {
        this.categoryNames = categoryNames;
    }
}
