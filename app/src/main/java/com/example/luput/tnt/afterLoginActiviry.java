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
<<<<<<< HEAD
import android.widget.TextView;
=======
>>>>>>> bef046bccefc588df21098259ab5ed2029ec6ac2
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class afterLoginActiviry extends AppCompatActivity {

    DrawerLayout drawerLayout;
    boolean isCoach;
<<<<<<< HEAD
    String firstName;
=======
>>>>>>> bef046bccefc588df21098259ab5ed2029ec6ac2

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);

        isCoach = getIntent().getBooleanExtra("isCoach",false);
<<<<<<< HEAD
        firstName = getIntent().getStringExtra("Name");
=======
>>>>>>> bef046bccefc588df21098259ab5ed2029ec6ac2

        //region toolbar + nav init
        Toolbar toolbar = findViewById(R.id.afterLoginToolBar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout_aftesignin);
        NavigationView navigationView = findViewById(R.id.Nav_afterlogin);
<<<<<<< HEAD
        TextView UserNameTV = navigationView.getHeaderView(0).findViewById(R.id.User_Name_After_Login);
        UserNameTV.setText("Hello " + firstName);

=======
>>>>>>> bef046bccefc588df21098259ab5ed2029ec6ac2
        if(isCoach){
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
        }
        else{
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

        if(isCoach){
            CoachFragment coachFragment = new CoachFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.Fragment_container_afterlogin,coachFragment)
                    .commit();
        }
        else {
            //import android.support.v4.app.Fragment;
            TraineeFragment traineeFragment = new TraineeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.Fragment_container_afterlogin,traineeFragment)
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

    private void drawerChoose(int clickedID){
        switch (clickedID){
<<<<<<< HEAD
            case R.id.Coach_setting:
                SettingFragment settingFragment = new SettingFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Fragment_container_afterlogin,settingFragment)
                        .addToBackStack("this")
                        .commit();
                break;
            case R.id.Trainee_setting:
                SettingFragment settingFragment1 = new SettingFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Fragment_container_afterlogin,settingFragment1)
                        .addToBackStack("this")
                        .commit();
                break;
=======
>>>>>>> bef046bccefc588df21098259ab5ed2029ec6ac2
            case R.id.Logout_coach:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(afterLoginActiviry.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Coach_about:
                Toast.makeText(afterLoginActiviry.this, "about our fail", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ShowTrainees:
                Toast.makeText(afterLoginActiviry.this, "open trainees", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Logout_trainee:
                FirebaseAuth.getInstance().signOut();
                Intent intent1 = new Intent(afterLoginActiviry.this,MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.AboutTrainee:
                Toast.makeText(afterLoginActiviry.this, "about our fail", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
