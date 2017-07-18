package com.example.ranggarizky.nami.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by RanggaRizky on 4/24/2017.
 */
public class RequestObject implements Serializable {


    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("request_type")
    @Expose
    private String request_type;

    @SerializedName("request_title")
    @Expose
    private String request_title;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("npsn")
    @Expose
    private String npsn;

    @SerializedName("institution_name")
    @Expose
    private String institution_name;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("sub_district")
    @Expose
    private String sub_district;

    @SerializedName("rejected_reason")
    @Expose
    private String rejected_reason;

    @SerializedName("latitude")
    @Expose
    private Double latitude;

    @SerializedName("longitude")
    @Expose
    private Double longitude;

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setInstitution_name(String institution_name) {
        this.institution_name = institution_name;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setNpsn(String npsn) {
        this.npsn = npsn;
    }

    public void setRequest_title(String request_title) {
        this.request_title = request_title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSub_district(String sub_district) {
        this.sub_district = sub_district;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public void setRejected_reason(String rejected_reason) {
        this.rejected_reason = rejected_reason;
    }

    public String getRejected_reason() {
        return rejected_reason;
    }

    public String getInstitution_name() {
        return institution_name;
    }

    public String getNpsn() {
        return npsn;
    }

    public String getRequest_title() {
        return request_title;
    }

    public String getStatus() {
        return status;
    }

    public String getSub_district() {
        return sub_district;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
