package com.example.clashroyale.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.clashroyale.R;
import com.example.clashroyale.model.Items;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {
    private List<Items> everyValues,values,filtredValue;
    private Context context;
    private Filter filter;
    public Filter getFilter() {
        return this.filter;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView img;
        public View layout;
        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            img = v.findViewById(R.id.icon);
        }
    }
    public MyAdapter(Context context, List<Items> values) {
        this.context = context;
        this.everyValues = new ArrayList<>(values);
        this.values = new ArrayList<>();
        this.filtredValue = new ArrayList<>();
        this.changeCategorie(3);
        this.filter = new Filter() {
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                results.values = filtredMyValue(constraint);
                return results;
            }
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filtredValue.clear();
                filtredValue.addAll((List) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
    private List<Items> filtredMyValue(CharSequence constraint) {
        List<Items> filtredValue = new ArrayList<>();
        if(constraint == null || constraint.length() == 0) filtredValue = this.values;
        else {
            String string = constraint.toString().toLowerCase().trim();
            for(Items value : this.values)
                if(value.getName().toLowerCase().contains(string)) filtredValue.add(value);
        }
        return filtredValue;
    }
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Items clash = filtredValue.get(position);
        holder.txtHeader.setText(clash.getName());
        holder.txtFooter.setText(clash.getMaxLevel());
        Glide.with(context).load(clash.getUrl()).into(holder.img);
    }
    public int getItemCount() {
        return filtredValue.size();
    }
    public void changeCategorie(int categorie){
        int value = 0;
        switch (categorie) {
            case 0:
                value = 5;
                break;
            case 1:
                value = 8;
                break;
            case 2:
                value = 11;
                break;
            case 3:
                value = 13;
                break;
        }
        this.values.clear();
        this.filtredValue.clear();
        for(Items item : this.everyValues)
            if(Integer.parseInt(item.getMaxLevel()) == value)
                this.values.add(item);
        this.filtredValue.addAll(this.values);
        notifyDataSetChanged();
    }
}
