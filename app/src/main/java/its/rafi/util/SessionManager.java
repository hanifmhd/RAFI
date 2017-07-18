package its.rafi.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ASUS on 6/9/2017.
 */

public class SessionManager {
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "Rafisession";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_IS_CHECKIN = "isCheckin";
    private static final String KEY_IS_USERTYPE = "usertype";
    private static final String KEY_IS_USERNAME = "username";
    private static final String KEY_IS_UID = "uid";
    private static final String KEY_IS_AREA= "area";
    private static final String KEY_IS_REGIONAL= "regional";
    private static final String KEY_IS_BRANCH= "branch";
    private static final String KEY_IS_SUB_BRANCH= "sub_branch";
    private static final String KEY_IS_CLUSTER= "cluster";
    private static final String KEY_IS_CURRENT_LOC= "current_loc";
    private static final String KEY_IS_LOCATION_NAME= "location_name";
    private static final String KEY_IS_id= "npsn";
    private static final String KEY_IS_checkinID= "checkin_id";
    private static final String KEY_IS_LASTLOGIN= "lastlogin";
    private static final String KEY_IS_SESSION_TIMEOUT= "timeout";
    private static final String FBASE_TOKEN= "fbase_token";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
    }

    public void setLastLogin(long lastlogin) {

        editor.putLong(KEY_IS_LASTLOGIN, lastlogin);
        editor.commit();
    }

    public void setSessionTimeOut(long timeOut) {

        editor.putLong(KEY_IS_SESSION_TIMEOUT, timeOut);
        editor.commit();
    }

    public void setCheckin(int checkin) {

        editor.putInt(KEY_IS_CHECKIN, checkin);
        editor.commit();
    }

    public void setUsername(String username) {

        editor.putString(KEY_IS_USERNAME, username);
        editor.commit();
    }

    public void setFirebaseToken(String token) {

        editor.putString(FBASE_TOKEN, token);
        editor.commit();
    }



    public void setCheckinID(String id) {

        editor.putString(KEY_IS_checkinID, id);
        editor.commit();
    }

    public void setArea(String area) {
        editor.putString(KEY_IS_AREA, area);
        editor.commit();
    }

    public void setLocName(String locName) {
        editor.putString(KEY_IS_LOCATION_NAME, locName);
        editor.commit();
    }

    public void setLocID(String npsn) {
        editor.putString(KEY_IS_id, npsn);
        editor.commit();
    }


    public void setRegional(String regional) {
        editor.putString(KEY_IS_REGIONAL, regional);
        editor.commit();
    }

    public void setBranch(String branch) {
        editor.putString(KEY_IS_BRANCH, branch);
        editor.commit();
    }

    public void setSubranch(String subranch) {
        editor.putString(KEY_IS_SUB_BRANCH, subranch);
        editor.commit();
    }

    public void setLOC(String location) {
        editor.putString(KEY_IS_CURRENT_LOC, location);
        editor.commit();
    }

    public void setCluster(String cluster) {
        editor.putString(KEY_IS_CLUSTER, cluster);
        editor.commit();
    }

    public void setUserType(String userType) {
        editor.putString(KEY_IS_USERTYPE, userType);
        editor.commit();
    }

    public void setUid(String uid) {
        editor.putString(KEY_IS_UID, uid);
        editor.commit();
    }


    public boolean isLogin(){
        return pref.getBoolean(KEY_IS_LOGGEDIN,false);
    }


    public int getCheckin(){
        return pref.getInt(KEY_IS_CHECKIN,0);
    }

    public String getCheckinID(){
        return pref.getString(KEY_IS_checkinID,null);
    }

    public String getFirebaseToken(){
        return pref.getString(FBASE_TOKEN,null);
    }


    public String getUsername(){
        return pref.getString(KEY_IS_USERNAME,null);
    }

    public String getUserType(){
        return pref.getString(KEY_IS_USERTYPE,null);
    }

    public String getLoc(){
        return pref.getString(KEY_IS_CURRENT_LOC,null);
    }

    public String getArea(){
        return pref.getString(KEY_IS_AREA,null);
    }

    public Long getLastLogin(){
        return pref.getLong(KEY_IS_LASTLOGIN,0);
    }

    public Long getSessionTimeout(){
        return pref.getLong(KEY_IS_SESSION_TIMEOUT,0);
    }

    public String getUid(){
        return pref.getString(KEY_IS_UID,null);
    }

    public String getLocName(){
        return pref.getString(KEY_IS_LOCATION_NAME,null);
    }

    public String getLocID(){
        return pref.getString(KEY_IS_id,null);
    }

    public String getcluster(){
        return pref.getString(KEY_IS_CLUSTER,null);
    }

    public String getRegional(){
        return pref.getString(KEY_IS_REGIONAL,null);
    }
    public String getBranch(){
        return pref.getString(KEY_IS_BRANCH,null);
    }
    public String getSubranch(){
        return pref.getString(KEY_IS_SUB_BRANCH,null);
    }
}
