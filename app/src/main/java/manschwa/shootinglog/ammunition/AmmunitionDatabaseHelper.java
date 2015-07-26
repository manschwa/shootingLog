package manschwa.shootinglog.ammunition;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;
import java.util.ArrayList;

import manschwa.shootinglog.DatabaseContract;
import manschwa.shootinglog.manufacturer.ManufacturerDatabaseHelper;

/**
 * Created by Manuel on 26.07.15.
 */
public class AmmunitionDatabaseHelper extends SQLiteOpenHelper {

    private ManufacturerDatabaseHelper manufacturerDatabaseHelper;

    public AmmunitionDatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
        this.manufacturerDatabaseHelper = new ManufacturerDatabaseHelper(context);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.Ammunition.CREATE_TABLE);
    }

    // Method is called during an upgrade of the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.Ammunition.DELETE_TABLE);
        onCreate(db);
    }

    public ContentValues setValues(Ammunition ammunition) {

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Ammunition.COLUMN_NAME_NAME, ammunition.getName());
        values.put(DatabaseContract.Ammunition.COLUMN_NAME_BATCH_NUMBER, ammunition.getBatchNumber());
        values.put(DatabaseContract.Ammunition.COLUMN_NAME_MANUFACTURER, ammunition.getManufacturer().getID());

        return values;
    }

    public void addAmmunition(Ammunition ammunition) {

        ContentValues values = setValues(ammunition);
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(DatabaseContract.Ammunition.TABLE_NAME, null, values);
        db.close();
    }

    public Ammunition findAmmunition(String ammunitionName) {
        String query = "Select * FROM " + DatabaseContract.Ammunition.TABLE_NAME +
                " WHERE " + DatabaseContract.Ammunition.COLUMN_NAME_NAME + " =  \"" + ammunitionName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Ammunition ammunition = new Ammunition();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            ammunition.setID(Integer.parseInt(cursor.getString(0)));
            ammunition.setName(cursor.getString(1));
            ammunition.setBatchNumber(Integer.parseInt(cursor.getString(2)));
            ammunition.setManufacturer(manufacturerDatabaseHelper.
                    findManufacturer(Integer.parseInt(cursor.getString(3))));
            cursor.close();
        } else {
            ammunition = null;
        }
        db.close();
        return ammunition;
    }

    public Ammunition findAmmunition(int ammunitionID) {
        String query = "Select * FROM " + DatabaseContract.Ammunition.TABLE_NAME +
                " WHERE " + DatabaseContract.Ammunition.COLUMN_NAME_AMMUNITION_ID + " =  \"" + ammunitionID + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Ammunition ammunition = new Ammunition();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            ammunition.setID(Integer.parseInt(cursor.getString(0)));
            ammunition.setName(cursor.getString(1));
            ammunition.setBatchNumber(Integer.parseInt(cursor.getString(2)));
            ammunition.setManufacturer(manufacturerDatabaseHelper.
                    findManufacturer(Integer.parseInt(cursor.getString(3))));
            cursor.close();
        } else {
            ammunition = null;
        }
        db.close();
        return ammunition;
    }

    public List<Ammunition> findAllAmmunitions() {
        String query = "Select * FROM " + DatabaseContract.Ammunition.TABLE_NAME +
                " order by " + DatabaseContract.Ammunition.COLUMN_NAME_NAME + " asc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        List<Ammunition> ammunitions = new ArrayList<>();
        int rows = cursor.getCount();

        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                Ammunition ammunition = new Ammunition();
                cursor.moveToNext();

                ammunition.setID(Integer.parseInt(cursor.getString(0)));
                ammunition.setName(cursor.getString(1));
                ammunition.setBatchNumber(Integer.parseInt(cursor.getString(2)));
                ammunition.setManufacturer(manufacturerDatabaseHelper.
                        findManufacturer(Integer.parseInt(cursor.getString(3))));

                ammunitions.add(ammunition);
            }
            cursor.close();
        } else {
            System.out.println("SQL Query without any results (findAllAmmunitions())");
        }
        db.close();
        return ammunitions;
    }

    public boolean updateAmmunition(Ammunition ammunition) {
        ContentValues values = setValues(ammunition);
        SQLiteDatabase db = this.getWritableDatabase();

        int update = db.update(DatabaseContract.Ammunition.TABLE_NAME, values,
                DatabaseContract.Ammunition.COLUMN_NAME_AMMUNITION_ID + " = ?",
                new String[]{String.valueOf(ammunition.getID())});

        return update == 1;
    }

    public boolean deleteAmmunition(int ammunitionID) {

        SQLiteDatabase db = this.getWritableDatabase();

        int delete = db.delete(DatabaseContract.Ammunition.TABLE_NAME,
                DatabaseContract.Ammunition.COLUMN_NAME_AMMUNITION_ID + " = ?",
                new String[]{String.valueOf(ammunitionID)});

        db.close();
        return delete == 1;
    }
}

