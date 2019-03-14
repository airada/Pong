package com.example.pong;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class AddScore extends AppCompatActivity {
    private DatabaseReference myRef;
    private DatabaseReference usrRef;
    private FirebaseDatabase database;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.AddScore);

        // Initializes the references to the database and contacts
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Scores");
    }
    public void addContact(View view)
    {
        EditText editName = findViewById(R.id.editTextName);
        String name = editName.getText().toString();

        if( name.length() > 0 )
        {
            String key = myRef.push().getKey(); // Generates unique random key
            Score c = new Score(name, 0, key);
            myRef.child(key).setValue(c);   // Adds new Contact to the Database
            Toast.makeText(this, c.getName() + " successfully added.", Toast.LENGTH_LONG).show();
        }

        // Resets fields
        editName.setText("");
    }
}
