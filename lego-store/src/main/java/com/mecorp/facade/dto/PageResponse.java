package com.mecorp.facade.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<Entity> {
    private Integer nrOfPages;
    private Integer nrOfTotalProducts;
    private Integer firstElem;
    private Integer nrOfElemsOnPage;
    private List<Entity> resultList;
    private Set<String> categoryNames;

    public Integer getNrOfPages() {
        return nrOfPages;
    }

    public void setNrOfPages(Integer nrOfPages) {
        this.nrOfPages = nrOfPages;
    }

    public Integer getNrOfTotalProducts() {
        return nrOfTotalProducts;
    }

    public void setNrOfTotalProducts(Integer nrOfTotalProducts) {
        this.nrOfTotalProducts = nrOfTotalProducts;
    }

    public Integer getFirstElem() {
        return firstElem;
    }

    public void setFirstElem(Integer firstElem) {
        this.firstElem = firstElem;
    }

    public Integer getNrOfElemsOnPage() {
        return nrOfElemsOnPage;
    }

    public void setNrOfElemsOnPage(Integer nrOfElemsOnPage) {
        this.nrOfElemsOnPage = nrOfElemsOnPage;
    }

    public List<Entity> getResultList() {
        return resultList;
    }

    public void setResultList(List<Entity> resultList) {
        this.resultList = resultList;
    }

    public Set<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(Set<String> categoryNames) {
        this.categoryNames = categoryNames;
    }
}
