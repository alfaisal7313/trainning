package com.example.dev.myapp.view.activities;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dev.myapp.R;
import com.example.dev.myapp.view.fragment.DetailFragment;
import com.example.dev.myapp.view.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    MenuItem menuItem;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initDrawebleComp();
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        setSupportActionBar(toolbar);
    }

    private int getStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0 ? getResources().getDimensionPixelOffset(resourceId) : 0;
    }

    private void initDrawebleComp() {
        drawerLayout = findViewById(R.id.dl_main);
        final NavigationView navigationView = findViewById(R.id.nav_main);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                menuItem = item;
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawable_open, R.string.drawable_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (null != menuItem){
                    switchNavigationMenu(menuItem.getItemId());
                }
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (null != drawerView && drawerView == navigationView){
                    super.onDrawerSlide(drawerView, 0);
                } else {
                    super.onDrawerSlide(drawerView, slideOffset);
                }
            }
        };

        drawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
        showHomeFragment();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawers();
        } else {
            finish();
        }
    }

    private void switchNavigationMenu(int itemId) {
        switch (itemId){
            case R.id.menu_home:
                showHomeFragment();
                break;
            case R.id.menu_detail:
                showDetailFragment();
                break;
        }
    }

    private void showHomeFragment() {
        final String tag = HomeFragment.class.getSimpleName();

        if (getSupportFragmentManager().findFragmentByTag(tag) == null){
            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_main, homeFragment, tag);
            fragmentTransaction.commit();
        }
    }

    private void showDetailFragment() {
        final String tag = DetailFragment.class.getSimpleName();

        if (getSupportFragmentManager().findFragmentByTag(tag) == null){

            final DetailFragment detailFragment = new DetailFragment();
            final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_main, detailFragment, tag);
            fragmentTransaction.commit();
        }
    }
}
