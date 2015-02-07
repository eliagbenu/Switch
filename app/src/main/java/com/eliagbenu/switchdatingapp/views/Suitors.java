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
    boolean genderStatus;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_suitors);


        // Construct the data source
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        // Create the adapter to convert the array to views
        UserListAdapter adapter = new UserListAdapter(this, arrayOfUsers);

        addTestMaleUsers(adapter);


        ListView listView = (ListView) findViewById(R.id.userList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                acceptProposal( position );

            }
        });

/*        if(getGender())
        {

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    expressInterest( position );

                }
            });

        }else{



        }*/

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

        String url = AppController.API_URL+"rest-auth/registration/";

        User newUser1 = new User("Eli", "Accra","I do not really cuddle", "MALE",R.drawable.profile_pix);
        User newUser2 = new User("Ammish", "Takoradi","I am a poet", "MALE",R.drawable.profile_pix);
        User newUser3 = new User("Stephen", "San Diego","I like stuff all stuff", "MALE",R.drawable.profile_pix);
        User newUser4 = new User("Larry", "Accra","Data science and more", "MALE",R.drawable.profile_pix);
        User newUser5 = new User("Godwin", "Kumasi","I cuddle after work", "MALE",R.drawable.profile_pix);

        adapter.add(newUser1);
        adapter.add(newUser2);
        adapter.add(newUser3);
        adapter.add(newUser4);
        adapter.add(newUser5);

    }

    public void addTestFemaleUsers(UserListAdapter adapter){

        User newUser1 = new User("Rejoice", "I bring joy into your life","I like stuff", "FEMALE",R.drawable.profile_pix);
        User newUser2 = new User("Stella", "I make everyday interesting","I like stuff", "FEMALE",R.drawable.profile_pix);
        User newUser3 = new User("Ernestina", "Lovingfor mr.right","I like stuff", "FEMALE",R.drawable.profile_pix);
        User newUser4 = new User("Nancy", "Easy to please","I like stuff", "FEMALE",R.drawable.profile_pix);
        User newUser5 = new User("Lady O", "I make chocolates","I like Chocolates", "FEMALE",R.drawable.profile_pix);

        adapter.add(newUser1);
        adapter.add(newUser2);
        adapter.add(newUser3);
        adapter.add(newUser4);
        adapter.add(newUser5);

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

        if (id == R.id.action_ask_question) {
            askQuestionsScreen();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void profileScreen(){
        Intent profileIntent = new Intent(this,Profile.class);
        startActivity(profileIntent);
    }

    public void askQuestionsScreen(){
        Intent questionsIntent = new Intent(this,AskQuestion.class);
        startActivity(questionsIntent);
    }


}

