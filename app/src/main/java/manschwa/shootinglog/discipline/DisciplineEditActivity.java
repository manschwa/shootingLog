package manschwa.shootinglog.discipline;

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


public class DisciplineEditActivity extends AppCompatActivity implements DeleteDialogFragment.DeleteDialogListener {

    private final String INTENT_DISCIPLINE = "Discipline";

    private boolean intentDiscipline;

    private TextView disciplineIdView;
    private EditText disciplineNameBox;
    private EditText totalShotsBox;
    private EditText numberOfPassesBox;
    private EditText timeInMinutesBox;
    private EditText distanceInMetersBox;
    private EditText infosBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discipline_edit);

        Intent intent = getIntent();
        this.intentDiscipline = intent.hasExtra(INTENT_DISCIPLINE);

        initToolbar();
        initViews();

        // fill up the Views if there is a Discipline in the given Intent
        if (this.intentDiscipline) {
            Discipline discipline = (Discipline) intent.getSerializableExtra(INTENT_DISCIPLINE);
            fillViews(discipline);
        } else {
            disciplineIdView.setText("New Discipline");
        }
    }

    private void initViews() {
        disciplineIdView = (TextView) findViewById(R.id.disciplineID);
        disciplineNameBox = (EditText) findViewById(R.id.disciplineName);
        totalShotsBox = (EditText) findViewById(R.id.disciplineTotalShots);
        numberOfPassesBox = (EditText) findViewById(R.id.disciplineNumberOfPasses);
        timeInMinutesBox = (EditText) findViewById(R.id.disciplineTimeInMinutes);
        distanceInMetersBox = (EditText) findViewById(R.id.disciplineDistanceInMeters);
        infosBox = (EditText) findViewById(R.id.disciplineInfos);
    }



    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            // Enables the Up-Navigation and replaces the home button with a reverse arrow
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (this.intentDiscipline) {
                actionBar.setTitle("Edit Discipline");
            } else {
                actionBar.setTitle("New Discipline");
            }
        }
    }

    private void fillViews(Discipline discipline) {
        disciplineIdView.setText(String.valueOf(discipline.getID()));
        disciplineNameBox.setText(String.valueOf(discipline.getName()));
        totalShotsBox.setText(String.valueOf(discipline.getTotalShots()));
        numberOfPassesBox.setText(String.valueOf(discipline.getNumberOfPasses()));
        timeInMinutesBox.setText(String.valueOf(discipline.getTimeInMinutes()));
        distanceInMetersBox.setText(String.valueOf(discipline.getDistanceInMeters()));
        infosBox.setText(String.valueOf(discipline.getInfos()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (this.intentDiscipline) {
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
        Discipline discipline;

        if (this.intentDiscipline) {
            discipline = (Discipline) getIntent().getSerializableExtra(INTENT_DISCIPLINE);
        } else {
            discipline = null;
        }

        switch (id) {
            case R.id.action_delete:
                showDeleteDialog();
                return true;
            case R.id.action_done:
                if (this.intentDiscipline) {
                    updateDiscipline(discipline);
                } else {
                    // TODO implement Pickers with default values.
                    createDiscipline();
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
        int id = ((Discipline) getIntent().getSerializableExtra(INTENT_DISCIPLINE)).getID();
        deleteDiscipline(id);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        // do nothing
    }

    public void createDiscipline () {
        DisciplineDatabaseHelper disciplineDatabaseHelper = new DisciplineDatabaseHelper(this);

        String name = disciplineNameBox.getText().toString().trim();
        int totalShots = Integer.parseInt(totalShotsBox.getText().toString());
        int passes = Integer.parseInt(numberOfPassesBox.getText().toString());
        int minutes = Integer.parseInt(timeInMinutesBox.getText().toString());
        int meters = Integer.parseInt(distanceInMetersBox.getText().toString());
        String infos = infosBox.getText().toString().trim();

        Discipline discipline = new Discipline(name, totalShots, passes, minutes, meters, infos);
        disciplineDatabaseHelper.addDiscipline(discipline);

        // same functionality as the back arrow button (starts the previous activity new)
        NavUtils.navigateUpFromSameTask(this);
    }

    public void updateDiscipline (Discipline discipline) {
        DisciplineDatabaseHelper disciplineDatabaseHelper = new DisciplineDatabaseHelper(this);

        String name = disciplineNameBox.getText().toString().trim();
        int totalShots = Integer.parseInt(totalShotsBox.getText().toString());
        int passes = Integer.parseInt(numberOfPassesBox.getText().toString());
        int minutes = Integer.parseInt(timeInMinutesBox.getText().toString());
        int meters = Integer.parseInt(distanceInMetersBox.getText().toString());
        String infos = infosBox.getText().toString().trim();

        discipline.setName(name);
        discipline.setTotalShots(totalShots);
        discipline.setNumberOfPasses(passes);
        discipline.setTimeInMinutes(minutes);
        discipline.setDistanceInMeters(meters);
        discipline.setInfos(infos);

        disciplineDatabaseHelper.updateDiscipline(discipline);

        // same functionality as the back arrow button (starts the previous activity new)
        NavUtils.navigateUpFromSameTask(this);
    }

    public void lookupDiscipline(View view) {
        DisciplineDatabaseHelper disciplineDatabaseHelper = new DisciplineDatabaseHelper(this);

        Discipline discipline = disciplineDatabaseHelper.findDiscipline(disciplineNameBox.getText().toString().trim());

        if (discipline != null) {
            disciplineIdView.setText(String.valueOf(discipline.getID()));
            disciplineNameBox.setText(String.valueOf(discipline.getName()));
            totalShotsBox.setText(String.valueOf(discipline.getTotalShots()));
            numberOfPassesBox.setText(String.valueOf(discipline.getNumberOfPasses()));
            timeInMinutesBox.setText(String.valueOf(discipline.getTimeInMinutes()));
            distanceInMetersBox.setText(String.valueOf(discipline.getDistanceInMeters()));
            infosBox.setText(String.valueOf(discipline.getInfos()));

        } else {
            disciplineIdView.setText("No Match Found");
        }
    }

    private void deleteDiscipline (int id) {
        DisciplineDatabaseHelper disciplineDatabaseHelper = new DisciplineDatabaseHelper(this);

        disciplineDatabaseHelper.deleteDiscipline(id);

        // same functionality as the back arrow button (starts the previous activity new)
        NavUtils.navigateUpFromSameTask(this);
    }

}
