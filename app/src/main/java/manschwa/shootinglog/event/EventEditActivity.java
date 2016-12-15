package manschwa.shootinglog.event;

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

public class EventEditActivity extends AppCompatActivity implements DeleteDialogFragment.DeleteDialogListener {

    private final String INTENT_EVENT = "Event";

    private boolean intentEvent;

    private TextView eventIdView;
    private EditText eventNameBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);

        Intent intent = getIntent();
        this.intentEvent = intent.hasExtra(INTENT_EVENT);

        initToolbar();
        initViews();

        // fill up the Views if there is a Discipline in the given Intent
        if (this.intentEvent) {
            Event event = (Event) intent.getSerializableExtra(INTENT_EVENT);
            fillViews(event);
        } else {
            eventIdView.setText("New Event");
        }
    }

    private void initViews() {
        eventIdView    = (TextView) findViewById(R.id.eventID);
        eventNameBox   = (EditText) findViewById(R.id.eventName);
    }



    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            // Enables the Up-Navigation and replaces the home button with a reverse arrow
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (this.intentEvent) {
                actionBar.setTitle("Edit Event");
            } else {
                actionBar.setTitle("New Event");
            }
        }
    }

    private void fillViews(Event event) {
        eventIdView.setText(String.valueOf(event.getID()));
        eventNameBox.setText(String.valueOf(event.getName()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (this.intentEvent) {
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
        Event event;

        if (this.intentEvent) {
            event = (Event) getIntent().getSerializableExtra(INTENT_EVENT);
        } else {
            event = null;
        }

        switch (id) {
            case R.id.action_delete:
                showDeleteDialog();
                return true;
            case R.id.action_done:
                if (this.intentEvent) {
                    updateEvent(event);
                } else {
                    // TODO implement Pickers with default values.
                    createEvent();
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
        int id = ((Event) getIntent().getSerializableExtra(INTENT_EVENT)).getID();
        deleteEvent(id);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        // do nothing
    }

    public void createEvent () {
        EventDatabaseHelper eventDatabaseHelper = new EventDatabaseHelper(this);

        String name = eventNameBox.getText().toString().trim();

        Event event = new Event(name);
        eventDatabaseHelper.addEvent(event);

        // same functionality as the back arrow button (starts the previous activity new)
        NavUtils.navigateUpFromSameTask(this);
    }

    public void updateEvent (Event event) {
        EventDatabaseHelper eventDatabaseHelper = new EventDatabaseHelper(this);

        String name = eventNameBox.getText().toString().trim();

        event.setName(name);

        eventDatabaseHelper.updateEvent(event);

        // same functionality as the back arrow button (starts the previous activity new)
        NavUtils.navigateUpFromSameTask(this);
    }

    public void lookupEvent(View view) {
        EventDatabaseHelper eventDatabaseHelper = new EventDatabaseHelper(this);

        Event event = eventDatabaseHelper.findEvent(eventNameBox.getText().toString().trim());

        if (event != null) {
            eventIdView.setText(String.valueOf(event.getID()));
            eventNameBox.setText(String.valueOf(event.getName()));
        } else {
            eventIdView.setText("No matching Event was found.");
        }
    }

    private void deleteEvent (int id) {
        EventDatabaseHelper eventDatabaseHelper = new EventDatabaseHelper(this);

        eventDatabaseHelper.deleteEvent(id);

        // same functionality as the back arrow button (starts the previous activity new)
        NavUtils.navigateUpFromSameTask(this);
    }

}
