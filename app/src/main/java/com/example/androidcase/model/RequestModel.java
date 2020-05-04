package com.example.androidcase.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestModel {

    @SerializedName("places")
    @Expose
    private List<Place> places = null;
    @SerializedName("fuel_consumption")
    @Expose
    private Integer fuelConsumption;
    @SerializedName("fuel_price")
    @Expose
    private Double fuelPrice;

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public Integer getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Integer fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Double getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(Double fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

}