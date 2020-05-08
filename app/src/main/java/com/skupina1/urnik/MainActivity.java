package com.skupina1.urnik;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;
import com.skupina1.urnik.Fragments.AgendaFragment;
import com.skupina1.urnik.Fragments.DayFragment;
import com.skupina1.urnik.Fragments.MapFragment;
import com.skupina1.urnik.Fragments.ProfessorsFragment;
import com.skupina1.urnik.Fragments.WeekFragment;
import com.skupina1.urnik.Map.MapActivity;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    public static OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfessorsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_professor);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.map:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapFragment()).commit();
                break;
            case R.id.nav_professor:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfessorsFragment()).commit();
                break;
            case R.id.nav_agenda:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AgendaFragment()).commit();
                break;
            case R.id.nav_dan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DayFragment()).commit();
                break;
            case R.id.nav_teden:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WeekFragment()).commit();
                break;
            default:
                return false;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void dayUp(View v) {
        DayFragment frag = (DayFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        frag.dayUp(v);
    }

    public void dayDown(View v) {
        DayFragment frag = (DayFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        frag.dayDown(v);
    }

    public void weekUp(View v) {
        WeekFragment frag = (WeekFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        frag.weekUp(v);
    }

    public void weekDown(View v) {
        WeekFragment frag = (WeekFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        frag.weekDown(v);
    }
}
