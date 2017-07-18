package its.rafi.util;

/**
 * Created by ASUS on 6/9/2017.
 */
public class GlobalVariabel {
    private static GlobalVariabel mInstance = null;
    private String url;
    private final int KantinMode = 1;
    private final int MarketMode = 2;
    private final int ProfileMode = 3;
    private final int AmbassadorMode = 4;
    private final int NudMode = 5;
    private final int OutletMode = 6;
    private final int CommetMode = 7;
    private final int MerchantMode = 8;

    private int Allchekin = 1;
    private int OutletCheckin = 2;
    private int Chekcout = 3;

    private GlobalVariabel(){
    }

    public static GlobalVariabel getInstance(){
        if(mInstance == null)
        {
            mInstance = new GlobalVariabel();
        }
        return mInstance;
    }

    public int getNudMode() {
        return NudMode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getKantinMode() {
        return KantinMode;
    }

    public int getMarketMode() {
        return MarketMode;
    }

    public int getProfileMode() { return ProfileMode; }

    public int getAmbassadorMode() {
        return AmbassadorMode;
    }

    public int getOutletMode() {
        return OutletMode;
    }

    public int getCommetMode() {
        return CommetMode;
    }

    public int getMerchantMode() {
        return MerchantMode;
    }

    public int getAllchekin() {
        return Allchekin;
    }

    public int getChekcout() {
        return Chekcout;
    }

    public int getOutletCheckin() {
        return OutletCheckin;
    }
}
