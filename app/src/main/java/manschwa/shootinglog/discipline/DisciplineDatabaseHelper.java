package manschwa.shootinglog.discipline;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;
import java.util.ArrayList;

import manschwa.shootinglog.DatabaseContract;
import manschwa.shootinglog.discipline.Discipline;

/**
 * Created by Manuel on 04.07.15.
 */
public class DisciplineDatabaseHelper extends SQLiteOpenHelper {
    public DisciplineDatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.Discipline.CREATE_TABLE);
    }

    // Method is called during an upgrade of the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.Discipline.DELETE_TABLE);
        onCreate(db);
    }

    public ContentValues setValues(Discipline discipline) {

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Discipline.COLUMN_NAME_NAME, discipline.getName());
        values.put(DatabaseContract.Discipline.COLUMN_NAME_TOTAL_SHOTS, discipline.getTotalShots());
        values.put(DatabaseContract.Discipline.COLUMN_NAME_NUMBER_OF_PASSES, discipline.getNumberOfPasses());
        values.put(DatabaseContract.Discipline.COLUMN_NAME_TIME_IN_MINUTES, discipline.getTimeInMinutes());
        values.put(DatabaseContract.Discipline.COLUMN_NAME_DISTANCE_IN_METERS, discipline.getDistanceInMeters());
        values.put(DatabaseContract.Discipline.COLUMN_NAME_INFOS, discipline.getInfos());

        return values;
    }

    public void addDiscipline(Discipline discipline) {

        ContentValues values = setValues(discipline);
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(DatabaseContract.Discipline.TABLE_NAME, null, values);
        db.close();
    }

    public Discipline findDiscipline(String disciplineName) {
        String query = "Select * FROM " + DatabaseContract.Discipline.TABLE_NAME +
                " WHERE " + DatabaseContract.Discipline.COLUMN_NAME_NAME + " =  \"" + disciplineName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Discipline discipline = new Discipline();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            discipline.setID(Integer.parseInt(cursor.getString(0)));
            discipline.setName(cursor.getString(1));
            discipline.setTotalShots(Integer.parseInt(cursor.getString(2)));
            discipline.setNumberOfPasses(Integer.parseInt(cursor.getString(3)));
            discipline.setTimeInMinutes(Integer.parseInt(cursor.getString(4)));
            discipline.setDistanceInMeters(Integer.parseInt(cursor.getString(5)));
            discipline.setInfos(cursor.getString(6));
            cursor.close();
        } else {
            discipline = null;
        }
        db.close();
        return discipline;
    }

    public Discipline findDiscipline(int disciplineID) {
        String query = "Select * FROM " + DatabaseContract.Discipline.TABLE_NAME +
                " WHERE " + DatabaseContract.Discipline.COLUMN_NAME_DISCIPLINE_ID + " =  \"" + disciplineID + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Discipline discipline = new Discipline();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            discipline.setID(Integer.parseInt(cursor.getString(0)));
            discipline.setName(cursor.getString(1));
            discipline.setTotalShots(Integer.parseInt(cursor.getString(2)));
            discipline.setNumberOfPasses(Integer.parseInt(cursor.getString(3)));
            discipline.setTimeInMinutes(Integer.parseInt(cursor.getString(4)));
            discipline.setDistanceInMeters(Integer.parseInt(cursor.getString(5)));
            discipline.setInfos(cursor.getString(6));
            cursor.close();
        } else {
            discipline = null;
        }
        db.close();
        return discipline;
    }

    public List<Discipline> findAllDisciplines() {
        String query = "Select * FROM " + DatabaseContract.Discipline.TABLE_NAME +
                " order by " + DatabaseContract.Discipline.COLUMN_NAME_NAME + " asc";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        List<Discipline> disciplines = new ArrayList<Discipline>();
        int rows = cursor.getCount();

        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                Discipline discipline = new Discipline();
                cursor.moveToNext();

                discipline.setID(cursor.getInt(0));
                discipline.setName(cursor.getString(1));
                discipline.setTotalShots(cursor.getInt(2));
                discipline.setNumberOfPasses(cursor.getInt(3));
                discipline.setTimeInMinutes(cursor.getInt(4));
                discipline.setDistanceInMeters(cursor.getInt(5));
                discipline.setInfos(cursor.getString(6));

                disciplines.add(discipline);
            }
            cursor.close();
        } else {
            System.out.println("SQL Query without any results (findAllDisciplines)");
        }
        db.close();
        return disciplines;
    }

    public boolean deleteDiscipline(String disciplineName) {

        boolean result = false;

        String query = "Select * FROM " + DatabaseContract.Discipline.TABLE_NAME +
                " WHERE " + DatabaseContract.Discipline.COLUMN_NAME_NAME + " =  \"" + disciplineName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Discipline discipline = new Discipline();

        if (cursor.moveToFirst()) {
            discipline.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(DatabaseContract.Discipline.TABLE_NAME, DatabaseContract.Discipline.COLUMN_NAME_DISCIPLINE_ID + " = ?",
                    new String[]{String.valueOf(discipline.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateDiscipline(Discipline discipline) {
        ContentValues values = setValues(discipline);
        SQLiteDatabase db = this.getWritableDatabase();

        int update = db.update(DatabaseContract.Discipline.TABLE_NAME, values, DatabaseContract.Discipline.COLUMN_NAME_DISCIPLINE_ID + " = ?",
                new String[]{String.valueOf(discipline.getID())});

        return update == 1;

    }

    public boolean deleteDiscipline(int disciplineID) {

        SQLiteDatabase db = this.getWritableDatabase();

        int delete = db.delete(DatabaseContract.Discipline.TABLE_NAME, DatabaseContract.Discipline.COLUMN_NAME_DISCIPLINE_ID + " = ?",
                new String[]{String.valueOf(disciplineID)});

        db.close();
        return delete == 1;
    }
}