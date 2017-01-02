package manschwa.shootinglog.manufacturer;

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
public class ManufacturerDatabaseHelper extends SQLiteOpenHelper {

    public ManufacturerDatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.Manufacturer.CREATE_TABLE);
    }

    // Method is called during an upgrade of the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(DatabaseContract.Manufacturer.DELETE_TABLE);
            onCreate(db);
        }
    }

    public ContentValues setValues(Manufacturer manufacturer) {

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Manufacturer.COLUMN_NAME_NAME, manufacturer.getName());

        return values;
    }

    public void addManufacturer(Manufacturer manufacturer) {

        ContentValues values = setValues(manufacturer);
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(DatabaseContract.Manufacturer.TABLE_NAME, null, values);
        db.close();
    }

    public Manufacturer findManufacturer(String manufacturerName) {
        String query = "Select * FROM " + DatabaseContract.Manufacturer.TABLE_NAME +
                " WHERE " + DatabaseContract.Manufacturer.COLUMN_NAME_NAME + " =  \"" + manufacturerName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Manufacturer manufacturer = new Manufacturer();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            manufacturer.setID(Integer.parseInt(cursor.getString(0)));
            manufacturer.setName(cursor.getString(1));
            cursor.close();
        } else {
            manufacturer = null;
        }
        db.close();
        return manufacturer;
    }

    public Manufacturer findManufacturer (int manufacturerID) {
        String query = "Select * FROM " + DatabaseContract.Manufacturer.TABLE_NAME +
                " WHERE " + DatabaseContract.Manufacturer.COLUMN_NAME_MANUFACTURER_ID + " =  \"" + manufacturerID + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Manufacturer manufacturer = new Manufacturer();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            manufacturer.setID(Integer.parseInt(cursor.getString(0)));
            manufacturer.setName(cursor.getString(1));
            cursor.close();
        } else {
            manufacturer = null;
        }
        db.close();
        return manufacturer;
    }

    public List<Manufacturer> findAllManufacturers() {
        String query = "Select * FROM " + DatabaseContract.Manufacturer.TABLE_NAME +
                " order by " + DatabaseContract.Manufacturer.COLUMN_NAME_NAME + " asc";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        List<Manufacturer> manufacturers = new ArrayList<>();
        int rows = cursor.getCount();

        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                Manufacturer manufacturer = new Manufacturer();
                cursor.moveToNext();

                manufacturer.setID(cursor.getInt(0));
                manufacturer.setName(cursor.getString(1));

                manufacturers.add(manufacturer);
            }
            cursor.close();
        } else {
            System.out.println("SQL Query without any results (findAllManufacturers())");
        }
        db.close();
        return manufacturers;
    }

    public boolean updateManufacturer(Manufacturer manufacturer) {
        ContentValues values = setValues(manufacturer);
        SQLiteDatabase db = this.getWritableDatabase();

        int update = db.update(DatabaseContract.Manufacturer.TABLE_NAME, values,
                DatabaseContract.Manufacturer.COLUMN_NAME_MANUFACTURER_ID + " = ?",
                new String[]{String.valueOf(manufacturer.getID())});

        return update == 1;
    }

    public boolean deleteManufacturer(int manufacturerID) {

        SQLiteDatabase db = this.getWritableDatabase();

        int delete = db.delete(DatabaseContract.Manufacturer.TABLE_NAME,
                DatabaseContract.Manufacturer.COLUMN_NAME_MANUFACTURER_ID + " = ?",
                new String[]{String.valueOf(manufacturerID)});

        db.close();
        return delete == 1;
    }
}

