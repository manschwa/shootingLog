package manschwa.shootinglog.event;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;
import java.util.ArrayList;

import manschwa.shootinglog.DatabaseContract;

/**
 * Created by Manuel on 04.07.15.
 */
public class EventDatabaseHelper extends SQLiteOpenHelper {

    public EventDatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.Event.CREATE_TABLE);
    }

    // Method is called during an upgrade of the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.Event.DELETE_TABLE);
        onCreate(db);
    }

    public ContentValues setValues(Event event) {

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Event.COLUMN_NAME_NAME, event.getName());

        return values;
    }

    public void addEvent(Event event) {

        ContentValues values = setValues(event);
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(DatabaseContract.Event.TABLE_NAME, null, values);
        db.close();
    }

    public Event findEvent(String eventName) {
        String query = "Select * FROM " + DatabaseContract.Event.TABLE_NAME +
                " WHERE " + DatabaseContract.Event.COLUMN_NAME_NAME + " =  \"" + eventName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Event event = new Event();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            event.setID(Integer.parseInt(cursor.getString(0)));
            event.setName(cursor.getString(1));
            cursor.close();
        } else {
            event = null;
        }
        db.close();
        return event;
    }

    public Event findEvent(int eventID) {
        String query = "Select * FROM " + DatabaseContract.Event.TABLE_NAME +
                " WHERE " + DatabaseContract.Event.COLUMN_NAME_EVENT_ID + " =  \"" + eventID + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Event event = new Event();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            event.setID(Integer.parseInt(cursor.getString(0)));
            event.setName(cursor.getString(1));
            cursor.close();
        } else {
            event = null;
        }
        db.close();
        return event;
    }

    public List<Event> findAllEvents() {
        String query = "Select * FROM " + DatabaseContract.Event.TABLE_NAME +
                " order by " + DatabaseContract.Event.COLUMN_NAME_NAME + " asc";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        List<Event> events = new ArrayList<>();
        int rows = cursor.getCount();

        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                Event event = new Event();
                cursor.moveToNext();

                event.setID(cursor.getInt(0));
                event.setName(cursor.getString(1));

                events.add(event);
            }
            cursor.close();
        } else {
            System.out.println("SQL Query without any results (findAllEvents())");
        }
        db.close();
        return events;
    }

    public boolean updateEvent(Event event) {
        ContentValues values = setValues(event);
        SQLiteDatabase db = this.getWritableDatabase();

        int update = db.update(DatabaseContract.Event.TABLE_NAME, values,
                DatabaseContract.Event.COLUMN_NAME_EVENT_ID + " = ?",
                new String[]{String.valueOf(event.getID())});

        return update == 1;
    }

    public boolean deleteEvent(int eventID) {

        SQLiteDatabase db = this.getWritableDatabase();

        int delete = db.delete(DatabaseContract.Event.TABLE_NAME,
                DatabaseContract.Event.COLUMN_NAME_EVENT_ID + " = ?",
                new String[]{String.valueOf(eventID)});

        db.close();
        return delete == 1;
    }
}

