package com.eliagbenu.switchdatingapp.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.eliagbenu.switchdatingapp.R;
import com.eliagbenu.switchdatingapp.adapter.UserListAdapter;
import com.eliagbenu.switchdatingapp.controller.AppController;
import com.eliagbenu.switchdatingapp.models.User;

import java.util.ArrayList;

public class Suitors extends ActionBarActivity {
    AppController appController;
    boolean genderStatus;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suitors);


        // Construct the data source
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        // Create the adapter to convert the array to views
        UserListAdapter adapter = new UserListAdapter(this, arrayOfUsers);

        //based on gender of user
        if(getGender()){

            //adding test data- FOR TESTING ONLY
            addTestFemaleUsers(adapter);

        }else{

            //adding test data- FOR TESTING ONLY
            addTestMaleUsers(adapter);

        }



        ListView listView = (ListView) findViewById(R.id.userList);
        listView.setAdapter(adapter);

        if(getGender()){

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Toast.makeText(getApplicationContext(),
                            "Express interest " + position, Toast.LENGTH_LONG)
                            .show();

                    expressInterest( position );

                }
            });

        }else{

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Toast.makeText(getApplicationContext(),
                            "Express interest " + position, Toast.LENGTH_LONG)
                            .show();

                    acceptProposal( position );

                }
            });

        }

    }


    public void acceptProposal(Integer position){
        // get xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.proposal_prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Yea...I like him",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text

                                //send lady interest proposal

                            }
                        })
                .setNegativeButton("Like seriously..",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public boolean getGender(){

        SharedPreferences settings = getSharedPreferences(AppController.PREF_NAME,0);

        gender = settings.getString("gender","");

        if (gender.equalsIgnoreCase("MALE") ){
            genderStatus = true;
        }else if(gender.equalsIgnoreCase("FEMALE") ){
            genderStatus = false;
        }else{
            genderStatus = false;
        }

        Log.e("xxxxxxxxxx",""+genderStatus+gender);

        return genderStatus;
    }

    public void expressInterest(Integer position){
        // get xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.interest_prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Oh yea",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text

                                //send lady interest proposal

                            }
                        })
                .setNegativeButton("Nope",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void addTestMaleUsers(UserListAdapter adapter){

        User newUser1 = new User("Nathan", "I am in the UK");
        User newUser2 = new User("Stephen", "San Diego");
        User newUser3 = new User("Timothy", "I jump");

        adapter.add(newUser1);
        adapter.add(newUser2);
        adapter.add(newUser3);

    }

    public void addTestFemaleUsers(UserListAdapter adapter){

        User newUser1 = new User("Rejoice", "4");
        User newUser2 = new User("Stella", "16");
        User newUser3 = new User("Ernestina", "9");

        adapter.add(newUser1);
        adapter.add(newUser2);
        adapter.add(newUser3);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_suitors, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            profileScreen();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void profileScreen(){
        Intent profileIntent = new Intent(this,Profile.class);
        startActivity(profileIntent);
    }
}

