package its.rafi.adapter;

/**
 * Created by ASUS on 6/16/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import its.rafi.R;
import its.rafi.model.Summary;
import its.rafi.util.GlobalVariabel;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class SummaryRecyclerViewAdapter extends RecyclerView.Adapter<SummaryRecyclerViewAdapter.ViewHolder> implements Filterable {
    private List<Summary> mValues;
    private CustomFilter mFilter;
    private List<Summary> filteredList;
    private List<Summary> dictionaryWords;
    private Context context;
    private int mode;
    private String cluster,actual,target,achievement;
    AdapterView.OnItemClickListener mItemClickListener;
    public SummaryRecyclerViewAdapter(int mode, Context context, List<Summary> items,
                                      List<Summary> filteredList, List<Summary> dictionaryWords) {
        mValues = items;
        this.context = context;
        this.mode = mode;
        this.filteredList = filteredList;
        this.dictionaryWords = dictionaryWords;
        mFilter = new CustomFilter(SummaryRecyclerViewAdapter.this);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summary, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);

        if(mode == GlobalVariabel.getInstance().getProfileMode()){
            cluster = holder.mItem.getCluster();
            actual = holder.mItem.getSales();
            target = holder.mItem.getTargetSales();
        }
        achievement = String.valueOf(holder.mItem.getAchievement());


        if(holder.mItem.getAchievement()<=25){
            holder.txt_achievement.setTextColor(context.getResources().getColor(R.color.red));
        }else if(holder.mItem.getAchievement()>25 && holder.mItem.getAchievement() <=50 ){
            holder.txt_achievement.setTextColor(context.getResources().getColor(R.color.orange));
        }else if(holder.mItem.getAchievement()>50 && holder.mItem.getAchievement() <=75 ){
            holder.txt_achievement.setTextColor(context. getResources().getColor(R.color.blue));
        }else if(holder.mItem.getAchievement()>75 ){
            holder.txt_achievement.setTextColor(context.getResources().getColor(R.color.green));
        }

//        if(holder.mItem.getUser_name() !=null){
//            holder.mContentView.setText(holder.mItem.getUser_name());
//        }else if(holder.mItem.getArea() !=null){
//            holder.mContentView.setText(holder.mItem.getArea());
//        }else if(holder.mItem.getCity() !=null){
//            holder.mContentView.setText(holder.mItem.getCity());
//        }else if(holder.mItem.getCluster() !=null){
//            holder.mContentView.setText(holder.mItem.getCluster());
//        }else if(holder.mItem.getRegional() !=null){
//            holder.mContentView.setText(holder.mItem.getRegional());
//        }else if(holder.mItem.getBranch() !=null){
//            holder.mContentView.setText(holder.mItem.getBranch());
//        }
        NumberFormat rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);
        holder.txt_cluster.setText(cluster);
        holder.txt_actual.setText(rupiahFormat.format(Double.parseDouble(actual)));
        holder.txt_target.setText(rupiahFormat.format(Double.parseDouble(target)));
        holder.txt_achievement.setText(achievement+"%");

//        holder.mainView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(holder.mItem.getUser_name() !=null){
//                    Intent intent = new Intent(context,KunjunganListActivity.class);
//                    intent.putExtra("aoc_id",holder.mItem.getUser_id());
//                    intent.putExtra("mode",mode);
//                    context.startActivity(intent);
//                }
//            }
//        });

    }
    @Override
    public int getItemCount() {
        return mValues.size();
    }
    @Override
    public Filter getFilter() {
        return mFilter;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        public TextView txt_actual,txt_cluster;
        public TextView txt_target,txt_achievement;
        public final TextView mContentView;
        public Summary mItem;
        public RelativeLayout mainView;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            txt_cluster = (TextView) view.findViewById(R.id.txt_cluster);
            txt_target = (TextView) view.findViewById(R.id.txt_target);
            txt_achievement = (TextView) view.findViewById(R.id.txt_achievement);
            txt_actual = (TextView) view.findViewById(R.id.txt_actual);
            mContentView = (TextView) view.findViewById(R.id.txt_nama_aoc);
            mainView = (RelativeLayout) view.findViewById(R.id.mainView);
        }
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }



    }

    public class CustomFilter extends Filter {
        private SummaryRecyclerViewAdapter mAdapter;
        private CustomFilter(SummaryRecyclerViewAdapter mAdapter) {
            super();
            this.mAdapter = mAdapter;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                filteredList.addAll(dictionaryWords);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Summary mWords : dictionaryWords) {
                    filteredList.add(mWords);
                }
            }
            System.out.println("Count Number " + filteredList.size());
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            System.out.println("Count Number 2 " + ((List<Summary>) results.values).size());
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
