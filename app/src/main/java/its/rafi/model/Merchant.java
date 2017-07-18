package com.example.ranggarizky.nami.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ranggarizky on 1/20/2017.
 */
public class Merchant {
    @SerializedName("merchant_name")
    @Expose
    private String merchant_name;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("owner_contact")
    @Expose
    private String owner_contact;

    @SerializedName("merchant_type")
    @Expose
    private String merchant_type;

    @SerializedName("pusat_diskon_program")
    @Expose
    private String pusat_diskon_program;

    @SerializedName("owner_name")
    @Expose
    private String owner_name;

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public String getAddress() {
        return address;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setMerchant_type(String merchant_type) {
        this.merchant_type = merchant_type;
    }

    public void setOwner_contact(String owner_contact) {
        this.owner_contact = owner_contact;
    }

    public void setPusat_diskon_program(String pusat_diskon_program) {
        this.pusat_diskon_program = pusat_diskon_program;
    }

    public String getCity() {
        return city;
    }

    public String getMerchant_type() {
        return merchant_type;
    }

    public String getOwner_contact() {
        return owner_contact;
    }

    public String getPusat_diskon_program() {
        return pusat_diskon_program;
    }
}
