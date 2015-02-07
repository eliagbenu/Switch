package com.eliagbenu.switchdatingapp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eliagbenu.switchdatingapp.R;
import com.eliagbenu.switchdatingapp.controller.AppController;
import com.eliagbenu.switchdatingapp.utils.VolleySingleton;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Signup extends ActionBarActivity {
    Button buttonSignup, buttonLogin;
    EditText editTextUsername,editTextPassword,editTextEmail;
    TextView textViewError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_signup);
        signupClick();
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
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String email = editTextEmail.getText().toString();

        if(username=="" || password ==""){
            textViewError = (TextView) findViewById(R.id.textViewError);
            textViewError.setVisibility(View.VISIBLE);
        }else{

            saveDetails(username,email,password);
        }
    }


    public void saveDetails(final String username,final String email,final String password){

        SharedPreferences settings = getSharedPreferences(AppController.PREF_NAME,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("email", email);
        editor.putString("username", username);
        editor.putString("password",password);
        editor.putBoolean("signup", true);
        editor.commit();

        String url = AppController.API_URL+"rest-auth/registration/";
        // Get a RequestQueue
        JsonObject json = new JsonObject();
        json.addProperty("username", username);
        json.addProperty("password1", password);
        json.addProperty("password2", password);
        json.addProperty("email", email);

        Ion.with(Signup.this)
                .load(url)
                .setHeader("Authorization ","Token "+ AppController.API_TOKEN)
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e == null){
                            return;
                        }


                    }
                });


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
