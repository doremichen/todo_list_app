/**
 * MainActivity: Activity for MainActivity
 * Description: Activity class for MainActivity
 * Date: 2023-05-27
 */
package com.adam.app.todoapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.med.app.todoapp.R;
import com.med.app.todoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // activity main binding
    private ActivityMainBinding mBinding;
    // nav controller
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initial view binding
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        // set action bar
        setSupportActionBar(mBinding.toolbar);

        // set nav host fragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        // null check
        if (navHostFragment != null) {
            mNavController = navHostFragment.getNavController();
            NavigationUI.setupActionBarWithNavController(this, mNavController);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        return mNavController.navigateUp() || super.onSupportNavigateUp();
    }

}