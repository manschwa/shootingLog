package manschwa.shootinglog.discipline;

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

import manschwa.shootinglog.R;


public class DisciplineNewActivity extends AppCompatActivity {

    TextView disciplineIdView;
    EditText disciplineNameBox;
    EditText totalShotsBox;
    EditText numberOfPassesBox;
    EditText timeInMinutesBox;
    EditText distanceInMetersBox;
    EditText infosBox;

    private View content;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discipline_new);

        initToolbar();

        disciplineIdView = (TextView) findViewById(R.id.disciplineID);
        disciplineNameBox = (EditText) findViewById(R.id.disciplineName);
        totalShotsBox = (EditText) findViewById(R.id.disciplineTotalShots);
        numberOfPassesBox = (EditText) findViewById(R.id.disciplineNumberOfPasses);
        timeInMinutesBox = (EditText) findViewById(R.id.disciplineTimeInMinutes);
        distanceInMetersBox = (EditText) findViewById(R.id.disciplineDistanceInMeters);
        infosBox = (EditText) findViewById(R.id.disciplineInfos);

        Intent intent = getIntent();

        if (intent.hasExtra("Discipline")) {

            Discipline discipline = (Discipline) intent.getSerializableExtra("Discipline");
            System.out.println("DisciplineID: " + discipline.getID());

            disciplineIdView.setText(String.valueOf(discipline.getID()));
            disciplineNameBox.setText(String.valueOf(discipline.getName()));
            totalShotsBox.setText(String.valueOf(discipline.getTotalShots()));
            numberOfPassesBox.setText(String.valueOf(discipline.getNumberOfPasses()));
            timeInMinutesBox.setText(String.valueOf(discipline.getTimeInMinutes()));
            distanceInMetersBox.setText(String.valueOf(discipline.getDistanceInMeters()));
            infosBox.setText(String.valueOf(discipline.getInfos()));

        } else {
            disciplineIdView.setText("New Discipline");
        }
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black);

            // Enables the Up-Navigation and replaces the home button with a reverse arrow
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_my, menu);

        content = findViewById(R.id.discipline_new_frame_layout);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void newDiscipline (View view) {
        DisciplineDatabaseHelper disciplineDatabaseHelper = new DisciplineDatabaseHelper(this);

        String name = disciplineNameBox.getText().toString().trim();
        int totalShots = Integer.parseInt(totalShotsBox.getText().toString());
        int passes = Integer.parseInt(numberOfPassesBox.getText().toString());
        int minutes = Integer.parseInt(timeInMinutesBox.getText().toString());
        int meters = Integer.parseInt(distanceInMetersBox.getText().toString());
        String infos = infosBox.getText().toString().trim();

        Discipline discipline = new Discipline(name, totalShots, passes, minutes, meters, infos);

        disciplineDatabaseHelper.addDiscipline(discipline);
        disciplineNameBox.setText("");
        totalShotsBox.setText("");
        numberOfPassesBox.setText("");
        timeInMinutesBox.setText("");
        distanceInMetersBox.setText("");
        infosBox.setText("");

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

    public void removeDiscipline (View view) {
        DisciplineDatabaseHelper disciplineDatabaseHelper = new DisciplineDatabaseHelper(this);

        boolean result = disciplineDatabaseHelper.deleteDiscipline(Integer.parseInt(disciplineIdView.getText().toString().trim()));

        if (result)
        {
            disciplineIdView.setText("Record Deleted");
            disciplineNameBox.setText("");
            totalShotsBox.setText("");
            numberOfPassesBox.setText("");
            timeInMinutesBox.setText("");
            distanceInMetersBox.setText("");
            infosBox.setText("");
        }
        else
            disciplineIdView.setText("Record not deleted.");
    }
}
