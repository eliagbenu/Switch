package com.eliagbenu.switchdatingapp.controller;

import android.app.Application;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by eli on 2/5/15.
 */
public class AppController extends Application {

    final public static String PREF_NAME = "switch_pref";
    boolean signUpStatus;

    final static public String API_URL = "https://theswitch.herokuapp.com/api/v1/";

    @Override
    public void onCreate() {
        super.onCreate();
        RequestQueue queue = Volley.newRequestQueue(this);
    }
}
