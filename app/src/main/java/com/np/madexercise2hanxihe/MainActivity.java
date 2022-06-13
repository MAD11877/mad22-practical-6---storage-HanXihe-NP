package com.np.madexercise2hanxihe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        // Loading name and description from User from previous practicals
        Intent receiveRandInt = getIntent();
        int randInt = receiveRandInt.getIntExtra("randIntIntent", 0);
        String desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua";
        user = new User("Han Xihe", desc, 1, false); */

        TextView textViewName = findViewById(R.id.textTitle);
        TextView textViewDesc = findViewById(R.id.textDesc);

        // receives user object from UserAdapter
        User userObj = (User) getIntent().getSerializableExtra("userObj");
        String newName = userObj.getName();
        String newDesc = userObj.getDescription();
        textViewName.setText(newName);
        textViewDesc.setText(newDesc);

        // practical 6 MyDBHandler
        MyDBHandler db = new MyDBHandler(this);

        Button button = findViewById(R.id.followButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button button = findViewById(R.id.followButton);
                if (userObj.getFollowed()) {
                    userObj.setFollowed(false);
                    db.updateUser(userObj);
                    button.setText("FOLLOW");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    userObj.setFollowed(true);
                    db.updateUser(userObj);
                    button.setText("UNFOLLOW");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        Button button2 = findViewById(R.id.messgeButton);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(i);
            }
        });
    }
}