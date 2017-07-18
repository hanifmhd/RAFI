package its.rafi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import its.rafi.ListItem;
import its.rafi.R;


/**
 * Created by ASUS on 6/13/2017.
 */

public class AocAdapter extends RecyclerView.Adapter<AocAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public AocAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.textViewBranch.setText(listItem.getBranch());
        holder.textViewSales.setText(listItem.getSales());
        holder.textViewTarget.setText(listItem.getTarget());


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewBranch;
        public TextView textViewSales;
        public TextView textViewTarget;


        public ViewHolder(View itemView) {
            super(itemView);
            textViewBranch = (TextView) itemView.findViewById(R.id.tv_branch);
            textViewSales = (TextView) itemView.findViewById(R.id.tv_sales);
            textViewTarget = (TextView) itemView.findViewById(R.id.tv_target);

        }
    }
}
