package com.nour.myapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BigModel {

    @SerializedName("RestResponse")
    BigModel bigModel;

    @SerializedName("result")
    ArrayList<ResultModel> categoryModelArrayList;

    public BigModel getBigModel() {
        return bigModel;
    }

    public ArrayList<ResultModel> getCategoryModelArrayList() {
        return categoryModelArrayList;
    }

    }



