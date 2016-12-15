package manschwa.shootinglog;

import android.provider.BaseColumns;

/**
 * Created by Manuel on 04.07.15.
 */
public final class DatabaseContract {

    public static final  int    DATABASE_VERSION   = 2;
    public static final  String DATABASE_NAME      = "database.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String INTEGER_TYPE       = " INTEGER";
    private static final String REAL_TYPE          = " REAL";
    private static final String BLOB_TYPE          = " BLOB";
    private static final String COMMA_SEP          = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private DatabaseContract() {}

    public static abstract class Ammunition implements BaseColumns {
        // TODO Add a ammunition-picture.
        public static final String TABLE_NAME = "ammunition";
        public static final String COLUMN_NAME_AMMUNITION_ID = "ammunition_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_BATCH_NUMBER = "batch_number";
        public static final String COLUMN_NAME_MANUFACTURER = "manufacturer";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_NAME_AMMUNITION_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_BATCH_NUMBER + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_MANUFACTURER + INTEGER_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Discipline implements BaseColumns {
        public static final String TABLE_NAME = "discipline";
        public static final String COLUMN_NAME_DISCIPLINE_ID = "discipline_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_TOTAL_SHOTS = "total_shots";
        public static final String COLUMN_NAME_NUMBER_OF_PASSES = "number_of_passes";
        public static final String COLUMN_NAME_TIME_IN_MINUTES = "time_in_minutes";
        public static final String COLUMN_NAME_DISTANCE_IN_METERS = "distance_in_meters";
        public static final String COLUMN_NAME_INFOS = "infos";


        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_NAME_DISCIPLINE_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_TOTAL_SHOTS + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_NUMBER_OF_PASSES + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_TIME_IN_MINUTES + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_DISTANCE_IN_METERS + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_INFOS + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Event implements BaseColumns {
        public static final String TABLE_NAME = "event";
        public static final String COLUMN_NAME_EVENT_ID = "event_id";
        public static final String COLUMN_NAME_NAME = "name";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_NAME_EVENT_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME_NAME + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Location implements BaseColumns {
        public static final String TABLE_NAME = "location";
        public static final String COLUMN_NAME_LOCATION_ID = "location_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ADDRESS = "address";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_NAME_LOCATION_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_ADDRESS + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Manufacturer implements BaseColumns {
        public static final String TABLE_NAME = "manufacturer";
        public static final String COLUMN_NAME_MANUFACTURER_ID = "manufacturer_id";
        public static final String COLUMN_NAME_NAME = "name";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_NAME_MANUFACTURER_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME_NAME + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Weapon implements BaseColumns {
        // TODO Add a weapon-picture.
        public static final String TABLE_NAME = "weapon";
        public static final String COLUMN_NAME_WEAPON_ID = "weapon_id";
        public static final String COLUMN_NAME_SERIAL_NUMBER = "serial_number";
        public static final String COLUMN_NAME_MODEL = "model";
        public static final String COLUMN_NAME_MANUFACTURER = "manufacturer";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_NAME_WEAPON_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME_SERIAL_NUMBER + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_MODEL + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_MANUFACTURER + INTEGER_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}