package com.example.ponderwonder;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener {

    public ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigation();
    }

    private void setupNavigation() {

        // Setup the MenuBar
        Toolbar menuBar = findViewById(R.id.menuBar);
        this.setSupportActionBar(menuBar);

//        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        // Load necessary parts
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        NavController navController = Navigation.findNavController(this, R.id.mainNavigationFragment);

        // Set the Top Level Fragments
        Set<Integer> topLevels = new HashSet<>();
        topLevels.add(R.id.journal);
        topLevels.add(R.id.today);
        topLevels.add(R.id.schedules);

        // Setup ActionBar with the custom Config
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(topLevels).setDrawerLayout(drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Setup Navigation
        NavigationView navigationView = findViewById(R.id.navigationView);

        // Handle drawer item clicks
        navigationView.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.mainNavigationFragment), drawerLayout) || super.onSupportNavigateUp();
    }

    private void displayScreen(int screenId) {
        Fragment fragment = null;

        switch (screenId) {
            case R.id.journal:
                fragment = new Journal();
                break;
            case R.id.schedules:
                fragment = new Schedules();
                break;
            case R.id.today:
                fragment = new Today();
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainNavigationFragment, fragment);
            ft.commit();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displayScreen(item.getItemId());
        //make this method blank
        return true;
    }
}
