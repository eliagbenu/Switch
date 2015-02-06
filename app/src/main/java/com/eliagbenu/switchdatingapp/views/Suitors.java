package com.eliagbenu.switchdatingapp.views;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.eliagbenu.switchdatingapp.R;
import com.eliagbenu.switchdatingapp.adapter.UserListAdapter;
import com.eliagbenu.switchdatingapp.models.User;

import java.util.ArrayList;

public class Suitors extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suitors);


        // Construct the data source
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        // Create the adapter to convert the array to views
        UserListAdapter adapter = new UserListAdapter(this, arrayOfUsers);

        boolean gender = true;
        //based on gender of user
        if(gender){

            //adding test data- FOR TESTING ONLY
            addTestFemaleUsers(adapter);

        }else{

            //adding test data- FOR TESTING ONLY
            addTestMaleUsers(adapter);

        }

    }


    public void addTestMaleUsers(UserListAdapter adapter){

        User newUser1 = new User("Nathan", "I am in the UK");
        User newUser2 = new User("Stephen", "San Diego");
        User newUser3 = new User("Timothy", "I jump");

        adapter.add(newUser1);
        adapter.add(newUser2);
        adapter.add(newUser3);

        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.userList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();


            }
        });


    }

    public void addTestFemaleUsers(UserListAdapter adapter){

        User newUser1 = new User("Rejoice", "4");
        User newUser2 = new User("Stella", "16");
        User newUser3 = new User("Ernestina", "9");

        adapter.add(newUser1);
        adapter.add(newUser2);
        adapter.add(newUser3);

        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.userFemaleList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
