package its.rafi;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.OnClick;
import its.rafi.model.Posko;
import its.rafi.model.ResponseApi;
import its.rafi.model.Summary;
import its.rafi.util.GlobalVariabel;
import its.rafi.util.SessionManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.crash.FirebaseCrash;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static its.rafi.R.id.map;
import static its.rafi.R.id.tv_lastupdate;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMyLocationChangeListener {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tv_branch)
    TextView tv_branch;
    @BindView(R.id.tv_sales)
    TextView tv_sales;
    @BindView(R.id.tv_target)
    TextView tv_target;
    @BindView(R.id.txt_nama_aoc)
    TextView txt_nama_aoc;
    @BindView(R.id.txt_achievement)
    TextView txt_achievement;
    @BindView(R.id.tv_lastupdate)
    TextView tv_lastupdate;
    private int mode;

    private GoogleApiClient googleApiClient;
    Location current;
    private SessionManager sessionManager;
    private String currentLoc;
    private Activity activity;
    private GoogleMap mgoogleMap;
    private Activity context;
    private Map<Marker, Posko> poskoMarkersMap = new HashMap<Marker, Posko>();
    ProgressDialog progressDialog;
    private String branch, sales, target, achievement, last_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        activity = this;
        setSupportActionBar(toolbar);
        setupmap();
        getcurrentLoc();
        txt_nama_aoc.setText("Welcome, "+sessionManager.getUsername());
        loadData();
        lastUpdate();
        notifPosko();

    }

    private void setupmap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);
        //Initializing googleapi client0
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void getcurrentLoc() {
        sessionManager = new SessionManager(this);
        FirebaseCrash.log(sessionManager.getUid());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager mlocmag = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    googleApiClient);
            if (mLastLocation != null) {
                currentLoc = String.valueOf(mLastLocation.getLatitude())+","+String.valueOf(mLastLocation.getLongitude());

            }
        }
    }

    private void notifPosko(){
        API apiService = API.client.create(API.class);
        Call<List<Posko>> call = apiService.getnotif(sessionManager.getUid());

        call.enqueue(new Callback<List<Posko>>() {
            @Override
            public void onResponse(Call<List<Posko>> call, Response<List<Posko>> response) {
                List<Posko> apiresponse = response.body();
                if(apiresponse.size() > 0){
                    if(!isFinishing()) {
                        Bitmap bmp =  BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
                        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                                .setLargeIcon(bmp)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Rafi")
                                .setContentText("Periksa Posko Sekitar Anda!")
                                .setAutoCancel(true)
                                .setSound(defaultSoundUri);
                        NotificationManager notificationManager =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(0, notificationBuilder.build());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Posko>> call, Throwable t) {
                Log.e("cok", "onFailure: ", t.fillInStackTrace());
            }
        });

    }

    @OnClick(R.id.btn_detail)
    public void toProfiling(View v){
        Intent intent = new Intent(MapsActivity.this, SummaryActivity.class);
        intent.putExtra("mode", GlobalVariabel.getInstance().getProfileMode());
        startActivity(intent);
    }

    private void loadData(){
        mode = getIntent().getIntExtra("mode",3);
        String[] loc= null;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<List<Summary>> call = null;
        API apiService = API.client.create(API.class);
        if(mode == GlobalVariabel.getInstance().getProfileMode()) {
            call = apiService.getAchievement(sessionManager.getUid());
        }
        //proses call
        call.enqueue(new Callback<List<Summary>>() {
            @Override
            public void onResponse(Call<List<Summary>> call, Response<List<Summary>> response) {
                if(response.body().size() > 0) {
                    if(mode == GlobalVariabel.getInstance().getProfileMode()){
                        branch = response.body().get(0).getBranch();
                        sales = response.body().get(0).getSales();
                        target = response.body().get(0).getTargetSales();
                    }
                    achievement = String.valueOf(response.body().get(0).getAchievement());

                    if(response.body().get(0).getAchievement()<=25){
                        txt_achievement.setTextColor(getResources().getColor(R.color.red));
                    }else if(response.body().get(0).getAchievement()>25 && response.body().get(0).getAchievement() <=50 ){
                        txt_achievement.setTextColor(getResources().getColor(R.color.orange));
                    }else if(response.body().get(0).getAchievement()>50 && response.body().get(0).getAchievement() <=75 ){
                        txt_achievement.setTextColor(getResources().getColor(R.color.blue));
                    }else if(response.body().get(0).getAchievement()>75){
                        txt_achievement.setTextColor(getResources().getColor(R.color.green));
                    }

                    NumberFormat rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);
                    tv_branch.setText(branch);
                    tv_sales.setText(rupiahFormat.format(Double.parseDouble(sales)));
                    tv_target.setText(rupiahFormat.format(Double.parseDouble(target)));
                    txt_achievement.setText(achievement+"%");
                }else{
                    Toast.makeText(getApplicationContext(),"Belum ada data achievement",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Summary>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed to Connect Internet",Toast.LENGTH_SHORT).show();
                Log.e("cok", "onFailure: ", t.fillInStackTrace());

            }
        });

    }

    private void lastUpdate(){
        Call<List<Summary>> call = null;
        API apiService = API.client.create(API.class);
        if(mode == GlobalVariabel.getInstance().getProfileMode()) {
            call = apiService.getLastUpdate();
        }
        //proses call
        call.enqueue(new Callback<List<Summary>>() {
            @Override
            public void onResponse(Call<List<Summary>> call, Response<List<Summary>> response) {
                if(response.body().size() > 0) {
                    if(mode == GlobalVariabel.getInstance().getProfileMode()){
                        last_update = response.body().get(0).getLastUpdate();
                    }
                    tv_lastupdate.setText(last_update);
                }else{
                    Toast.makeText(getApplicationContext(),"Belum ada data achievement",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Summary>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed to Connect Internet",Toast.LENGTH_SHORT).show();
                Log.e("cok", "onFailure: ", t.fillInStackTrace());
            }
        });
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
            Toast.makeText(this,"Aktifkan GPS anda dan refresh kembali",Toast.LENGTH_SHORT).show();
        }
    }

    private void loadPosko(final GoogleMap map){
        //map.clear();
        String[] loc= null;
        loc = currentLoc.split(",");

        API apiService = API.client.create(API.class);
        Call<List<Posko>> call = apiService.getNearbyLocation(loc[0],loc[1],"0.5");

        //proses call
        call.enqueue(new Callback<List<Posko>>() {
            @Override
            public void onResponse(Call<List<Posko>> call, Response<List<Posko>> response) {

                List<Posko> apiresponse = response.body();
                if(apiresponse.size() == 0){
                    Toast.makeText(getApplicationContext(),"Tidak ditemukan posko pada lokasi anda",Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(),"Failed to Connect Internet",Toast.LENGTH_SHORT).show();
                Log.e("cok", "onFailure: ", t.fillInStackTrace());

            }
        });

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()        {

            @Override
            public void onInfoWindowClick(Marker marker) {
                final Posko posko = poskoMarkersMap.get(marker);
                new AlertDialog.Builder(activity)
                        .setMessage("Anda akan check in di "+posko.getPosko_name() +"?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sessionManager.setLocName(posko.getPosko_name());
//                                sessionManager.setLocID(institusi.getNpsn());
                                checkin(posko.getPosko_name(),GlobalVariabel.getInstance().getAllchekin());
                                finish();
                                Intent i = new Intent(MapsActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("Batal", null)
                        .show();
            }

        });

    }


    private void checkin(String locName, final int mode){
        progressDialog = new ProgressDialog(this);
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
                    Toast.makeText(getApplicationContext(),apiresponse.getMessage(),Toast.LENGTH_SHORT).show();
                    sessionManager.setCheckinID(apiresponse.getCheckin_id());
                    sessionManager.setCheckin(mode);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),apiresponse.getMessage(),Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }


            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed to Connect Internet",Toast.LENGTH_SHORT).show();
                Log.e("cok", "onFailure: ", t.fillInStackTrace());
                progressDialog.dismiss();
            }
        });
    }

    @Override
    protected void onResume() {
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case android.R.id.home:
//                onBackPressed();
//                return true;
            case R.id.menuRefresh:
                getcurrentLoc();
                if(mgoogleMap!=null){
                    mgoogleMap.clear();
                    moveCamera(mgoogleMap);
                }
                return true;
            case R.id.menu_Logout:
                logout();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logout(){
        new AlertDialog.Builder(this)
                .setMessage("Apakah anda ingin logout?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SessionManager sessionManager = new SessionManager(getApplicationContext());
                        sessionManager.setLogin(false);
                        Intent i = new Intent(MapsActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public void onMyLocationChange(Location location) {
        currentLoc = String.valueOf(location.getLatitude())+","+String.valueOf(location.getLongitude());
    }
}
