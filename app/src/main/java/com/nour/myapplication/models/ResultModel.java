package com.nour.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class ResultModel {
    public ResultModel(String name) {
        this.name = name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @SerializedName("name")
   private String name ;
}
