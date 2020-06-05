package com.example.notetakingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    /*
    Before we insert the note data into the database, create a function that can save the note and its components
    The 'note' parameter already has the Title, Content, Date and Time details of the created note.
     */
    public long addNote(Note note){
//        Add data to the SQLite Database
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        Set the values of the Note in a way that can be saved
        ContentValues contentValues = new ContentValues();
//        Create a key-value pairing for all the fields
        contentValues.put(KEY_TITLE, note.getTitle());
        contentValues.put(KEY_CONTENT, note.getContent());
        contentValues.put(KEY_DATE, note.getDate());
        contentValues.put(KEY_TIME, note.getTime());
//        If the data is inserted successfully return the id/ If an error occurs the id becomes -1
        long ID = sqLiteDatabase.insert(DATABASE_TABLE, null, contentValues);

        return ID;
    }

    public Note getNote(long id){
        /*
        This method returns a single note
         */
//        Get an instance of the Database and Read From it.
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        A cursor object is a pointer that looks at a row in a database.
//        To Read from a database we need to run "SELECT * FROM TABLE NAME WHERE ID=id"
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE,
                new String[]{KEY_ID, KEY_TITLE, KEY_CONTENT, KEY_DATE, KEY_TIME},
                KEY_ID+"?", new String[]{(String.valueOf(id))},
                null, null, null);
//        The Cursor object always starts at the position -1. Move it to the first position
        if (cursor != null)
            cursor.moveToFirst();

//        Create a new Note object (using a constructor in Note.class)
//        Pass the data we have just read from the database via the cursor.
        return new Note(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4));
    }

    public List<Note> getNotes(){
        /*
        This method returns a list of all the notes in the database
         */
//        Instantiate the SQLDatabase and read from it
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        Create a List to store all the Notes that will be produced
        List<Note> listNotes = new ArrayList<>();
//        Create a RAW SQL Query to read all items in the database
        String rawQuery = "SELECT * FROM " + DATABASE_TABLE;
//        Use a cursor to obtain all the rows
        Cursor cursor = sqLiteDatabase.rawQuery(rawQuery, null);
//        Ensure cursor is not null
        do {
//            Create a new Note with no arguments and manually feed the data into the Note object
            Note note = new Note();
//            Manually feed the note items
            note.setId(cursor.getLong(0));
            note.setTitle(cursor.getString(1));
            note.setContent(cursor.getString(2));
            note.setDate(cursor.getString(3));
            note.setTime(cursor.getString(4));

//            Feed the new note to the list
            listNotes.add(note);
        } while (cursor != null);

//        Return the list with all the Notes
        return listNotes;
    }
}
