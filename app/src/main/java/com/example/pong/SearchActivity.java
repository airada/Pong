package com.example.pong;
// Introducing Google Firebase: A Free Database for your Mobile Apps
// CSTA 2018 Kimberly Hermans
// Search Activity - Searches the database for a Score name and returns all matching results

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    // Google Firebase Database Reference
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    // Event Listener that listens to each child in the database
    private ChildEventListener childEventListener;

    // Local data structure that will store all the values from the database
    private ArrayList<Score> ScoreList;
    private ArrayList<Score> searchResults;

    // ArrayAdapter allows the results to be displayed in a list on the app
    private ScoreAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initializes the references to the database and Scores
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Score");

        // Initializes the local data structure to store the database
        ScoreList = new ArrayList<Score>();

        // Set up an array that will have the contents that you want to display
        searchResults = new ArrayList<Score>();

        // Sets up the event listener that will specify what happens when access of a node
        // occurs in the database
        childEventListener = new ChildEventListener(){
            // Method is run when any new node is added to the database, and once
            // for every existing node when the activity is loaded
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Adds the Score to the local data structure
                ScoreList.add(dataSnapshot.getValue(Score.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        // Need to add the event listener to the database
        myRef.addChildEventListener(childEventListener);

        // Sets up the list adapter to read from the results array
        listAdapter = new ScoreAdapter( this, searchResults);
        ListView results = findViewById(R.id.listViewResults);
        results.setAdapter(listAdapter);
    }

    public void search(View view)
    {
        listAdapter.clear();    // clears any content
        boolean found = false;
        for( Score c: ScoreList)
        {
            EditText text = (EditText)findViewById(R.id.editTextName);
            String search = text.getText().toString();
            if(c.getName().equalsIgnoreCase(search)) {
                // If the Score name is a match, add the result to the listAdapter for display
                listAdapter.add(c);
                found = true;
            }
        }
        EditText search = (EditText)findViewById(R.id.editTextName);
        if(!found) {
            Toast.makeText(this, search.getText().toString() + " not found.", Toast.LENGTH_LONG).show();
        }
        search.setText("");
    }

    public void goHome( View view )
    {
        Intent intent = new Intent(this, FullscreenActivity.class);
        startActivity( intent);
    }
}
