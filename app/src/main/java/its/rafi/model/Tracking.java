package its.rafi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 6/9/2017.
 */
public class Tracking {

    @SerializedName("location_name")
    @Expose
    private String location_name;

    @SerializedName("activities_hour")
    @Expose
    private String lama_aktivitas;

    @SerializedName("checkin_time")
    @Expose
    private String chekin_time;

    @SerializedName("checkout_time")
    @Expose
    private String chekout_time;

    @SerializedName("distance_checkin")
    @Expose
    private Double distance_checkin;

    @SerializedName("distance_checkout")
    @Expose
    private Double distance_checkout;

    @SerializedName("profiling_noted")
    @Expose
    private String profiling_noted;

    @SerializedName("marketshare_noted")
    @Expose
    private String marketshare_noted;

    @SerializedName("sales_noted")
    @Expose
    private String sales_noted;

    @SerializedName("canteen_noted")
    @Expose
    private String canteen_noted;

    @SerializedName("outlet_noted")
    @Expose
    private String outlet_noted;

    @SerializedName("checkin_latitude")
    @Expose
    private Double checkin_latitude;

    @SerializedName("checkin_longitude")
    @Expose
    private Double checkin_longitude;

    @SerializedName("checkout_latitude")
    @Expose
    private Double checkout_latitude;

    @SerializedName("checkout_longitude")
    @Expose
    private Double checkout_longitude;

    @SerializedName("latitude")
    @Expose
    private Double latitude;

    @SerializedName("longitude")
    @Expose
    private Double longitude;


    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setCheckout_longitude(Double checkout_longitude) {
        this.checkout_longitude = checkout_longitude;
    }

    public void setCheckout_latitude(Double checkout_latitude) {
        this.checkout_latitude = checkout_latitude;
    }

    public void setCheckin_latitude(Double checkin_latitude) {
        this.checkin_latitude = checkin_latitude;
    }

    public void setCheckin_longitude(Double checkin_longitude) {
        this.checkin_longitude = checkin_longitude;
    }

    public Double getCheckin_latitude() {
        return checkin_latitude;
    }

    public Double getCheckin_longitude() {
        return checkin_longitude;
    }

    public Double getCheckout_latitude() {
        return checkout_latitude;
    }

    public Double getCheckout_longitude() {
        return checkout_longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setCanteen_noted(String canteen_noted) {
        this.canteen_noted = canteen_noted;
    }

    public void setProfiling_noted(String profiling_noted) {
        this.profiling_noted = profiling_noted;
    }

    public void setMarketshare_noted(String marketshare_noted) {
        this.marketshare_noted = marketshare_noted;
    }

    public void setSales_noted(String sales_noted) {
        this.sales_noted = sales_noted;
    }

    public String getCanteen_noted() {
        return canteen_noted;
    }

    public void setOutlet_noted(String outlet_noted) {
        this.outlet_noted = outlet_noted;
    }

    public String getOutlet_noted() {
        return outlet_noted;
    }

    public String getMarketshare_noted() {
        return marketshare_noted;
    }

    public String getProfiling_noted() {
        return profiling_noted;
    }

    public String getSales_noted() {
        return sales_noted;
    }



    public void setChekout_time(String chekout_time) {
        this.chekout_time = chekout_time;
    }

    public String getChekout_time() {
        return chekout_time;
    }

    public void setChekin_time(String chekin_time) {
        this.chekin_time = chekin_time;
    }

    public String getChekin_time() {
        return chekin_time;
    }

    public Double getDistance_checkin() {
        return distance_checkin;
    }

    public void setDistance_checkin(Double distance_checkin) {
        this.distance_checkin = distance_checkin;
    }
    public void setDistance_checkout(Double distance_checkout) {
        this.distance_checkout = distance_checkout;
    }

    public void setLama_aktivitas(String lama_aktivitas) {
        this.lama_aktivitas = lama_aktivitas;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public Double getDistance_checkout() {
        return distance_checkout;
    }

    public String getLama_aktivitas() {
        return lama_aktivitas;
    }

    public String getLocation_name() {
        return location_name;
    }
}
