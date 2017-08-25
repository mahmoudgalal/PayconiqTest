package com.mgalal.payconiq.payconiqtest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mgalal.payconiq.payconiqtest.R;
import com.mgalal.payconiq.payconiqtest.model.Repo;

import java.util.List;

/**
 * Created by fujitsu-lap on 25/08/2017.
 * Adapter for the Repos recycler
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {



    private List<Repo> items;
    private Context context;
    public ReposAdapter(Context context ,List<Repo> items){
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item_layout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repo item = items.get(position);
        holder.repoNameTxt.setText(item.getFullName());
        holder.repoDescTxt.setText(item.getDescription());
        Glide.with(context).load(item.getOwner().getAvatar()).placeholder(R.drawable.profile_icon)
                .fitCenter().into(holder.userImageView);
    }

    @Override
    public int getItemCount() {
        return items != null?items.size():0;
    }
    public List<Repo> getItems() {
        return items;
    }

    public void setItems(List<Repo> items) {
        this.items = items;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView repoNameTxt,repoDescTxt;
        ImageView userImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            repoNameTxt = (TextView) itemView.findViewById(R.id.repo_name);
            repoDescTxt = (TextView) itemView.findViewById(R.id.repo_desc);
            userImageView = (ImageView) itemView.findViewById(R.id.user_img);
        }
    }
}
