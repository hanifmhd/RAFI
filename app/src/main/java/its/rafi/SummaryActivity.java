package its.rafi;

/**
 * Created by ASUS on 6/16/2017.
 */

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import its.rafi.adapter.SummaryRecyclerViewAdapter;
import its.rafi.fragment.AocSummaryFragment;
import its.rafi.model.Summary;
import its.rafi.util.GlobalVariabel;
import its.rafi.util.SessionManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SummaryActivity extends AppCompatActivity {
    private List<Summary> dictionaryWords;
    private List<Summary> filteredList;
    private SummaryRecyclerViewAdapter mAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private int mode;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tl_summary);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mode = getIntent().getIntExtra("mode",3);
        if(mode == GlobalVariabel.getInstance().getProfileMode()){
            getSupportActionBar().setTitle("DETAIL ACHIEVEMENT");
        }
        AocSummaryFragment fragment = new AocSummaryFragment();
        if (savedInstanceState == null) {
            fragment.setArguments(getIntent().getExtras());
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame, fragment)
                    .commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
