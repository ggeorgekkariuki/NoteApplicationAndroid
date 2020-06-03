package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

    Toolbar mToolbar;
    EditText mEditTextNoteTitle, mEditTextNoteContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
//        Set toolbar for second activity
        mToolbar = findViewById(R.id.toolbarSecondActivity);
        setSupportActionBar(mToolbar);

//        References to the EditTexts in the layout
        mEditTextNoteTitle = findViewById(R.id.editTextNoteTitle);
        mEditTextNoteContent = findViewById(R.id.editTextNoteContent);
    }
}