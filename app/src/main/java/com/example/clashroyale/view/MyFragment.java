package com.example.clashroyale.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clashroyale.R;
import com.example.clashroyale.controller.MyAdapter;
import com.example.clashroyale.controller.OnClick;
import com.example.clashroyale.model.Items;

import java.util.List;

public class MyFragment extends Fragment {
    private View myView;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MyAdapter mAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment,container,false);
        return myView;
    }
    public void setRecyclerView(MainActivity activity, List<Items> list) {
        recyclerView = this.myView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(getContext(), list);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new OnClick(getContext(), recyclerView, new OnClick.ClickListener() {
            public void onClick(View view, int position) {
                Items items = list.get(position);
                activity.otherActivity(items);
            }
            public void onLongClick(View view, int position) {
            }
        }));
    }
    public void changeStat(int value) {
        this.mAdapter.changeCategorie(value);
    }
    public void filtred(String s){
        mAdapter.getFilter().filter(s);
    }
}
