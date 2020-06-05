package com.example.notetakingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {

    Toolbar mToolbar;
    EditText mEditTextNoteTitle, mEditTextNoteContent;
    private String mCurrentDate;
    private String mCurrentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
//        Set toolbar for second activity
        mToolbar = findViewById(R.id.toolbarSecondActivity);
//        Change text color to white
//        Display the toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("New Note");
//        Display a back button the app bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        References to the EditTexts in the layout
        mEditTextNoteTitle = findViewById(R.id.editTextNoteTitle);
        mEditTextNoteContent = findViewById(R.id.editTextNoteContent);

//        Listen to the Note Title edit text and change the toolbar to the title
        mEditTextNoteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null){
//                    Change the Toolbar title when user inserts text in note title edit text
                    getSupportActionBar().setTitle(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//        Get the time and date for the Note Display
        Calendar calendar = Calendar.getInstance();
        mCurrentDate = formatDateOrTime(calendar.get(Calendar.DAY_OF_WEEK)) + "/"
                + formatDateOrTime(calendar.get(Calendar.MONTH)+1) + "/"
                + calendar.get(Calendar.YEAR);
        mCurrentTime = formatDateOrTime(calendar.get(Calendar.HOUR)) + ":"
                + formatDateOrTime(calendar.get(Calendar.MINUTE));
    }

    public String formatDateOrTime(int time){
        if (time < 10){
            return "0" + time;
        } else{
            return String.valueOf(time);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_menu_item){
//            Create a NEW NOTE (using the constructor from Note.class)
//            Store the data from the User interface - Title, Content, Date and Time
            Note note = new Note(mEditTextNoteTitle.getText().toString(),
                    mEditTextNoteContent.getText().toString(),
                    mCurrentDate, mCurrentTime);
//            Create an instance of the NoteDatabase class and use the 'addNote' method which takes in 1 argument - a NOTE
            NoteDatabase noteDatabase = new NoteDatabase(this);
            noteDatabase.addNote(note);
//            After saving the note, go back to the MainActivity
            onBackPressed();

        } else {
//            Delete the data
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        This method goes back to the Parent activity
        super.onBackPressed();
    }
}