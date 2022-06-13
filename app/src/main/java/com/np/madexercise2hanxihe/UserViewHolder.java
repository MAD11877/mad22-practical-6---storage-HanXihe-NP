package com.np.madexercise2hanxihe;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView desc;
    public UserViewHolder(View item){
        super(item);
        name = item.findViewById(R.id.username);
        desc = item.findViewById(R.id.description);

    }
}
