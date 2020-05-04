package com.example.androidcase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnttPricesRequestModel {

    @SerializedName("axis")
    @Expose
    private Integer axis;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("has_return_shipment")
    @Expose
    private Boolean hasReturnShipment;

    /**
     * No args constructor for use in serialization
     *
     */
    public AnttPricesRequestModel() {
    }

    /**
     *
     * @param distance
     * @param axis
     * @param hasReturnShipment
     */
    public AnttPricesRequestModel(Integer axis, Double distance, Boolean hasReturnShipment) {
        super();
        this.axis = axis;
        this.distance = distance;
        this.hasReturnShipment = hasReturnShipment;
    }

    public Integer getAxis() {
        return axis;
    }

    public void setAxis(Integer axis) {
        this.axis = axis;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Boolean getHasReturnShipment() {
        return hasReturnShipment;
    }

    public void setHasReturnShipment(Boolean hasReturnShipment) {
        this.hasReturnShipment = hasReturnShipment;
    }

}