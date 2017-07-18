package com.example.ranggarizky.nami.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ranggarizky on 2/19/2017.
 */
public class PlaceObject {
    @SerializedName("merchant_name")
    @Expose
    private String merchant_name;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("institution_name")
    @Expose
    private String institution_name;

    @SerializedName("npsn")
    @Expose
    private String npsn;

    @SerializedName("id_outlet_sefiia")
    @Expose
    private String id_outlet_sefiia;

    @SerializedName("outlet_name")
    @Expose
    private String outlet_name;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId_outlet_sefiia(String id_outlet_sefiia) {
        this.id_outlet_sefiia = id_outlet_sefiia;
    }



    public void setInstitution_name(String institution_name) {
        this.institution_name = institution_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public void setOutlet_name(String outlet_name) {
        this.outlet_name = outlet_name;
    }

    public String getId_outlet_sefiia() {
        return id_outlet_sefiia;
    }

    public String getInstitution_name() {
        return institution_name;
    }

    public String getAddress() {
        return address;
    }

    public void setNpsn(String npsn) {
        this.npsn = npsn;
    }

    public String getNpsn() {
        return npsn;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public String getOutlet_name() {
        return outlet_name;
    }
}
