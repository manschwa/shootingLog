package manschwa.shootinglog.location;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import manschwa.shootinglog.DeleteDialogFragment;
import manschwa.shootinglog.R;

/**
 * Created by root on 02.01.17.
 */
public class LocationEditActivity extends AppCompatActivity implements DeleteDialogFragment.DeleteDialogListener {

    private final String INTENT_LOCATION = "Location";

    private boolean intentLocation;

    private TextView locationIdView;
    private EditText locationNameBox;
    private EditText locationAddressBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_edit);

        Intent intent = getIntent();
        this.intentLocation = intent.hasExtra(INTENT_LOCATION);

        initToolbar();
        initViews();

        // fill up the Views if there is a Discipline in the given Intent
        if (this.intentLocation) {
            Location location = (Location) intent.getSerializableExtra(INTENT_LOCATION);
            fillViews(location);
        } else {
            locationIdView.setText("New Location");
        }
    }

    private void initViews() {
        locationIdView    = (TextView) findViewById(R.id.locationID);
        locationNameBox   = (EditText) findViewById(R.id.locationName);
        locationAddressBox = (EditText) findViewById(R.id.locationAddress);
    }



    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            // Enables the Up-Navigation and replaces the home button with a reverse arrow
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (this.intentLocation) {
                actionBar.setTitle("Edit Location");
            } else {
                actionBar.setTitle("New Location");
            }
        }
    }

    private void fillViews(Location location) {
        locationIdView.setText(String.valueOf(location.getID()));
        locationNameBox.setText(String.valueOf(location.getName()));
        locationAddressBox.setText(String.valueOf(location.getAddress()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (this.intentLocation) {
            getMenuInflater().inflate(R.menu.menu_edit, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_new, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Location location;

        if (this.intentLocation) {
            location = (Location) getIntent().getSerializableExtra(INTENT_LOCATION);
        } else {
            location = null;
        }

        switch (id) {
            case R.id.action_delete:
                showDeleteDialog();
                return true;
            case R.id.action_done:
                if (this.intentLocation) {
                    updateLocation(location);
                } else {
                    // TODO implement Pickers with default values.
                    createLocation();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showDeleteDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DeleteDialogFragment();
        dialog.show(getFragmentManager(), "DeleteDialogFragment");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the DeleteDialogFragment.DeleteDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button: delete Discipline
        int id = ((Location) getIntent().getSerializableExtra(INTENT_LOCATION)).getID();
        deleteLocation(id);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        // do nothing
    }

    public void createLocation () {
        LocationDatabaseHelper locationDatabaseHelper = new LocationDatabaseHelper(this);

        String name = locationNameBox.getText().toString().trim();
        String address = locationAddressBox.getText().toString().trim();

        Location location = new Location(name, address);
        locationDatabaseHelper.addLocation(location);

        // same functionality as the back arrow button (starts the previous activity new)
        NavUtils.navigateUpFromSameTask(this);
    }

    public void updateLocation (Location location) {
        LocationDatabaseHelper locationDatabaseHelper = new LocationDatabaseHelper(this);

        String name = locationNameBox.getText().toString().trim();
        String address = locationAddressBox.getText().toString().trim();

        location.setName(name);
        location.setAddress(address);

        locationDatabaseHelper.updateLocation(location);

        // same functionality as the back arrow button (starts the previous activity new)
        NavUtils.navigateUpFromSameTask(this);
    }

    public void lookupLocation(View view) {
        LocationDatabaseHelper locationDatabaseHelper = new LocationDatabaseHelper(this);

        Location location = locationDatabaseHelper.findLocation(locationNameBox.getText().toString().trim());

        if (location != null) {
            locationIdView.setText(String.valueOf(location.getID()));
            locationNameBox.setText(String.valueOf(location.getName()));
            locationAddressBox.setText(String.valueOf(location.getAddress()));
        } else {
            locationIdView.setText("No matching Location was found.");
        }
    }

    private void deleteLocation (int id) {
        LocationDatabaseHelper locationDatabaseHelper = new LocationDatabaseHelper(this);

        locationDatabaseHelper.deleteLocation(id);

        // same functionality as the back arrow button (starts the previous activity new)
        NavUtils.navigateUpFromSameTask(this);
    }

}
