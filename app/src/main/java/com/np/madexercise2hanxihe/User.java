package com.np.madexercise2hanxihe;

import java.io.Serializable;

public class User implements Serializable { // i know its slow
    private String _name;
    private String _description;
    private int _id;
    private boolean _followed;

    public User(){ }

    public User(String name, String desc, Integer id, Boolean followed) {
        this._name = name;
        this._description = desc;
        this._id = id;
        this._followed = followed;
    }

    public User(String name, String desc, Boolean followed){
        this._name = name;
        this._description = desc;
        this._followed = followed;
    }

    public void setID(int id){
        this._id = id;
    }

    public int getID(){
        return this._id;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getName(){
        return this._name;
    }

    public void setDescription(String description){
        this._description = description;
    }

    public String getDescription(){
        return this._description;
    }

    public void setFollowed(Boolean followed){
        this._followed = followed;
    }

    public Boolean getFollowed(){
        return this._followed;
    }
}
