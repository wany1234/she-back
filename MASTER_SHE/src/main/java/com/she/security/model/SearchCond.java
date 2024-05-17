package com.she.security.model;

import java.io.Serializable;

public class SearchCond implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String searchText = "";
    private Boolean useYn;

    protected SearchCond() {

    }

    public SearchCond(String searchText) {
        super();
        this.searchText = searchText;
    }

    public SearchCond(String searchText, Boolean useYn) {
        super();
        this.searchText = searchText;
        this.useYn = useYn;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public Boolean getUseYn() {
        return useYn;
    }

    public void setUseYn(Boolean useYn) {
        this.useYn = useYn;
    }

}
