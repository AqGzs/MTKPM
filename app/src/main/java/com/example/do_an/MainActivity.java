package com.example.do_an;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.do_an.DesignPattern.FragmentFactory;
import com.example.do_an.fragment.HomeFragment;

import com.example.do_an.fragment.SettingFragment;
import com.example.do_an.fragment.TransHisFragment;
import com.example.do_an.fragment.UuDaiFragment;
import com.example.do_an.ui.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView mnBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mnBottom = findViewById(R.id.navmenu);
        mnBottom.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Load the default fragment when the activity is created
        loadFragment(FragmentFactory.getFragment(R.id.mnHome));

        mnBottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Use the FragmentFactory to get the fragment corresponding to the selected item
                Fragment fragment = FragmentFactory.getFragment(item.getItemId());
                loadFragment(fragment);
                return true;
            }
        });
    }
    void loadFragment(Fragment fmNew)
    {
        // In your activity, when replacing fragments:
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fmNew);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}