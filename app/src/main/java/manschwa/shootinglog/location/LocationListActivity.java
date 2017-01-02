package manschwa.shootinglog.location;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.List;
import java.util.ArrayList;

import manschwa.shootinglog.DrawerItemListener;
import manschwa.shootinglog.R;

/**
 * Created by root on 02.01.17.
 */
public class LocationListActivity extends AppCompatActivity implements LocationListAdapter.OnItemClickListener {

    private List<Location> locations = new ArrayList<>();
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private View content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        this.locations = getAllLocations();

        initRecyclerView();
        initFab();
        initToolbar();
        setupDrawerLayout();

        content = findViewById(R.id.location_list_coordinator_layout);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.location_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LocationListAdapter locationListAdapter = new LocationListAdapter(this.locations);
        locationListAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(locationListAdapter);
    }

    private void initFab() {
        findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationListActivity.this, LocationEditActivity.class);

                startActivity(intent);
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24px);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private List<Location> getAllLocations() {
        LocationDatabaseHelper locationDatabaseHelper = new LocationDatabaseHelper(this);
        return locationDatabaseHelper.findAllLocations();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // define the behaviour if clicked on a Drawer Layout Button
        NavigationView view = (NavigationView) findViewById(R.id.navigation_view);
        DrawerItemListener drawerItemListener = new DrawerItemListener(this, this.drawerLayout);
        view.setNavigationItemSelectedListener(drawerItemListener);
        view.getMenu().getItem(1).setChecked(true);
    }

    @Override
    public void onItemClick(View view, Location location) {
        Intent intent = new Intent(LocationListActivity.this, LocationEditActivity.class);
        intent.putExtra("Location", location);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.scale_in);
    }
}
