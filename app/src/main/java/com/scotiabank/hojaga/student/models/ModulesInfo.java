package com.scotiabank.hojaga.student.models;

import java.util.Arrays;

/**
 * Created by gauravmalvankar on 2018-09-28.
 */
public class ModulesInfo {

    private String title;
    private Integer id;
    private String keywords[];


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
