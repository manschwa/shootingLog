package manschwa.shootinglog.location;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;
import java.util.ArrayList;

import manschwa.shootinglog.DatabaseContract;

/**
 * Created by Manuel on 26.07.15.
 */
public class LocationDatabaseHelper extends SQLiteOpenHelper {

    public LocationDatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.Location.CREATE_TABLE);
    }

    // Method is called during an upgrade of the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.Location.DELETE_TABLE);
        onCreate(db);
    }

    public ContentValues setValues(Location location) {

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Location.COLUMN_NAME_NAME, location.getName());
        values.put(DatabaseContract.Location.COLUMN_NAME_ADDRESS, location.getAddress());

        return values;
    }

    public void addLocation(Location location) {

        ContentValues values = setValues(location);
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(DatabaseContract.Location.TABLE_NAME, null, values);
        db.close();
    }

    public Location findLocation(String locationName) {
        String query = "Select * FROM " + DatabaseContract.Location.TABLE_NAME +
                " WHERE " + DatabaseContract.Location.COLUMN_NAME_NAME + " =  \"" + locationName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Location location = new Location();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            location.setID(Integer.parseInt(cursor.getString(0)));
            location.setName(cursor.getString(1));
            location.setAddress(cursor.getString(2));
            cursor.close();
        } else {
            location = null;
        }
        db.close();
        return location;
    }

    public Location findLocation(int locationID) {
        String query = "Select * FROM " + DatabaseContract.Location.TABLE_NAME +
                " WHERE " + DatabaseContract.Location.COLUMN_NAME_LOCATION_ID + " =  \"" + locationID + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Location location = new Location();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            location.setID(Integer.parseInt(cursor.getString(0)));
            location.setName(cursor.getString(1));
            location.setAddress(cursor.getString(2));
            cursor.close();
        } else {
            location = null;
        }
        db.close();
        return location;
    }

    public List<Location> findAllLocations() {
        String query = "Select * FROM " + DatabaseContract.Location.TABLE_NAME +
                " order by " + DatabaseContract.Location.COLUMN_NAME_NAME + " asc";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        List<Location> locations = new ArrayList<>();
        int rows = cursor.getCount();

        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                Location location = new Location();
                cursor.moveToNext();

                location.setID(cursor.getInt(0));
                location.setName(cursor.getString(1));
                location.setAddress(cursor.getString(2));

                locations.add(location);
            }
            cursor.close();
        } else {
            System.out.println("SQL Query without any results (findAllLocations())");
        }
        db.close();
        return locations;
    }

    public boolean updateLocation(Location location) {
        ContentValues values = setValues(location);
        SQLiteDatabase db = this.getWritableDatabase();

        int update = db.update(DatabaseContract.Location.TABLE_NAME, values,
                DatabaseContract.Location.COLUMN_NAME_LOCATION_ID + " = ?",
                new String[]{String.valueOf(location.getID())});

        return update == 1;
    }

    public boolean deleteLocation(int locationID) {

        SQLiteDatabase db = this.getWritableDatabase();

        int delete = db.delete(DatabaseContract.Location.TABLE_NAME,
                DatabaseContract.Location.COLUMN_NAME_LOCATION_ID + " = ?",
                new String[]{String.valueOf(locationID)});

        db.close();
        return delete == 1;
    }
}

