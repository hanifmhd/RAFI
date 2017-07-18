package its.rafi;

/**
 * Created by ASUS on 6/13/2017.
 */

public class ListItem {

    private String branch;
    private String sales;
    private String target;

    public ListItem(String branch, String sales, String target) {
        this.branch = branch;
        this.sales = sales;
        this.target = target;
    }

    public String getBranch() {
        return branch;
    }

    public String getSales() {
        return sales;
    }

    public String getTarget() {
        return target;
    }
}
