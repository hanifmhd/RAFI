package its.rafi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 6/9/2017.
 */
public class TrackingDetail {

    @SerializedName("note_profiling")
    @Expose
    private String count_profiling;

    @SerializedName("note_market")
    @Expose
    private String count_market;

    @SerializedName("note_ndu")
    @Expose
    private String count_ndu;

    @SerializedName("note_kantin")
    @Expose
    private String count_kantin;

    @SerializedName("note_outlet")
    @Expose
    private String note_outlet;


    public void setCount_kantin(String count_kantin) {
        this.count_kantin = count_kantin;
    }

    public void setCount_market(String count_market) {
        this.count_market = count_market;
    }

    public void setCount_ndu(String count_ndu) {
        this.count_ndu = count_ndu;
    }

    public void setCount_profiling(String count_profiling) {
        this.count_profiling = count_profiling;
    }

    public String getCount_kantin() {
        return count_kantin;
    }

    public String getCount_market() {
        return count_market;
    }

    public String getCount_ndu() {
        return count_ndu;
    }

    public String getCount_profiling() {
        return count_profiling;
    }

    public void setNote_outlet(String note_outlet) {
        this.note_outlet = note_outlet;
    }

    public String getNote_outlet() {
        return note_outlet;
    }
}
