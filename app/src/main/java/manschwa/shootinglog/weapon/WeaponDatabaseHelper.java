package manschwa.shootinglog.weapon;

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
public class WeaponDatabaseHelper extends SQLiteOpenHelper {

    private ManufacturerDatabaseHelper manufacturerDatabaseHelper;

    public WeaponDatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
        this.manufacturerDatabaseHelper = new ManufacturerDatabaseHelper(context);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.Weapon.CREATE_TABLE);
    }

    // Method is called during an upgrade of the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.Weapon.DELETE_TABLE);
        onCreate(db);
    }

    public ContentValues setValues(Weapon weapon) {

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Weapon.COLUMN_NAME_SERIAL_NUMBER, weapon.getSerialNumber());
        values.put(DatabaseContract.Weapon.COLUMN_NAME_MODEL, weapon.getModel());
        values.put(DatabaseContract.Weapon.COLUMN_NAME_MANUFACTURER, weapon.getManufacturer().getID());

        return values;
    }

    public void addWeapon(Weapon weapon) {

        ContentValues values = setValues(weapon);
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(DatabaseContract.Weapon.TABLE_NAME, null, values);
        db.close();
    }

    public Weapon findWeapon(String weaponModel) {
        String query = "Select * FROM " + DatabaseContract.Weapon.TABLE_NAME +
                " WHERE " + DatabaseContract.Weapon.COLUMN_NAME_MODEL + " =  \"" + weaponModel + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Weapon weapon = new Weapon();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            weapon.setID(Integer.parseInt(cursor.getString(0)));
            weapon.setSerialNumber(Integer.parseInt(cursor.getString(1)));
            weapon.setModel(cursor.getString(2));
            weapon.setManufacturer(manufacturerDatabaseHelper.
                    findManufacturer(Integer.parseInt(cursor.getString(3))));
            cursor.close();
        } else {
            weapon = null;
        }
        db.close();
        return weapon;
    }

    public Weapon findWeapon(int weaponID) {
        String query = "Select * FROM " + DatabaseContract.Weapon.TABLE_NAME +
                " WHERE " + DatabaseContract.Weapon.COLUMN_NAME_WEAPON_ID + " =  \"" + weaponID + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Weapon weapon = new Weapon();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            weapon.setID(Integer.parseInt(cursor.getString(0)));
            weapon.setSerialNumber(Integer.parseInt(cursor.getString(1)));
            weapon.setModel(cursor.getString(2));
            weapon.setManufacturer(manufacturerDatabaseHelper.
                    findManufacturer(Integer.parseInt(cursor.getString(3))));
            cursor.close();
        } else {
            weapon = null;
        }
        db.close();
        return weapon;
    }

    public List<Weapon> findAllWeapons() {
        String query = "Select * FROM " + DatabaseContract.Weapon.TABLE_NAME +
                " order by " + DatabaseContract.Weapon.COLUMN_NAME_MODEL + " asc";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        List<Weapon> weapons = new ArrayList<>();
        int rows = cursor.getCount();

        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                Weapon weapon = new Weapon();
                cursor.moveToNext();

                weapon.setID(Integer.parseInt(cursor.getString(0)));
                weapon.setSerialNumber(Integer.parseInt(cursor.getString(1)));
                weapon.setModel(cursor.getString(2));
                weapon.setManufacturer(manufacturerDatabaseHelper.
                        findManufacturer(Integer.parseInt(cursor.getString(3))));

                weapons.add(weapon);
            }
            cursor.close();
        } else {
            System.out.println("SQL Query without any results (findAllWeapons())");
        }
        db.close();
        return weapons;
    }

    public boolean updateWeapon(Weapon weapon) {
        ContentValues values = setValues(weapon);
        SQLiteDatabase db = this.getWritableDatabase();

        int update = db.update(DatabaseContract.Weapon.TABLE_NAME, values,
                DatabaseContract.Weapon.COLUMN_NAME_WEAPON_ID + " = ?",
                new String[]{String.valueOf(weapon.getID())});

        return update == 1;
    }

    public boolean deleteWeapon(int weaponID) {

        SQLiteDatabase db = this.getWritableDatabase();

        int delete = db.delete(DatabaseContract.Weapon.TABLE_NAME,
                DatabaseContract.Weapon.COLUMN_NAME_WEAPON_ID + " = ?",
                new String[]{String.valueOf(weaponID)});

        db.close();
        return delete == 1;
    }
}

