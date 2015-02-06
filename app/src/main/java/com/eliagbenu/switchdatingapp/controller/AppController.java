package com.eliagbenu.switchdatingapp.controller;

import android.app.Application;

/**
 * Created by eli on 2/5/15.
 */
public class AppController extends Application {


    //pick saved users frm api
    public boolean getSignedUpStatus(){
        boolean signUpStatus=true;
        return signUpStatus;
    }


}
