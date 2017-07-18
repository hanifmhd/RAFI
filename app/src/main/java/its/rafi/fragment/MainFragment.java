package its.rafi.fragment;

/**
 * Created by ASUS on 6/11/2017.
 */

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.crash.FirebaseCrash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import its.rafi.API;
import its.rafi.InputNDUActivity;
import its.rafi.R;
import its.rafi.model.Posko;
import its.rafi.model.ResponseApi;
import its.rafi.util.GlobalVariabel;
import its.rafi.util.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMyLocationChangeListener {
    private GoogleApiClient googleApiClient;
    Location current;
    private SessionManager sessionManager;
    private String currentLoc;
    private GoogleMap mgoogleMap;
    private Activity context;
    private Map<Marker, Posko> poskoMarkersMap = new HashMap<Marker, Posko>();
    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {;
        setupmap();
        getcurrentLoc();
        return inflater.inflate(R.layout.fragment_aoc_summary, container,false);

    }

    private void setupmap() {
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
        //Initializing googleapi client
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void getcurrentLoc() {
        sessionManager = new SessionManager(getActivity());
        FirebaseCrash.log(sessionManager.getUid());
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager mlocmag = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            Location loc = mlocmag.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (loc == null) {
                loc = mlocmag.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (loc != null) {
                    currentLoc = loc.getLatitude() + "," + loc.getLongitude();
                    sessionManager.setLOC(currentLoc);
                }
            } else {
                currentLoc = loc.getLatitude() + "," + loc.getLongitude();
                sessionManager.setLOC(currentLoc);
            }
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    googleApiClient);
            if (mLastLocation != null) {
                currentLoc = String.valueOf(mLastLocation.getLatitude())+","+String.valueOf(mLastLocation.getLongitude());

            }
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapFragment fragment = (MapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap = googleMap;
        moveCamera(googleMap);

    }

    private void moveCamera(GoogleMap googleMap){
        if(currentLoc != null){
            String[] loc= null;
            loc = currentLoc.split(",");
            CameraPosition position = CameraPosition.builder()
                    .target(new LatLng(Double.valueOf(loc[0]),
                            Double.valueOf(loc[1])))
                    .zoom(14f)
                    .bearing(0.0f)
                    .tilt(0.0f)
                    .build();

            googleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(position), null);
            MarkerOptions options = new MarkerOptions().position( new LatLng(Double.valueOf(loc[0]),
                    Double.valueOf(loc[1])) );
            googleMap.addMarker( options );
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            loadPosko(googleMap);
        }else{
            Toast.makeText(getActivity(),"Aktifkan GPS anda dan refresh kembali",Toast.LENGTH_SHORT).show();
        }
    }

    private void loadPosko(final GoogleMap map){
        //map.clear();
        String[] loc= null;
        loc = currentLoc.split(",");
        // Log.d("lokasi",currentLoc);
        // Toast.makeText(getActivity(),currentLoc,Toast.LENGTH_SHORT).show();

        API apiService = API.client.create(API.class);
        Call<List<Posko>> call = apiService.getNearbyLocation(loc[0],loc[1],"0.5");

        //proses call
        call.enqueue(new Callback<List<Posko>>() {
            @Override
            public void onResponse(Call<List<Posko>> call, Response<List<Posko>> response) {

                List<Posko> apiresponse = response.body();
                if(apiresponse.size() == 0){
                    Toast.makeText(getActivity().getApplicationContext(),"Tidak ditemukan posko pada lokasi anda",Toast.LENGTH_SHORT).show();
                }else{
                    for(int i = 0;i<apiresponse.size();i++){
                        Marker marker = map.addMarker(new MarkerOptions()
                                .position(new LatLng(Float.valueOf(apiresponse.get(i).getLatitude()),Float.valueOf(apiresponse.get(i).getLongitude())))
                                .title(apiresponse.get(i).getPosko_name())
//                                .snippet(apiresponse.get(i).getPosko_name())
                                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
                        poskoMarkersMap.put(marker, apiresponse.get(i));
                    }
                }
            }


            @Override
            public void onFailure(Call<List<Posko>> call, Throwable t) {
                // Log error
                Toast.makeText(getActivity().getApplicationContext(),"Failed to Connect Internet",Toast.LENGTH_SHORT).show();
                Log.e("cok", "onFailure: ", t.fillInStackTrace());

            }
        });

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()        {

            @Override
            public void onInfoWindowClick(Marker marker) {
                final Posko posko = poskoMarkersMap.get(marker);
                new AlertDialog.Builder(getActivity())
                        .setMessage("Anda Akan Check in di "+posko.getPosko_name() +"?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sessionManager.setLocName(posko.getPosko_name());
//                                sessionManager.setLocID(institusi.getNpsn());
                                checkin(posko.getPosko_name(), GlobalVariabel.getInstance().getAllchekin());
                                getActivity().finish();
                                Intent i = new Intent(getActivity(), InputNDUActivity.class);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("Batal", null)
                        .show();
            }

        });

    }

    private void checkin(String locName, final int mode){
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String[] loc= null;
        loc = currentLoc.split(",");

        String[] location = sessionManager.getLoc().split(",");
        // Log.d("wow",locID);
        API apiService = API.client.create(API.class);
        Call<ResponseApi> call = apiService.checkin(
                locName,
                sessionManager.getUid(),
                sessionManager.getUsername(),
                loc[0],loc[1]
        );
        //proses call
        call.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                ResponseApi apiresponse = response.body();
                if(apiresponse.getError().equals("false")){
                    Toast.makeText(getActivity().getApplicationContext(),apiresponse.getMessage(),Toast.LENGTH_SHORT).show();
                    sessionManager.setCheckinID(apiresponse.getCheckin_id());
                    sessionManager.setCheckin(mode);
                    getActivity().finish();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),apiresponse.getMessage(),Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }


            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(),"Failed to Connect Internet",Toast.LENGTH_SHORT).show();
                Log.e("cok", "onFailure: ", t.fillInStackTrace());
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mgoogleMap!=null){
            mgoogleMap.clear();
            moveCamera(mgoogleMap);
        }
    }

    @Override
    public void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onMyLocationChange(Location location) {
        currentLoc = String.valueOf(location.getLatitude())+","+String.valueOf(location.getLongitude());
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    googleApiClient);
            if (mLastLocation != null) {
                currentLoc = String.valueOf(mLastLocation.getLatitude())+","+String.valueOf(mLastLocation.getLongitude());

            }
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
