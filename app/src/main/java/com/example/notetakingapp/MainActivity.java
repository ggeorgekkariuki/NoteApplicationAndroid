package com.example.notetakingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Reference and set up toolbar
        mToolbar = findViewById(R.id.toolbarSecondActivity);
        setSupportActionBar(mToolbar);
//        References for the RecyclerView
        mRecyclerView = findViewById(R.id.listOfNotes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        Register the menu and inflate it
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
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