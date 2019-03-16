package com.example.pong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

public class RemoveScore extends AppCompatActivity {

    // Google Firebase Database Reference
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private DatabaseReference usrRef;

    // Event Listener that listens to each child in the database
    private ChildEventListener childEventListener;

    // Local data structure that will store all the values from the database
    private ArrayList<Score> ScoreList;

    // Local data structure that will store all the search results from the database
    // These results will be used to populate the ListView
    private ArrayList<Score> searchResults;

    // ContactAdapter allows the results to be displayed in a list on the app
    private ScoreAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        FirebaseApp.initializeApp(this);
        // Initializes the references to the database and contacts
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Score");

        // Initializes the local data structures
        ScoreList = new ArrayList<Score>();
        searchResults = new ArrayList<Score>();

        // Sets up the event listener that will specify what happens when access of a node
        // occurs in the database
        childEventListener = new ChildEventListener() {
            // Method is run when any new node is added to the database, and once
            // for every existing node when the activity is loaded
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Adds the Contact to the local data structure
                ScoreList.add(dataSnapshot.getValue(Score.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                ScoreList.add(dataSnapshot.getValue(Score.class));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        // Need to add the event listener to the database
        myRef.addChildEventListener(childEventListener);

        // Sets up the list view/list adapter to read from the search results array
        listAdapter = new ScoreAdapter(this, searchResults);

        // Associates the ListView to the adapter
        ListView results = findViewById(R.id.listViewResults);
        results.setAdapter(listAdapter);

        // Defines what happens when an item of the listView is clicked
        results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                Score selectedItem = (Score) parent.getItemAtPosition(position);
                myRef.child(selectedItem.getName()).removeValue();
                ScoreList.remove(selectedItem);
                refresh(selectedItem.getName());

            }
        });
    }

    // Method refreshes the ListView
    public void refresh(String update) {
        listAdapter.clear();    // clears any content
        for (Score c : ScoreList) {
            if (c.getName().equalsIgnoreCase(update)) {
                // If the contact name is a match, add the result to the listAdapter for display
                listAdapter.add(c);
            }
        }
    }

    // Upon button click of "Remove"
    public void removeScore(View view) {
        System.out.println(ScoreList);
        listAdapter.clear();    // clears any content
        boolean found = false;
        EditText text = (EditText) findViewById(R.id.editTextName);
        String search = text.getText().toString();  // Extracts name from EditText
        for (Score c : ScoreList) {
            System.out.println(c.getName());
            if (c.getName().equalsIgnoreCase(search)) {
                listAdapter.add(c);
                found = true;
            }
        }
        if (!found) {
            Toast.makeText(this, text.getText().toString() + " not found.", Toast.LENGTH_LONG).show();
        }
        text.setText("");
    }

}
