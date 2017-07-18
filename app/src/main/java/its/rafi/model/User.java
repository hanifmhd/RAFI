package its.rafi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 6/9/2017.
 */

public class User {
    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("branch")
    @Expose
    private String branch;

    @SerializedName("user_name")
    @Expose
    private String username;

    @SerializedName("pass_code")
    @Expose
    private String password;

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("user_type")
    @Expose
    private String user_type;

    @SerializedName("flag")
    @Expose
    private String flag;

    @SerializedName("regional")
    @Expose
    private String regional;

    @SerializedName("area")
    @Expose
    private String area;

    @SerializedName("cluster")
    @Expose
    private String cluster;

    @SerializedName("sub_branch")
    @Expose
    private String sub_branch;

    public void setLatitude(String latitude){this.latitude = latitude;}

    public void setLongitude (String longitude) {this.longitude = longitude;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public void setSub_branch(String sub_branch) {
        this.sub_branch = sub_branch;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLatitude(){return latitude;}

    public String getLongitude(){return longitude;}

    public String getArea() {
        return area;
    }

    public String getCluster() {
        return cluster;
    }

    public String getRegional() {
        return regional;
    }

    public String getSub_branch() {
        return sub_branch;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranch() {
        return branch;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }
}
