package its.rafi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 6/9/2017.
 */
public class Tl{

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("tl_name")
    @Expose
    private String tl_name;

    @SerializedName("tl_name_full")
    @Expose
    private String tl_name_full;


    @SerializedName("flag")
    @Expose
    private String flag;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setTl_name(String tl_name) {
        this.tl_name = tl_name;
    }

    public void setTl_name_full(String tl_name_full) {
        this.tl_name_full = tl_name_full;
    }

    public String getTl_name_full() {
        return tl_name_full;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getTl_name() {
        return tl_name;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }
}
