package com.example.agri;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    // Define constants for menu item IDs
    private static final int NAV_WEATHER = R.id.nav_weather;
    private static final int NAV_CROP = R.id.nav_crop;
    private static final int NAV_MARKET = R.id.nav_market;
    private static final int NAV_BULLETIN = R.id.nav_bulletin;
    private static final int NAV_LOGOUT = R.id.nav_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Drawer Layout
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // Use a different constructor for ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Enable home button for navigation drawer
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Set Navigation Item Selection Listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == NAV_WEATHER) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WeatherFragment()).commit();
                } else if (id == NAV_CROP) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CropFragment()).commit();
                } else if (id == NAV_MARKET) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MarketFragment()).commit();
                } else if (id == NAV_BULLETIN) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BulletinFragment()).commit();
                } else if (id == NAV_LOGOUT) {
                    finish();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    // Handle Drawer Toggle
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}