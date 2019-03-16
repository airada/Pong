package com.example.pong;




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
import com.google.firebase.FirebaseApp;
import java.util.ArrayList;
import java.util.Collections;

public class ViewScore extends AppCompatActivity {

    // Google Firebase Database Reference
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    // Event Listener that listens to each child in the database
    private ChildEventListener childEventListener;
    private ArrayList<Score> searchResults;
    private ArrayList<Score> ScoreList;
    // ArrayAdapter allows the results to be displayed in a list on the app
    private ScoreAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_view);

        // Initializes the references to the database and contacts
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Score");

        // Set up an array that will have the contents that you want to display
        ArrayList<Score> ScoreList = new ArrayList<>();
        searchResults = new ArrayList<Score>();
        // Sets up the event listener that will specify what happens when access of a node
        // occurs in the database
        childEventListener = new ChildEventListener(){
            @Override
            // Method is run when any new node is added to the database, and once
            // for every existing node when the activity is loaded
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listAdapter.add( dataSnapshot.getValue(Score.class));
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
        Collections.sort(ScoreList);
        listAdapter = new ScoreAdapter(this, ScoreList );

        ListView results = findViewById(R.id.listViewResults);
        results.setAdapter(listAdapter);
    }
    public void goHome(View view)
    {
        Intent intent = new Intent( this, FullscreenActivity.class);
        startActivity(intent);
    }
}