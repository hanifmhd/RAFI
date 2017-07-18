package its.rafi.fragment;

/**
 * Created by ASUS on 6/16/2017.
 */

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import its.rafi.API;
import its.rafi.R;
import its.rafi.adapter.SummaryRecyclerViewAdapter;
import its.rafi.model.Summary;
import its.rafi.util.GlobalVariabel;
import its.rafi.util.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AocSummaryFragment extends Fragment {
    private List<Summary> dictionaryWords;
    private List<Summary> filteredList;
    private SummaryRecyclerViewAdapter mAdapter;

    @BindView(R.id.search_box)
    EditText searchBox;
    @BindView(R.id.item_list)
    RecyclerView recyclerView;
    @BindView(R.id.emptyView)
    RelativeLayout emptyView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    int mode;
    SessionManager sessionManager;


    public AocSummaryFragment() {
        // Required empty public constructor
    }

    public static AocSummaryFragment newInstance(int index) {
        AocSummaryFragment f = new AocSummaryFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("mode", index);
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt("mode", 0);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aoc_summary, container, false);
        ButterKnife.bind(this,view);
        //Toast.makeText(getActivity(),"aoc",Toast.LENGTH_SHORT).show();
        sessionManager = new SessionManager(getActivity());
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(mAdapter!=null && s!=null){
                    mAdapter.getFilter().filter(s.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mode = getShownIndex();

        loadData();
        return view;
    }

    private void loadData(){

        final Activity currentActivity = getActivity();
        progressBar.setVisibility(View.VISIBLE);

        API apiService = API.client.create(API.class);
        Call<List<Summary>> call = null;
        String report = "POSKO";
        if(mode == GlobalVariabel.getInstance().getProfileMode()){
            call = apiService.getDetail(sessionManager.getUid(),report);
        }
        //proses call
        call.enqueue(new Callback<List<Summary>>() {
            @Override
            public void onResponse(Call<List<Summary>> call, Response<List<Summary>> response) {

                List<Summary> apiresponse = response.body();
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                if(apiresponse.size() > 0){
                    searchBox.setVisibility(View.VISIBLE);
                    dictionaryWords = apiresponse;
                    filteredList = new ArrayList<Summary>();
                    filteredList.addAll(dictionaryWords);
                    assert recyclerView != null;
                    mAdapter = new SummaryRecyclerViewAdapter(mode,
                            currentActivity,
                            filteredList,
                            filteredList,
                            dictionaryWords);
                    recyclerView.setAdapter(mAdapter);
                    emptyView.setVisibility(View.GONE);
                }else{
                    emptyView.setVisibility(View.VISIBLE);
                }

            }


            @Override
            public void onFailure(Call<List<Summary>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                if(getActivity()!=null){
                    Toast.makeText(getActivity(),"Failed to Connect Internet",Toast.LENGTH_SHORT).show();
                }
                Log.e("cok", "onFailure: ", t.fillInStackTrace());

            }
        });

    }

}
