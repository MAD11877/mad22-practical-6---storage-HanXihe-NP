package com.np.madexercise2hanxihe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Practical 6 Firebase Challenge fix
        // get instance, see teams video as ref
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-exercise-2-han-xihe-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("Users");

        EditText usernameInput = findViewById(R.id.editTextTextEmailAddress);
        EditText passwordInput = findViewById(R.id.editTextTextPassword);

        // Reading the user account data inside the database (path: Users/mad)
        ((Button)findViewById(R.id.loginButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Read input from layout and convert it into string
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                myRef.child("mad").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // checks username and password
                        if(snapshot.child("username").getValue(String.class).equals(username) &&
                                snapshot.child("password").getValue(String.class).equals(password)){
                            Intent openListActivity = new Intent(LoginActivity.this, ListActivity.class);
                            startActivity(openListActivity);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}