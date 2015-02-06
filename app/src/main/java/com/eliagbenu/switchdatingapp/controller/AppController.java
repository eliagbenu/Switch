package com.eliagbenu.switchdatingapp.controller;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by eli on 2/5/15.
 */
public class AppController extends Application {

    final public static String PREF_NAME = "switch_pref";
    boolean signUpStatus;


/*    //pick saved users frm api
    public boolean getSignedUpStatus(){

        SharedPreferences settings = getSharedPreferences(PREF_NAME,0);

        String username = settings.getString("username","");


        if (!username.isEmpty()){
            signUpStatus=true;
        }else{
            signUpStatus=false;
        }

        return signUpStatus;
    }*/

/*
    public boolean getGender(){

        SharedPreferences settings = getSharedPreferences(PREF_NAME,0);

        String gender = settings.getString("gender","");

        if (!gender.isEmpty() && gender=="MALE"){
            genderStatus=true;
        }else if(!gender.isEmpty() && gender=="FEMALE"){
            genderStatus=false;
        }else{
            genderStatus=false;
        }

        return genderStatus;
    }
*/


}
