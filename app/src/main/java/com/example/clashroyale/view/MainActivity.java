package com.example.clashroyale.view;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.clashroyale.R;
import com.example.clashroyale.controller.MainController;
import com.example.clashroyale.controller.MyAdapter;
import com.example.clashroyale.controller.OnClick;
import com.example.clashroyale.model.Items;

import java.util.List;

/**
 * Created by Vincent ETIENNE on 12/02/2019.
 */
public class MainActivity extends AppCompatActivity {
    private MainController mainController;
    private MyFragment fragment;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNavigationView();

        fragment = new MyFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment,fragment).commit();


        mainController = new MainController(this);
        mainController.start();
    }

    private void createNavigationView() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        this.navListener = item -> {
            switch (item.getItemId()) {
                case R.id.nav_commune:
                    this.fragment.changeStat(3);
                    break;
                case R.id.nav_rare:
                    this.fragment.changeStat(2);
                    break;
                case R.id.nav_epique:
                    this.fragment.changeStat(1);
                    break;
                case R.id.nav_legendaire:
                    this.fragment.changeStat(0);
                    break;
            }
            return true;
        };
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }
        public void showList(List<Items> list) {
        fragment.setRecyclerView(this,list);
    }
    public void otherActivity(Items item) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("description", item.getMaxLevel());
        intent.putExtra("name", item.getName());
        intent.putExtra("url", item.getUrl());
        startActivity(intent);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            public boolean onQueryTextChange(String s) {
                fragment.filtred(s);
                return false;
            }
        });
        return true;
    }

}
