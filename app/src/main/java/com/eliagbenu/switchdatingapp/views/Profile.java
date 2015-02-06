package com.eliagbenu.switchdatingapp.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.eliagbenu.switchdatingapp.R;
import com.eliagbenu.switchdatingapp.controller.AppController;
import com.eliagbenu.switchdatingapp.fragments.DatePickerFragment;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import java.util.Calendar;

public class Profile extends FragmentActivity {
    Button buttonDOB, buttonSave, buttonCancel;
    TextView textViewDOB,textSwitchStatus,textViewGender;
    EditText     editTextInterest ,  editTextPitch  ;
    private Switch switchGender;
    boolean genderStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textSwitchStatus = (TextView) findViewById(R.id.textSwitchStatus);
        switchGender = (Switch) findViewById(R.id.switchGender);
        textViewGender  = (TextView) findViewById(R.id.textViewGender);

        //set the switch to ON
        if(getGender()){
            switchGender.setChecked(true);
        }else{
            switchGender.setChecked(false);
        }

        //attach a listener to check for changes in state
        switchGender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    textSwitchStatus.setText("OK- you are a guy");
                    textViewGender.setText("MALE");
                }else{
                    textSwitchStatus.setText("OK- you are a girl");
                    textViewGender.setText("FEMALE");
                }

            }
        });

        //check the current state before we display the screen
        if(switchGender.isChecked()){
            textSwitchStatus.setText("OK- you are a guy");
        }
        else {
            textSwitchStatus.setText("OK- you are a girl");
        }


        findViewById(R.id.buttonDOB).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        cancelClick();
        saveClick();
    }

    public boolean getGender(){

        SharedPreferences settings = getSharedPreferences(AppController.PREF_NAME,0);

        String gender = settings.getString("gender","");

        if (!gender.isEmpty() && gender=="MALE"){
            genderStatus=true;
        }else if(!gender.isEmpty() && gender=="FEMALE"){
            genderStatus=false;
        }

        return genderStatus;
    }

    public void saveClick() {
        buttonSave = (Button) findViewById(R.id.buttonSave);


        buttonSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                saveProfile();
                Intent saveIntent = new Intent(getApplicationContext(),Suitors.class);
                startActivity(saveIntent);
            }
        });
    }

    public void cancelClick() {
        buttonCancel = (Button) findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent cancelIntent = new Intent(getApplicationContext(),Suitors.class);
                startActivity(cancelIntent);
            }
        });
    }


    public void saveProfile(){
        editTextInterest = (EditText) findViewById(R.id.editTextInterest);
        editTextPitch = (EditText) findViewById(R.id.editTextPitch);
        textViewGender = (TextView) findViewById(R.id.textViewGender);
        textViewDOB = (TextView) findViewById(R.id.textViewDOB);

        SharedPreferences settings = getSharedPreferences(AppController.PREF_NAME,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("pitch", editTextPitch.getText().toString());
        editor.putString("interest",editTextInterest.getText().toString());
        editor.putString("gender",textViewGender.getText().toString());
        editor.putString("dob", textViewDOB.getText().toString());
        editor.commit();

    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getSupportFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            String monthOfYear_string="01";
            String dayOfMonth_string="01";

            //format month and day to have leading zeros
            monthOfYear = monthOfYear+1;

            if(monthOfYear<10){
                //    monthOfYear = Integer.valueOf(String.format("%02d", monthOfYear));
                monthOfYear_string = String.format("%02d", monthOfYear);
            }else{
                monthOfYear_string = String.format("%02d", monthOfYear);
            }
            if(dayOfMonth<10){
                //    dayOfMonth = Integer.valueOf(String.format("%02d", dayOfMonth));
                dayOfMonth_string = String.format("%02d", dayOfMonth);
            }else{
                dayOfMonth_string = String.format("%02d", dayOfMonth);
            }

            String date_string_value = String.valueOf(year) + "-" + monthOfYear_string
                    + "-" + dayOfMonth_string;

            Toast.makeText(
                    Profile.this,
                    date_string_value,
                    Toast.LENGTH_LONG).show();

            textViewDOB = (TextView) findViewById(R.id.textViewDOB);
            textViewDOB.setText(date_string_value);


        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
