package its.rafi;

/**
 * Created by ASUS on 6/15/2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.ButterKnife;
import its.rafi.model.ResponseApi;
import its.rafi.util.SessionManager;
import its.rafi.util.Validation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 2/28/2017.
 */

public class BarcodeFragment extends Fragment {
    private static final String TAG = "Tab2Fragment";
    SessionManager sessionManager;
    private String nama;
    private TextView tvScan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barcode,container,false);
        TextView textNama = (TextView) view.findViewById(R.id.textNama);
        TextView tvScan = (TextView) view.findViewById(R.id.tv_scan);

        LinearLayout btnBarcode = (LinearLayout) view.findViewById(R.id.btnBarcode);
        sessionManager = new SessionManager(getActivity());
        FirebaseCrash.log(sessionManager.getUid());
        nama = sessionManager.getLocName();
        textNama.setText(nama);
        btnBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    scanFromFragment();
            }
        });
        return view;
    }

    public void scanFromFragment(){
        IntentIntegrator.forSupportFragment(BarcodeFragment.this);
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setPrompt("Scan QR code yang tertera pada kartu Telkomsel");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
//        integrator.setOrientationLocked(false);
//        integrator.initiateScan();
    }
}
