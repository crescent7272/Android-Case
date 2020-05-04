package com.example.androidcase.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseModel {

    @SerializedName("points ")
    @Expose
    private List<Points> points = null;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("distance_unit")
    @Expose
    private String distanceUnit;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("duration_unit")
    @Expose
    private String durationUnit;
    @SerializedName("has_tolls")
    @Expose
    private Boolean hasTolls;
    @SerializedName("toll_count")
    @Expose
    private Integer tollCount;
    @SerializedName("toll_cost ")
    @Expose
    private Integer tollCost;
    @SerializedName("toll_cost_unit")
    @Expose
    private String tollCostUnit;
    @SerializedName("route")
    @Expose
    private List<List<List<Double>>> route = null;
    @SerializedName("provider")
    @Expose
    private String provider;
    @SerializedName("cached")
    @Expose
    private Boolean cached;
    @SerializedName("fuel_usage")
    @Expose
    private Double fuelUsage;
    @SerializedName("fuel_usage_unit")
    @Expose
    private String fuelUsageUnit;
    @SerializedName("fuel_cost")
    @Expose
    private Double fuelCost;
    @SerializedName("fuel_cost_unit")
    @Expose
    private String fuelCostUnit;
    @SerializedName("total_cost")
    @Expose
    private Double totalCost;

    public List<Points> getPoints() {
        return points;
    }

    public void setPoints(List<Points> points) {
        this.points = points;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDurationUnit() {
        return durationUnit;
    }

    public void setDurationUnit(String durationUnit) {
        this.durationUnit = durationUnit;
    }

    public Boolean getHasTolls() {
        return hasTolls;
    }

    public void setHasTolls(Boolean hasTolls) {
        this.hasTolls = hasTolls;
    }

    public Integer getTollCount() {
        return tollCount;
    }

    public void setTollCount(Integer tollCount) {
        this.tollCount = tollCount;
    }

    public Integer getTollCost() {
        return tollCost;
    }

    public void setTollCost(Integer tollCost) {
        this.tollCost = tollCost;
    }

    public String getTollCostUnit() {
        return tollCostUnit;
    }

    public void setTollCostUnit(String tollCostUnit) {
        this.tollCostUnit = tollCostUnit;
    }

    public List<List<List<Double>>> getRoute() {
        return route;
    }

    public void setRoute(List<List<List<Double>>> route) {
        this.route = route;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Boolean getCached() {
        return cached;
    }

    public void setCached(Boolean cached) {
        this.cached = cached;
    }

    public Double getFuelUsage() {
        return fuelUsage;
    }

    public void setFuelUsage(Double fuelUsage) {
        this.fuelUsage = fuelUsage;
    }

    public String getFuelUsageUnit() {
        return fuelUsageUnit;
    }

    public void setFuelUsageUnit(String fuelUsageUnit) {
        this.fuelUsageUnit = fuelUsageUnit;
    }

    public Double getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(Double fuelCost) {
        this.fuelCost = fuelCost;
    }

    public String getFuelCostUnit() {
        return fuelCostUnit;
    }

    public void setFuelCostUnit(String fuelCostUnit) {
        this.fuelCostUnit = fuelCostUnit;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

}