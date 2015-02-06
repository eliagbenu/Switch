package com.eliagbenu.switchdatingapp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eliagbenu.switchdatingapp.R;
import com.eliagbenu.switchdatingapp.controller.AppController;

public class Signup extends ActionBarActivity {
    Button buttonSignup, buttonLogin;
    EditText editTextUsername,editTextPassword;
    TextView textViewError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupClick();
        loginClick();
    }

    public void loginClick() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //if all is well go to profile
                Intent loginIntent = new Intent(getApplicationContext(),Login.class);
                startActivity(loginIntent);

            }
        });
    }

    public void signupClick() {
        buttonSignup = (Button) findViewById(R.id.buttonSignup);


        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //chk details
                checkSignupDetails();

                //if all is well go to profile
                Intent profileIntent = new Intent(getApplicationContext(),Profile.class);
                startActivity(profileIntent);

            }
        });
    }


    public void checkSignupDetails(){
         editTextUsername = (EditText) findViewById(R.id.editTextPassword);
         editTextPassword= (EditText) findViewById(R.id.editTextUsername);

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if(username=="" || password ==""){
            textViewError = (TextView) findViewById(R.id.textViewError);
            textViewError.setVisibility(View.VISIBLE);
        }else{

            SharedPreferences settings = getSharedPreferences(AppController.PREF_NAME,0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("username", editTextUsername.getText().toString());
            editor.putString("password",editTextPassword.getText().toString());
            editor.putBoolean("signup", true);
            editor.commit();

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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
