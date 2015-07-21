package manschwa.shootinglog;

import android.provider.BaseColumns;

/**
 * Created by Manuel on 04.07.15.
 */
public final class DatabaseContract {

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "database.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String INTEGER_TYPE       = " INTEGER";
    private static final String REAL_TYPE          = " REAL";
    private static final String COMMA_SEP          = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private DatabaseContract() {}

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
}