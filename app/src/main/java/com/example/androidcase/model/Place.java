package com.example.androidcase.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Place {

    @SerializedName("point")
    @Expose
    private List<Double> point = null;

    public Place(List<Double> point) {
        this.point = point;
    }

    public List<Double> getPoint() {
        return point;
    }

    public void setPoint(List<Double> point) {
        this.point = point;
    }

}