package its.rafi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ASUS on 6/9/2017.
 */
public class ConfigModel {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("main")
    @Expose
    private String main;

    public void setId(String id) {
        this.id = id;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getId() {
        return id;
    }

    public String getMain() {
        return main;
    }
}