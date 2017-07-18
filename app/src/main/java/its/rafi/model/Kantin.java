package com.example.ranggarizky.nami.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ranggarizky on 1/20/2017.
 */
public class Kantin implements Serializable {

    @SerializedName("mode")
    @Expose
    private String mode;

    @SerializedName("path_image")
    @Expose
    private String path_image;

    @SerializedName("npsn")
    @Expose
    private String npsn;

    @SerializedName("id_outlet_sefiia")
    @Expose
    private String id_outlet_sefiia;

    @SerializedName("outlet_name")
    @Expose
    private String outlet_name;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("outlet_type")
    @Expose
    private String outlet_type;

    @SerializedName("owner_name")
    @Expose
    private String owner_name;

    @SerializedName("owner_contact")
    @Expose
    private String owner_contact;

    @SerializedName("rs_mkios")
    @Expose
    private String rs_mkios;

    @SerializedName("canvaser_name")
    @Expose
    private String canvaser_name;

    @SerializedName("sales_telkomsel")
    @Expose
    private String sales_telkomsel;

    @SerializedName("sales_ooredoo")
    @Expose
    private String sales_ooredoo;

    @SerializedName("sales_xl")
    @Expose
    private String sales_xl;

    @SerializedName("sales_axis")
    @Expose
    private String sales_axis;

    @SerializedName("sales_3")
    @Expose
    private String sales_3;

    @SerializedName("sales_others")
    @Expose
    private String sales_others;

    @SerializedName("display_telkomsel")
    @Expose
    private String display_telkomsel;

    @SerializedName("display_ooredoo")
    @Expose
    private String display_ooredoo;

    @SerializedName("display_xl")
    @Expose
    private String display_xl;


    @SerializedName("display_axis")
    @Expose
    private String display_axis;

    @SerializedName("display_3")
    @Expose
    private String display_3;

    @SerializedName("display_others")
    @Expose
    private String display_others;

    @SerializedName("recharge_telkomsel")
    @Expose
    private String recharge_telkomsel;

    @SerializedName("recharge_ooredoo")
    @Expose
    private String recharge_ooredoo;

    @SerializedName("recharge_xl")
    @Expose
    private String recharge_xl;

    @SerializedName("recharge_axis")
    @Expose
    private String recharge_axis;


    @SerializedName("recharge_3")
    @Expose
    private String recharge_3;


    @SerializedName("recharge_others")
    @Expose
    private String recharge_others;

    public void setNpsn(String npsn) {
        this.npsn = npsn;
    }

    public void setCanvaser_name(String canvaser_name) {
        this.canvaser_name = canvaser_name;
    }

    public void setId_outlet_sefiia(String id_outlet_sefiia) {
        this.id_outlet_sefiia = id_outlet_sefiia;
    }

    public void setOutlet_name(String outlet_name) {
        this.outlet_name = outlet_name;
    }

    public void setOutlet_type(String outlet_type) {
        this.outlet_type = outlet_type;
    }

    public void setOwner_contact(String owner_contact) {
        this.owner_contact = owner_contact;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public void setRs_mkios(String rs_mkios) {
        this.rs_mkios = rs_mkios;
    }

    public void setPath_image(String path_image) {
        this.path_image = path_image;
    }

    public String getPath_image() {
        return path_image;
    }

    public String getId_outlet_sefiia() {
        return id_outlet_sefiia;
    }

    public String getCanvaser_name() {
        return canvaser_name;
    }

    public String getNpsn() {
        return npsn;
    }

    public String getOutlet_name() {
        return outlet_name;
    }

    public String getOutlet_type() {
        return outlet_type;
    }

    public String getOwner_contact() {
        return owner_contact;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public String getRs_mkios() {
        return rs_mkios;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setDisplay_3(String display_3) {
        this.display_3 = display_3;
    }

    public void setDisplay_axis(String display_axis) {
        this.display_axis = display_axis;
    }

    public void setDisplay_ooredoo(String display_ooredoo) {
        this.display_ooredoo = display_ooredoo;
    }

    public void setDisplay_telkomsel(String display_telkomsel) {
        this.display_telkomsel = display_telkomsel;
    }

    public void setDisplay_others(String display_others) {
        this.display_others = display_others;
    }

    public void setDisplay_xl(String display_xl) {
        this.display_xl = display_xl;
    }

    public void setRecharge_3(String recharge_3) {
        this.recharge_3 = recharge_3;
    }

    public void setRecharge_ooredoo(String recharge_ooredoo) {
        this.recharge_ooredoo = recharge_ooredoo;
    }

    public void setRecharge_axis(String recharge_axis) {
        this.recharge_axis = recharge_axis;
    }

    public void setSales_3(String sales_3) {
        this.sales_3 = sales_3;
    }

    public void setRecharge_others(String recharge_others) {
        this.recharge_others = recharge_others;
    }

    public void setRecharge_telkomsel(String recharge_telkomsel) {
        this.recharge_telkomsel = recharge_telkomsel;
    }

    public void setRecharge_xl(String recharge_xl) {
        this.recharge_xl = recharge_xl;
    }

    public void setSales_axis(String sales_axis) {
        this.sales_axis = sales_axis;
    }

    public void setSales_ooredoo(String sales_ooredoo) {
        this.sales_ooredoo = sales_ooredoo;
    }

    public void setSales_others(String sales_others) {
        this.sales_others = sales_others;
    }

    public void setSales_telkomsel(String sales_telkomsel) {
        this.sales_telkomsel = sales_telkomsel;
    }

    public void setSales_xl(String sales_xl) {
        this.sales_xl = sales_xl;
    }

    public String getDisplay_telkomsel() {
        return display_telkomsel;
    }

    public String getRecharge_telkomsel() {
        return recharge_telkomsel;
    }

    public String getDisplay_3() {
        return display_3;
    }

    public String getDisplay_axis() {
        return display_axis;
    }

    public String getDisplay_ooredoo() {
        return display_ooredoo;
    }

    public String getDisplay_others() {
        return display_others;
    }

    public String getDisplay_xl() {
        return display_xl;
    }

    public String getRecharge_3() {
        return recharge_3;
    }

    public String getRecharge_axis() {
        return recharge_axis;
    }

    public String getRecharge_ooredoo() {
        return recharge_ooredoo;
    }

    public String getRecharge_others() {
        return recharge_others;
    }

    public String getRecharge_xl() {
        return recharge_xl;
    }

    public String getSales_3() {
        return sales_3;
    }

    public String getSales_axis() {
        return sales_axis;
    }

    public String getSales_ooredoo() {
        return sales_ooredoo;
    }

    public String getSales_others() {
        return sales_others;
    }

    public String getSales_telkomsel() {
        return sales_telkomsel;
    }

    public String getSales_xl() {
        return sales_xl;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }
}