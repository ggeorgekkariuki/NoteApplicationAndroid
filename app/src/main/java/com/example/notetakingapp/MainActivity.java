package com.example.notetakingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;
    RecyclerView mRecyclerView;
//  Global variables for the Adapter and List of Notes
    Adapter mAdapter;
    List<Note> mNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Reference and set up toolbar
        mToolbar = findViewById(R.id.toolbarSecondActivity);
        setSupportActionBar(mToolbar);

//        A reference to the NoteDatabase class that holds methods to getNotes
        NoteDatabase database = new NoteDatabase(this);

//        The data/ A list of all the notes
        mNotes = database.getNotes();

//        Instantiate the Adapter from it's constructor
        mAdapter = new Adapter(this, mNotes);

//        References for the RecyclerView
        mRecyclerView = findViewById(R.id.listOfNotes);
//        Set a LayoutManager for the RecyclerView eg LinearLayoutMgr or GridLayoutMgr
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        Attach the Adapter to the RecyclerView using the constructor. This takes a Context and a List<Note>
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        Register the menu and inflate it
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        This method handles the clicks that happen within the Menu
        if(item.getItemId() == R.id.add_menu_item){
//            Starting the AddNoteActivity
            Intent intent = new Intent(this, AddNoteActivity.class);
            startActivity(intent);
            Toast.makeText(this, item.getTitle() + " button was clicked",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}