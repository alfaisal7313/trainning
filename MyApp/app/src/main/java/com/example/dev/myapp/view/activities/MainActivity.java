package com.example.dev.myapp.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.dev.myapp.R;

/**
 * Created by MacBook on 2/3/18.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        setSupportActionBar(toolbar);
    }

    private int getStatusBarHeight() {
        final int resourceId = getResources().getIdentifier("status_bar_hight", "dimen", "android");
        return resourceId > 0 ? getResources().getDimensionPixelOffset(resourceId) : 0;
    }
}
