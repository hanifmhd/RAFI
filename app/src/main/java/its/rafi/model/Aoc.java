package its.rafi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 6/9/2017.
 */
public class Aoc {

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("user_name")
    @Expose
    private String user_name;

    @SerializedName("real_name")
    @Expose
    private String real_name;

    @SerializedName("flag")
    @Expose
    private String flag;

    @SerializedName("user_id_tl")
    @Expose
    private String user_id_tl;

    @SerializedName("city")
    @Expose
    private String city;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getReal_name() {
        return real_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getFlag() {
        return flag;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_id_tl(String user_id_tl) {
        this.user_id_tl = user_id_tl;
    }

    public String getUser_id_tl() {
        return user_id_tl;
    }
}
