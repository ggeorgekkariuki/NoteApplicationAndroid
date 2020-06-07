package com.example.notetakingapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    TextView mTextViewDetails;
    String EXTRA_INTENT_FROM_ADAPTER_ID = "EXTRA_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Receive the Intent from the Adapter
        Intent intent = getIntent();
        Long id = intent.getLongExtra(EXTRA_INTENT_FROM_ADAPTER_ID, 0);

//        Obtain Instance of the Database
        NoteDatabase database = new NoteDatabase(this);
//        Create a new Note Object from the Id passed in, find the appropriate note
        Note note = database.getNote(id);

//        Change the Action Bar to have the same title as the note
        getSupportActionBar().setTitle(note.getTitle());
//        Pass in the content of note to the text view in the content_detail.xml
        mTextViewDetails = findViewById(R.id.tvDetails);
        mTextViewDetails.setText(note.getContent());
        Toast.makeText(this, "Title is " + note.getTitle(), Toast.LENGTH_SHORT).show();

//        Toast.makeText(this, "ID is " + id, Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.edit_menu_item){

        }
        return super.onOptionsItemSelected(item);
    }
}