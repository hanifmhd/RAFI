package its.rafi.util;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;

/**
 * Created by ASUS on 6/9/2017.
 */

public class LocationService extends Service {
    public static final String Stub = null;
    LocationManager mlocmag;
    LocationListener mlocList;
    private double lat, longn;

    @Override
    public void onCreate() {
        super.onCreate();
        mlocmag = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mlocList = new MyLocationList();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            Location loc = mlocmag.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (loc == null) {
                loc = mlocmag.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            UpdateWithNewLocation(loc);
            mlocmag.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 200, mlocList);
        }
        else{
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Location loc = mlocmag.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (loc == null) {
                    loc = mlocmag.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
                UpdateWithNewLocation(loc);
                mlocmag.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0 , 200, mlocList);
            }

        }


    }



    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location loc = mlocmag.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (loc == null) {
                loc = mlocmag.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if(loc!=null){
                    UpdateWithNewLocation(loc);
                }
            }
            else{
                UpdateWithNewLocation(loc);
            }
        }

        return START_STICKY;
    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    private void UpdateWithNewLocation(final Location current) {


        SessionManager session = new SessionManager(this);
        if(current!= null){
            session.setLOC(current.getLatitude()+","+current.getLongitude());
        }

    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    public class MyLocationList implements LocationListener {

        public void onLocationChanged(Location arg0) {
            UpdateWithNewLocation(arg0);
        }

        public void onProviderDisabled(String provider) {
            // Toast.makeText(getApplicationContext(), "GPS Disable ",
            //           Toast.LENGTH_LONG).show();
        }

        public void onProviderEnabled(String provider) {
            //Toast.makeText(getApplicationContext(), "GPS enabled",
            //      Toast.LENGTH_LONG).show();
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

    }
}