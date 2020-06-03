package com.example.notetakingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteDatabase extends SQLiteOpenHelper {
    /*
    The application will use SQLite as it's database
     */
//    Variables for the basic database set up
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NotesDB";
    private static final String DATABASE_TABLE = "NotesTable";

//    Variables for the database table columns names set up
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    public NoteDatabase(Context context) {
        /*
        The constructor that will create an instance of the class
         */
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
        This method is called every time an instance oF NoteDatabase is called.
        This creates a table if the table does not exist.
        We translate SQLite queries into Java strings
         */
        String create_query = "CREATE TABLE " + DATABASE_TABLE
                + "(" + KEY_ID + " INT PRIMARY KEY, "
                + KEY_TITLE +" TEXT, "
                + KEY_CONTENT+" TEXT, "
                + KEY_DATE + " TEXT, "
                + KEY_TIME + " TEXT"
                +")";

//        Execute the query
        sqLiteDatabase.execSQL(create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old_version, int new_version) {
        /*
        This method checks the version of the database available and creates a new database if a later version exists
         */
//        Check for current version
        if (old_version >= new_version){
//            This means that we are at the latest/ most current version
            return;
        }
//        Else, drop the table then re-create it
        String drop_query = "DROP TABLE IF EXISTS " + DATABASE_TABLE;
        sqLiteDatabase.execSQL(drop_query);
//        Create a new one
        onCreate(sqLiteDatabase);
    }
}