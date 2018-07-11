package com.example.luput.tnt;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    final String REGISTER_FRAGMENT_TAG = "register_fragment";
    final String LIST_FRAGMENT_TAG = "list_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);//setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        SigninLoginPagerAdapter adapter = new SigninLoginPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager);


    }

    private class SigninLoginPagerAdapter extends FragmentStatePagerAdapter {

        final int size = 2;

        public SigninLoginPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return LoginFragment.newInstance();
            else
                return SignupFragment.newInstance();
        }

        @Override
        public int getCount() {
            return size;
            //return Countries.values().length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            String signin = getResources().getString(R.string.sign_in);
            String signup = getResources().getString(R.string.sign_up);

            if (position == 0)
                return signin;
            else
                return signup;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
