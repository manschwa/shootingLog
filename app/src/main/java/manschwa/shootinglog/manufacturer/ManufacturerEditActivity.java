package manschwa.shootinglog.manufacturer;

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
 * Created by root on 15.12.16.
 */

public class ManufacturerEditActivity extends AppCompatActivity implements DeleteDialogFragment.DeleteDialogListener {

    private final String INTENT_MANUFACTURER = "Manufacturer";

    private boolean intentManufacturer;

    private TextView manufacturerIdView;
    private EditText manufacturerNameBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturer_edit);

        Intent intent = getIntent();
        this.intentManufacturer = intent.hasExtra(INTENT_MANUFACTURER);

        initToolbar();
        initViews();

        // fill up the Views if there is a Discipline in the given Intent
        if (this.intentManufacturer) {
            Manufacturer manufacturer = (Manufacturer) intent.getSerializableExtra(INTENT_MANUFACTURER);
            fillViews(manufacturer);
        } else {
            manufacturerIdView.setText("New Manufacturer");
        }
    }

    private void initViews() {
        manufacturerIdView    = (TextView) findViewById(R.id.manufacturerID);
        manufacturerNameBox   = (EditText) findViewById(R.id.manufacturerName);
    }



    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            // Enables the Up-Navigation and replaces the home button with a reverse arrow
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (this.intentManufacturer) {
                actionBar.setTitle("Edit Manufacturer");
            } else {
                actionBar.setTitle("New Manufacturer");
            }
        }
    }

    private void fillViews(Manufacturer manufacturer) {
        manufacturerIdView.setText(String.valueOf(manufacturer.getID()));
        manufacturerNameBox.setText(String.valueOf(manufacturer.getName()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (this.intentManufacturer) {
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
        Manufacturer manufacturer;

        if (this.intentManufacturer) {
            manufacturer = (Manufacturer) getIntent().getSerializableExtra(INTENT_MANUFACTURER);
        } else {
            manufacturer = null;
        }

        switch (id) {
            case R.id.action_delete:
                showDeleteDialog();
                return true;
            case R.id.action_done:
                if (this.intentManufacturer) {
                    updateManufacturer(manufacturer);
                } else {
                    // TODO implement Pickers with default values.
                    createManufacturer();
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
        int id = ((Manufacturer) getIntent().getSerializableExtra(INTENT_MANUFACTURER)).getID();
        deleteManufacturer(id);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        // do nothing
    }

    public void createManufacturer () {
        ManufacturerDatabaseHelper manufacturerDatabaseHelper = new ManufacturerDatabaseHelper(this);

        String name = manufacturerNameBox.getText().toString().trim();

        Manufacturer manufacturer = new Manufacturer(name);
        manufacturerDatabaseHelper.addManufacturer(manufacturer);

        // same functionality as the back arrow button (starts the previous activity new)
        NavUtils.navigateUpFromSameTask(this);
    }

    public void updateManufacturer (Manufacturer manufacturer) {
        ManufacturerDatabaseHelper manufacturerDatabaseHelper = new ManufacturerDatabaseHelper(this);

        String name = manufacturerNameBox.getText().toString().trim();

        manufacturer.setName(name);

        manufacturerDatabaseHelper.updateManufacturer(manufacturer);

        // same functionality as the back arrow button (starts the previous activity new)
        NavUtils.navigateUpFromSameTask(this);
    }

    public void lookupManufacturer(View view) {
        ManufacturerDatabaseHelper manufacturerDatabaseHelper = new ManufacturerDatabaseHelper(this);

        Manufacturer manufacturer = manufacturerDatabaseHelper.findManufacturer(manufacturerNameBox.getText().toString().trim());

        if (manufacturer != null) {
            manufacturerIdView.setText(String.valueOf(manufacturer.getID()));
            manufacturerNameBox.setText(String.valueOf(manufacturer.getName()));
        } else {
            manufacturerIdView.setText("No matching Manufacturer was found.");
        }
    }

    private void deleteManufacturer (int id) {
        ManufacturerDatabaseHelper manufacturerDatabaseHelper = new ManufacturerDatabaseHelper(this);

        manufacturerDatabaseHelper.deleteManufacturer(id);

        // same functionality as the back arrow button (starts the previous activity new)
        NavUtils.navigateUpFromSameTask(this);
    }

}
