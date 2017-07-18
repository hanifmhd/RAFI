package its.rafi;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import its.rafi.model.ResponseApi;
import its.rafi.model.User;
import its.rafi.util.LocationService;
import its.rafi.util.SessionManager;
import its.rafi.util.Validation;
import com.xwray.passwordview.PasswordView;

import java.util.Calendar;
import java.util.Date;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.editUsername) EditText editUsername;
    @BindView(R.id.editPassword) EditText editPassword;
    private String currentLoc;
    private ProgressDialog progressDialog;
    private SessionManager sessionManager;
    private String user_id, npsn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        startService(new Intent(LoginActivity.this, LocationService.class));
        sessionManager = new SessionManager(getApplicationContext());
        if(sessionManager.isLogin()){
            if (sessionManager.getCheckinID()!= null){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Intent intent = new Intent(LoginActivity.this,MapsActivity.class);
                startActivity(intent);
                finish();

            }
        }


    }

    @OnClick(R.id.btnPhoneDetect)
    public void toGetPhoneNumber(View view){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},1); // define this constant yourself
        } else {
            // you have the permission
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            //---get the phone number---
            String telNumber = tm.getLine1Number();
            if (telNumber != null){
                if (telNumber.length() == 12){
                    telNumber= telNumber.substring(telNumber.length() - 11);
                    editUsername.setText("62"+telNumber);
                    return;
                }
                else if (telNumber.length() == 11){
                    telNumber= telNumber.substring(telNumber.length() - 10);
                    editUsername.setText("62"+telNumber);
                    return;
                }
            }
        }
        Toast.makeText(this,"SIM Card tidak terdeteksi!",Toast.LENGTH_LONG).show();


    }

    @OnClick(R.id.btnLogin)
    public void toLogin(View view){
        if(Validation.checkEmpty(editUsername) && Validation.checkPassword(editPassword)){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            getcurrentLoc();
            String[] loc= null;
            if(currentLoc!=null){
                loc = currentLoc.split(",");
            }else{
                loc = sessionManager.getLoc().split(",");
            }

            API apiService = API.client.create(API.class);
            Call<ResponseApi> call = apiService.login(editUsername.getText().toString(),
                    editPassword.getText().toString(),
                    loc[0], loc[1]);

            //proses call
            call.enqueue(new Callback<ResponseApi>() {
                @Override
                public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                    ResponseApi apiresponse = response.body();
                    if(apiresponse!=null){
                        if(apiresponse.getError().equals("false")){
                            User user = (User) apiresponse.getData();
                            sessionManager.setUid(user.getUserId());
                            sessionManager.setLogin(true);
                            sessionManager.setArea(user.getArea());
                            sessionManager.setCluster(user.getCluster());
                            sessionManager.setUsername(user.getUsername());
                            sessionManager.setUserType(user.getUser_type());
                            sessionManager.setRegional(user.getRegional());
                            sessionManager.setBranch(user.getBranch());
                            sessionManager.setSubranch(user.getSub_branch());
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis(System.currentTimeMillis());
                            calendar.set(Calendar.HOUR_OF_DAY, 23);
                            calendar.set(Calendar.MINUTE, 30);
                            Long jam24 = calendar.getTime().getTime();
                            Long current = new Date().getTime();
                            sessionManager.setSessionTimeOut(jam24-current);
                            sessionManager.setLastLogin(current);
                            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                            startActivity(intent);
                            finish();

                        }else{
                            Toast.makeText(getApplicationContext(),apiresponse.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Server no response",Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.dismiss();
                }


                @Override
                public void onFailure(Call<ResponseApi> call, Throwable t) {
                    // Log error
                    String message = t.getMessage();

                    Toast.makeText(getApplicationContext(),"Failed to Connect Internet",Toast.LENGTH_SHORT).show();
                    Log.e("cok", "onFailure: ", t.fillInStackTrace());
                    progressDialog.dismiss();
                }
            });

        }
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
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apakah anda ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .setNegativeButton("Batal", null)
                .show();
    }
}
