package com.example.notetakingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity {

    String EXTRA_ID_FROM_DETAIL_ACTIVITY = "EXTRA_ID";
    private EditText mNoteTitle;
    private EditText mNoteContent;
    private Note mNote;
    private NoteDatabase mNoteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

//        Set up the Database
        mNoteDatabase = new NoteDatabase(this);

//        Receive intent from the DetailActivity
        Intent intent = getIntent();
        Long ID = intent.getLongExtra(EXTRA_ID_FROM_DETAIL_ACTIVITY, 0);

//        Find the appropriate note from the id
        mNote = mNoteDatabase.getNote(ID);

//        Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbarEditActivity);

        setSupportActionBar(toolbar);
//        Set the title of the note to the toolbar
        getSupportActionBar().setTitle(mNote.getTitle());
//        Set the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        The Layout References - Edit Texts
        mNoteTitle = findViewById(R.id.etNoteTitle);
        mNoteContent = findViewById(R.id.etNoteContent);
//        Set data from the note saved in the DB to the Edit texts
        mNoteTitle.setText(mNote.getTitle());
        mNoteContent.setText(mNote.getTitle());

//        Add a listener to the Note Title
        mNoteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null)
                    getSupportActionBar().setTitle(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//        Find the Current Date and time
        Calendar calendar = Calendar.getInstance();
        String updatedDate = formatDateOrTime(calendar.get(Calendar.DAY_OF_MONTH)) + "/"
                + formatDateOrTime(calendar.get(Calendar.MONTH)+1) +"/"
                + calendar.get(Calendar.YEAR);
        String updatedTime = formatDateOrTime(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
                + formatDateOrTime(calendar.get(Calendar.MINUTE));
    }

    public String formatDateOrTime(int item){
//        Format date and time if the item has a digit less than 10 eg 8 to be 08
        if (item < 10){
            return "0"+item;
        } else {
            return String.valueOf(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        Added this menu because it has the menu items - Save and Delete
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.save_menu_item){
//            Get all the items (Title and Content)saved in the EditTexts in the CURRENT NOTE
            mNote.setTitle(mNoteTitle.getText().toString());
            mNote.setContent(mNoteContent.getText().toString());
//            Use the database method updateNote and save the integer it returns
            int ID = mNoteDatabase.updateNote(mNote);
//            Use the ID in an intent targeting the DetailActivity
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("EXTRA_ID", ID);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.delete_menu_item){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}