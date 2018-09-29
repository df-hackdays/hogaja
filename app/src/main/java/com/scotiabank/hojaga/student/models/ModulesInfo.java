package com.scotiabank.hojaga.student.models;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by gauravmalvankar on 2018-09-28.
 */
public class ModulesInfo {

    private String title;
   // private String keywords[];
    private String desc;
    private String id;
//    private String[] keywords;

    private Keywords[] keywords;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Keywords[] getKeywords() {
        return keywords;
    }

    public void setKeywords(Keywords[] keywords) {
        this.keywords = keywords;
    }
}
