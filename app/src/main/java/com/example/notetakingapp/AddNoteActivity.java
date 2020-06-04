package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
//        Display the toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("New Note");

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
    }
}