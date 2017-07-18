package com.example.ranggarizky.nami.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ranggarizky on 1/23/2017.
 */
public class Ketos {

    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("noHp")
    @Expose
    private String noHp;
    @SerializedName("tanggalLahir")
    @Expose
    private String tanggalLahir;
    @SerializedName("agama")
    @Expose
    private String agama;
    @SerializedName("hobby")
    @Expose
    private String hobby;

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAgama() {
        return agama;
    }

    public String getHobby() {
        return hobby;
    }

    public String getNama() {
        return nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }
}
