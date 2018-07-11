package com.example.luput.tnt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

public class afterLoginActiviry extends AppCompatActivity {

    DrawerLayout drawerLayout;
    boolean isCoach;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);
        isCoach = getIntent().getBooleanExtra("isCoach",false);

        Toolbar toolbar = findViewById(R.id.afterLoginToolBar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout_aftesignin);
        NavigationView navigationView = findViewById(R.id.Nav_afterlogin);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                //make switch case base on coach or trainee
                return false;
            }
        });
        if(isCoach){

        }
        else {
            TraineeFragment traineeFragment = new TraineeFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.Fragment_container_afterlogin, traineeFragment)
                    .commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
