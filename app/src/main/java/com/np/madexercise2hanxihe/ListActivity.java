package com.np.madexercise2hanxihe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Login for Practical 6 challenge





        // random user generator is also reused for SQLite database data of users
        // database handling for practical 6

        MyDBHandler db = new MyDBHandler(this);
        for(int i=1; i<21; i++){
            User user = new User();

            int randInt = ThreadLocalRandom.current().nextInt(-999999999, 999999999 + 1);
            user.setName("Name" + randInt);

            int randInt2 = ThreadLocalRandom.current().nextInt(-999999999, 999999999 + 1);
            user.setDescription("Description " + randInt2);

            Random random = new Random();
            user.setFollowed(random.nextBoolean());

            // auto-increment integer as in practical 6
            user.setID(i);

            // db insert data for practical 6 to fill up database
            db.insertUser(user);
        }
        ArrayList<User> userData = db.getUsers();

        RecyclerView rv = findViewById(R.id.recyclerView);
        UserAdapter adapter = new UserAdapter(userData);
        LinearLayoutManager layout = new LinearLayoutManager(this);

        rv.setAdapter(adapter);
        rv.setLayoutManager(layout);

        /*
        // AlertDialog with onClickListener from previous practicals

        ImageView iv = findViewById(R.id.imageView2);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab = new AlertDialog.Builder(ListActivity.this);
                ab.setTitle("Profile").setMessage("MADness");

                ab.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                ab.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent i2 = new Intent(ListActivity.this, MainActivity.class);
                        int randInt = ThreadLocalRandom.current().nextInt(-999999999, 999999999 + 1);
                        i2.putExtra("randIntIntent", randInt);
                        startActivity(i2);
                    }
                });

                ab.show();
            }
        }); */
    }
}