package com.example.luput.tnt;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class afterLoginActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    boolean isCoach;
    String firstName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);

        isCoach = getIntent().getBooleanExtra("isCoach", false);
        firstName = getIntent().getStringExtra("Name");

        //region toolbar + nav init
        Toolbar toolbar = findViewById(R.id.afterLoginToolBar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout_aftesignin);
        NavigationView navigationView = findViewById(R.id.Nav_afterlogin);
        TextView UserNameTV = navigationView.getHeaderView(0).findViewById(R.id.User_Name_After_Login);
        UserNameTV.setText("Hello " + firstName);

        if (isCoach) {
            navigationView.inflateMenu(R.menu.drawer_menucoach);
            navigationView.bringToFront();
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    drawerLayout.closeDrawers();
                    drawerChoose(item.getItemId());
                    return false;
                }
            });
        } else {
            navigationView.inflateMenu(R.menu.drawer_menutrainee);
            navigationView.bringToFront();
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    drawerLayout.closeDrawers();
                    drawerChoose(item.getItemId());
                    return false;
                }
            });
        }
        //endregion

        if (isCoach) {
            CoachFragment coachFragment = new CoachFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.Fragment_container_afterlogin, coachFragment)
                    .commit();
        } else {
            //import android.support.v4.app.Fragment;
            TraineeFragment traineeFragment = new TraineeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.Fragment_container_afterlogin, traineeFragment)
                    .commit();

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void drawerChoose(int clickedID) {
        switch (clickedID) {
            case R.id.Coach_setting:
                SettingFragment settingFragment = new SettingFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Fragment_container_afterlogin, settingFragment)
                        .addToBackStack("this")
                        .commit();
                break;
            case R.id.Trainee_setting:
                SettingFragment settingFragment1 = new SettingFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Fragment_container_afterlogin, settingFragment1)
                        .addToBackStack("this")
                        .commit();
                break;
            case R.id.Logout_coach:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(afterLoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Coach_about:
                Toast.makeText(afterLoginActivity.this, "about our fail", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ShowTrainees:
                Toast.makeText(afterLoginActivity.this, "open trainees", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Logout_trainee:
                FirebaseAuth.getInstance().signOut();
                Intent intent1 = new Intent(afterLoginActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.AboutTrainee:
                Toast.makeText(afterLoginActivity.this, "about our fail", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}