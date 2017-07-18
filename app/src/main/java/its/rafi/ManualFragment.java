package its.rafi;

/**
 * Created by ASUS on 6/15/2017.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import its.rafi.model.ResponseApi;
import its.rafi.util.SessionManager;
import its.rafi.util.Validation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 2/28/2017.
 */

public class ManualFragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mainView)
    RelativeLayout mainView;
    private String nama;
    private String currentLoc;
    private ProgressDialog progressDialog;
    SessionManager sessionManager;
    private Button btnTEST;
    private Button btnSelesai;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manual,container,false);
        TextView textNama = (TextView) view.findViewById(R.id.textNama);
        final EditText editMsisdn = (EditText) view.findViewById(R.id.editMsisdn);
        sessionManager = new SessionManager(getActivity());
        FirebaseCrash.log(sessionManager.getUid());
        nama = sessionManager.getLocName();
        textNama.setText(nama);
        btnSelesai = (Button) view.findViewById(R.id.btnSelesai);
        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editMsisdn.getText().toString().length() == 0){
                    Toast.makeText(getActivity(), "MSISDN Wajib diisi", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String cek =editMsisdn.getText().toString().substring(0,1);
                    Log.d("tess",cek);
                    if(cek.equals("8")){
                        progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setMessage("Please wait...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        String[] location = sessionManager.getLoc().split(",");

                        API apiService = API.client.create(API.class);
                        Call<ResponseApi> call = apiService.insertUserData(
                                nama,
                                "62"+editMsisdn.getText().toString(),
                                sessionManager.getUid(),
                                sessionManager.getUsername());
                        //proses call
                        call.enqueue(new Callback<ResponseApi>() {
                            @Override
                            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                                ResponseApi apiresponse = response.body();
                                if(apiresponse.getError().equals("false")){
                                    Toast.makeText(getActivity(),"Nomor 62"+editMsisdn.getText().toString(), Toast.LENGTH_LONG).show();
                                    Toast.makeText(getActivity(),apiresponse.getMessage(),Toast.LENGTH_SHORT).show();
                                    editMsisdn.setText("");
                                }else{
                                    Toast.makeText(getActivity(),apiresponse.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<ResponseApi> call, Throwable t) {
                                Toast.makeText(getActivity(),"Failed to Connect Internet",Toast.LENGTH_SHORT).show();
                                Log.e("cok", "onFailure: ", t.fillInStackTrace());
                                progressDialog.dismiss();
                            }
                        });

                    }else{
                        Toast.makeText(getActivity()," Format MSISDN harus 8xxxx",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return view;
    }
}
