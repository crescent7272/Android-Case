package com.example.androidcase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnttPricesModel {

    @SerializedName("refrigerated")
    @Expose
    private Double refrigerated;
    @SerializedName("general")
    @Expose
    private Double general;
    @SerializedName("bulk")
    @Expose
    private Double bulk;
    @SerializedName("neogranel")
    @Expose
    private Double neogranel;
    @SerializedName("dangerous")
    @Expose
    private Double dangerous;

    /**
     * No args constructor for use in serialization
     *
     */
    public AnttPricesModel() {
    }

    /**
     *
     * @param refrigerated
     * @param general
     * @param dangerous
     * @param bulk
     * @param neogranel
     */
    public AnttPricesModel(Double refrigerated, Double general, Double bulk, Double neogranel, Double dangerous) {
        super();
        this.refrigerated = refrigerated;
        this.general = general;
        this.bulk = bulk;
        this.neogranel = neogranel;
        this.dangerous = dangerous;
    }

    public Double getRefrigerated() {
        return refrigerated;
    }

    public void setRefrigerated(Double refrigerated) {
        this.refrigerated = refrigerated;
    }

    public Double getGeneral() {
        return general;
    }

    public void setGeneral(Double general) {
        this.general = general;
    }

    public Double getBulk() {
        return bulk;
    }

    public void setBulk(Double bulk) {
        this.bulk = bulk;
    }

    public Double getNeogranel() {
        return neogranel;
    }

    public void setNeogranel(Double neogranel) {
        this.neogranel = neogranel;
    }

    public Double getDangerous() {
        return dangerous;
    }

    public void setDangerous(Double dangerous) {
        this.dangerous = dangerous;
    }

}
