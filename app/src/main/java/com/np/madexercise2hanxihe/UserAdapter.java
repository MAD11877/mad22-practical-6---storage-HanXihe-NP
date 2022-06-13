package com.np.madexercise2hanxihe;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    ArrayList<User> data;
    public UserAdapter(ArrayList<User> data){
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        String usernames = data.get(position).getName();
        String lastNumber = usernames.substring(usernames.length() - 1);
        if(lastNumber.equals("7")){
            return 1;
        }
        return 0;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;

        if(viewType == 1) {
            item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.seventh_list_layout, parent, false);
        }
        else if (viewType == 0){
                item = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_layout, null, false);
        }
        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = data.get(position);
        String newName2 = user.getName();
        String newDesc2 = user.getDescription();
        holder.name.setText(newName2);
        holder.desc.setText(newDesc2);

        // do alertDialog here so that alertDialogs can be used for every "recycled" view,
        // with the proper user data generated passed in to the alertDialog
        ImageView iv2 = holder.itemView.findViewById(R.id.imageView2);

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab = new AlertDialog.Builder(iv2.getContext());
                String newTitle = user.getName();
                ab.setTitle("Profile").setMessage(newTitle);
                ab.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                ab.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent openMainActivity = new Intent(iv2.getContext(), MainActivity.class);
                        // putExtra objects for MainActivity, so that user.followed can still be changed
                        // i know it's slower than parcelable, but the extra complexity is not worth
                                                                    // the performance gain and effort
                        openMainActivity.putExtra("userObj", user);
                        iv2.getContext().startActivity(openMainActivity);
                    }
                });
                ab.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

