package its.rafi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 6/9/2017.
 */
public class Influencer {

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("jabatan")
    @Expose
    private String jabatan;


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

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getJabatan() {
        return jabatan;
    }
}
