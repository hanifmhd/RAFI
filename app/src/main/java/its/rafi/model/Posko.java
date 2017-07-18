package its.rafi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 6/9/2017.
 */
public class Posko {
    @SerializedName("poi_name")
    @Expose
    private String posko_name;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    public Posko(String b){
        posko_name = b;
    }

    public void setPosko_name(String posko_name) {
        this.posko_name = posko_name;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPosko_name() {
        return posko_name;
    }
}
