package com.example.pong;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class AddScore extends AppCompatActivity {
    private DatabaseReference myRef;
    private DatabaseReference usrRef;
    private FirebaseDatabase database;

    prrivate pl
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_score);
        FirebaseApp.initializeApp(this);
        // Initializes the references to the database and contacts
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Score");
        usrRef = database.getReference("Users");
    }
    public void addScore(View view)
    {
        //myRef.child("Score").child("id").setValue("0");
        EditText editName = findViewById(R.id.editTextName);
        String name = editName.getText().toString();

        if( name.length() > 0 )
        {
            String key = myRef.push().getKey(); // Generates unique random key
            Score c = new Score(name, 1, key);

            myRef.child(key).child(name).setValue(c);   // replace key with the score value
            usrRef.child(name).child("0").setValue(c); // replace "0" with the score to string

            Toast.makeText(this, c.getName() + " successfully added.", Toast.LENGTH_LONG).show();
        }

        // Resets fields
        editName.setText("");
    }
}
