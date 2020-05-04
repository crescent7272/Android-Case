package com.example.androidcase.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Points {

    @SerializedName("point")
    @Expose
    private List<Double> point = null;
    @SerializedName("provider")
    @Expose
    private String provider;

    public List<Double> getPoint() {
        return point;
    }

    public void setPoint(List<Double> point) {
        this.point = point;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

}