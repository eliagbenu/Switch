package com.eliagbenu.switchdatingapp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.eliagbenu.switchdatingapp.R;
import com.eliagbenu.switchdatingapp.controller.AppController;
import com.eliagbenu.switchdatingapp.models.User;

import java.util.List;


public class Splash extends ActionBarActivity {
    AppController appController;
    boolean signedUpStatus;
    private static int SPLASH_TIME_OUT = 3000;
    Intent launchProfileActivity;
    Intent launchNextActivity;
    boolean signUpStatus;

    public boolean getSignedUpStatus(){

        SharedPreferences settings = getSharedPreferences(AppController.PREF_NAME,0);

        String username = settings.getString("username","");


        if (!username.isEmpty()){
            signUpStatus=true;
        }else{
            signUpStatus=false;
        }

        return signUpStatus;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        signedUpStatus = getSignedUpStatus();

        if(signedUpStatus){

            //user already has acct, mv to suitors page
            launchProfileActivity = new Intent(getApplicationContext(), Profile.class);
            startActivity(launchProfileActivity);
            finish();
        }else{
            //user displays splash
            setContentView(R.layout.activity_splash);

            // Splash screen timer
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    ///launchNextActivity = new Intent(getApplicationContext(), Onboarding.class);
                    launchNextActivity = new Intent(getApplicationContext(), ScreenSlideActivity.class);
                    startActivity(launchNextActivity);
                    finish();
                }
            }, SPLASH_TIME_OUT);

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
