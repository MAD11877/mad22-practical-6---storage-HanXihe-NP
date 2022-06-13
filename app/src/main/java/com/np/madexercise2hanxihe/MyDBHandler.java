package com.np.madexercise2hanxihe;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    public MyDBHandler(Context c){
        super(c, "UserDB.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE User (Name_TEXT, Description_TEXT, Id_TEXT, Followed_INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }

    public void insertUser(User u){
        SQLiteDatabase db = this.getWritableDatabase();

        // use 1 or 0 in Followed_INTEGER
        int followedInt = 0;

        if(u.getFollowed() == false){
            followedInt = 0;
        }
        else{
            followedInt = 1;
        }
        // only executes db statement if there is an entry
        db.execSQL("INSERT INTO User VALUES(\"" + u.getName()
                + "\", \"" + u.getDescription()
                + "\", \"" + u.getID()
                + "\", \"" + followedInt
                + "\")");

            db.close();
        }

    public ArrayList<User> getUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<User> getUsersList = new ArrayList<User>();

        Cursor cursor = db.rawQuery("SELECT * FROM User", null);
        while(cursor.moveToNext()){
            User u = new User();
            String newName4 = cursor.getString(0);
            String newDesc4 = cursor.getString(1);
            String newId = cursor.getString(2); // fix int
            int newFollowed2 = cursor.getInt(3);

            u.setName(newName4);
            u.setDescription(newDesc4);
            int newIDint = Integer.parseInt(newId);
            u.setID(newIDint);

            // control structure to determine bool for followed
            if(newFollowed2 == 1){
            u.setFollowed(true);
            }
            else{
                u.setFollowed(false);
            }
             getUsersList.add(u);
        }

        cursor.close();
        return getUsersList;
    }

    public void updateUser(User u){
        // find user based on userID
        int userId = u.getID();
        SQLiteDatabase db = this.getWritableDatabase();

        if (u.getFollowed()) {
            // if user is followed, update Followed_TEXT value in database false
            db.execSQL("UPDATE User SET " +
                    "Followed_INTEGER = " +
                    0 +
                    " WHERE Id_TEXT = " +
                    userId);
        } else {
            // if user is not followed, update Followed_TEXT value in database to true
            db.execSQL("UPDATE User SET " +
                    "Followed_INTEGER = " +
                    1 +
                    " WHERE Id_TEXT = " +
                    userId);
        }
        db.close();
    }
}
