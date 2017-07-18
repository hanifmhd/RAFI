package com.example.ranggarizky.nami.model;

/**
 * Created by ranggarizky on 1/30/2017.
 */
public class Kartu {

    private String xl,telkomsel,ooredo,axis,tri,other;

    public void setAxis(String axis) {
        this.axis = axis;
    }

    public void setOoredo(String ooredo) {
        this.ooredo = ooredo;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void setTelkomsel(String telkomsel) {
        this.telkomsel = telkomsel;
    }

    public void setTri(String tri) {
        this.tri = tri;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    public String getAxis() {
        return axis;
    }

    public String getOoredo() {
        return ooredo;
    }

    public String getOther() {
        return other;
    }

    public String getTelkomsel() {
        return telkomsel;
    }

    public String getTri() {
        return tri;
    }

    public String getXl() {
        return xl;
    }
}
