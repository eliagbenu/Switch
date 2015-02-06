package com.eliagbenu.switchdatingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.eliagbenu.switchdatingapp.R;
import com.eliagbenu.switchdatingapp.models.User;

import java.util.ArrayList;

public class UserListAdapter extends ArrayAdapter<User>
{

    public UserListAdapter(Context context, ArrayList<User> user) {
        super(context, 0, user);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        // Lookup view for data population
        TextView personName = (TextView) convertView.findViewById(R.id.textViewName);
        TextView personAccountNumber = (TextView) convertView.findViewById(R.id.textViewPitch);
        // Populate the data into the template view using the data object
        personName.setText(user.name);
        personAccountNumber.setText(""+user.pitch);
        // Return the completed view to render on screen
        return convertView;
    }


}


