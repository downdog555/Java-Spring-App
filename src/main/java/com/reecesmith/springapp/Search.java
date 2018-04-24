package com.reecesmith.springapp;

public class Search
{
    String searchValue;

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Search(String searchValue) {

        this.searchValue = searchValue;
    }
}
