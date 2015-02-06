package com.eliagbenu.switchdatingapp.models;

/**
 * Created by eli on 2/5/15.
 */
public class User
{
    public String name;
    public String pitch;
    public String interest;
    public String gender;

    public User(String name, String pitch, String interest,
                String gender) {
        this.name = name;
        this.pitch = pitch;
        this.interest = interest;
        this.gender = gender;
    }

}
