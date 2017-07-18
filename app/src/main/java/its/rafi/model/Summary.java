package its.rafi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 6/9/2017.
 */
public class Summary {
    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("branch")
    @Expose
    private String branch;

    @SerializedName("cluster")
    @Expose String cluster;

    @SerializedName("sales")
    @Expose
    private String sales;

    @SerializedName("target_sales")
    @Expose
    private String target_sales;

    @SerializedName("achievement")
    @Expose
    private Double achievement;

    @SerializedName("last_update")
    @Expose
    private String last_update;


    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUser_id() {
        return user_id;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
    public String getBranch() {
        return branch;
    }

    public void setCluster (String cluster){this.cluster = cluster;}
    public String getCluster() {return cluster;}

    public void setSales(String sales) {this.sales = sales;}
    public String getSales() {return sales;}

    public void setTargetSales (String target_sales) {this.target_sales = target_sales;}
    public String getTargetSales() {return target_sales;}

    public void setAchievement(Double achievement) {
        this.achievement = achievement;
    }
    public Double getAchievement() {
        return achievement;
    }

    public void setLastupdate(String last_update) {this.last_update = last_update;}
    public String getLastUpdate(){return last_update;}
}
