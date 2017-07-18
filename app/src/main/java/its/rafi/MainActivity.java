package its.rafi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.BindView;
import its.rafi.adapter.SectionsPageAdapter;
import its.rafi.model.ResponseApi;
import its.rafi.util.GlobalVariabel;
import its.rafi.util.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private String currentLoc;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(this);

        Log.d(TAG, "onCreate: Starting.");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ManualFragment(), "MANUAL");
        adapter.addFragment(new BarcodeFragment(), "BARCODE");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(getApplicationContext(), "Batal", Toast.LENGTH_LONG).show();
            }
            else {
                String nomor = result.getContents();
                if(nomor.length() >= 11){
                    String scanMsisdn = (nomor.substring(nomor.length()-11));
                    Toast.makeText(getApplicationContext(), "Nomor 62"+scanMsisdn, Toast.LENGTH_LONG).show();
                    selesai(scanMsisdn);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void selesai(String scanMsisdn){
        String[] location = sessionManager.getLoc().split(",");

        API apiService = API.client.create(API.class);
        Call<ResponseApi> call = apiService.insertUserData(
                sessionManager.getLocName(),
                "62"+scanMsisdn,
                sessionManager.getUid(),
                sessionManager.getUsername());
        //proses call
        call.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                ResponseApi apiresponse = response.body();
                if(apiresponse.getError().equals("false")){
                    Toast.makeText(getApplicationContext(),apiresponse.getMessage(),Toast.LENGTH_SHORT).show();
//                            finish();
                }else{
                    Toast.makeText(getApplicationContext(),apiresponse.getMessage(),Toast.LENGTH_SHORT).show();
                }
//                        progressDialog.dismiss();
            }


            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed to Connect Internet",Toast.LENGTH_SHORT).show();
                Log.e("cok", "onFailure: ", t.fillInStackTrace());
//                        progressDialog.dismiss();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_Checkout:
                checkingCheckout();
                getcurrentLoc();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void checkingCheckout(){
        if(GlobalVariabel.getInstance().getAllchekin()== 1){
            new AlertDialog.Builder(this)
                    .setMessage("Apakah anda ingin check out dari "+sessionManager.getLocName()+"?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            checkout();
                        }
                    })
                    .setNegativeButton("Batal", null)
                    .show();
        }else{
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
    }

    private void checkout(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String[] loc= null;
        if(currentLoc!=null){
            loc = currentLoc.split(",");
        }else{
            loc = sessionManager.getLoc().split(",");
        }
        // Log.d("wow",locID);
        API apiService = API.client.create(API.class);
        Call<ResponseApi> call = apiService.checkout(
                sessionManager.getCheckinID(),
                loc[0],loc[1]
        );
        //proses call
        call.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                ResponseApi apiresponse = response.body();
                if(apiresponse.getError().equals("false")){
                    sessionManager.setCheckinID(null);
                    sessionManager.setCheckin(GlobalVariabel.getInstance().getChekcout());
                    Intent i = new Intent(getApplicationContext(),MapsActivity.class);
                    startActivity(i);
                }
                if(this!=null){
                    Toast.makeText(getApplicationContext(),apiresponse.getMessage(),Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {
                if(getApplicationContext()!=null) {
                    Toast.makeText(getApplicationContext(), "Failed to Connect Internet", Toast.LENGTH_SHORT).show();
                }
                Log.e("cok", "onFailure: ", t.fillInStackTrace());
                if(progressDialog != null){progressDialog.dismiss();}
            }
        });

    }

    private void getcurrentLoc(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager mlocmag = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            Location loc = mlocmag.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (loc == null) {
                loc = mlocmag.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if(loc!=null){
                    currentLoc = loc.getLatitude()+","+loc.getLongitude();
                    sessionManager.setLOC(currentLoc);
                }
            }
            else{
                currentLoc = loc.getLatitude()+","+loc.getLongitude();
                sessionManager.setLOC(currentLoc);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }



}
