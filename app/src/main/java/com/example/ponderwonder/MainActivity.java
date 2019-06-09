package com.example.ponderwonder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigation();
    }

    private void setupNavigation() {
        // TODO: Init the NavBar here
        Toolbar menuBar = findViewById(R.id.menuBar);
        this.setSupportActionBar(menuBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        NavController navController = Navigation.findNavController(this, R.id.mainNavigationFragment);

        Set<Integer> topLevels = new HashSet<Integer>();
        topLevels.add(R.id.journal);
        topLevels.add(R.id.today);
        topLevels.add(R.id.schedules);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(topLevels).setDrawerLayout(drawerLayout).build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationView navigationView = findViewById(R.id.navigationView);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.mainNavigationFragment), drawerLayout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        NavController navController = Navigation.findNavController(this, R.id.mainNavigationFragment);
        return NavigationUI.onNavDestinationSelected(menuItem, navController) || super.onOptionsItemSelected(menuItem);
    }

}
