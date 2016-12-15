package manschwa.shootinglog.event;

import android.content.Intent;
import android.os.Bundle;
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

import manschwa.shootinglog.R;
/**
 * Created by root on 15.12.16.
 */

public class EventListActivity extends AppCompatActivity implements EventListAdapter.OnItemClickListener {

    private List<Event> events = new ArrayList<Event>();
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private View content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        this.events = getAllEvents();

        initRecyclerView();
        initFab();
        initToolbar();

        content = findViewById(R.id.event_list_coordinator_layout);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.event_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EventListAdapter eventListAdapter = new EventListAdapter(this.events);
        eventListAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(eventListAdapter);
    }

    private void initFab() {
        findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventListActivity.this, EventEditActivity.class);

                startActivity(intent);
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private List<Event> getAllEvents() {
        EventDatabaseHelper eventDatabaseHelper = new EventDatabaseHelper(this);
        return eventDatabaseHelper.findAllEvents();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void onItemClick(View view, Event event) {
        Intent intent = new Intent(EventListActivity.this, EventEditActivity.class);
        intent.putExtra("Event", event);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.scale_in);
    }
}
