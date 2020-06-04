package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

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
}