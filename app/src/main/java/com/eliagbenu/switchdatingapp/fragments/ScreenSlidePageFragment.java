package com.eliagbenu.switchdatingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eliagbenu.switchdatingapp.R;
import com.eliagbenu.switchdatingapp.views.Initial;
import com.eliagbenu.switchdatingapp.views.Profile;
import com.eliagbenu.switchdatingapp.views.Signup;

/**
 * Created by eli on 2/6/15.
 */
public class ScreenSlidePageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    private Button buttonGetStarted;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_slide_page, container, false);

        // Set the title view to show the page number.
        ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                getString(R.string.title_template_step, mPageNumber + 1));

        TextView textViewWalkThrou = ((TextView) rootView.findViewById(R.id.textViewWalkThrou));
        TextView text1 = ((TextView) rootView.findViewById(R.id.textView1));

        Button buttonGetStarted = ((Button) rootView.findViewById(R.id.buttonGetStarted));

        LinearLayout layout1_1 = ((LinearLayout) rootView.findViewById(R.id.layout1_1));

        switch (mPageNumber){
            case 0:
            textViewWalkThrou.setText("SIGNUP ON SWITCH");
            textViewWalkThrou.setTextColor(Color.WHITE);
            textViewWalkThrou.setVisibility(View.VISIBLE);
            buttonGetStarted.setVisibility(View.INVISIBLE);
            break;

            case 1:
            textViewWalkThrou.setText("TELL THE WORLD ABOUT YOURSELF");
            textViewWalkThrou.setTextColor(Color.WHITE);
            textViewWalkThrou.setVisibility(View.VISIBLE);
            buttonGetStarted.setVisibility(View.INVISIBLE);
            break;

            case 2:
            textViewWalkThrou.setText("EXPERIENCE SWITCH");
            textViewWalkThrou.setTextColor(Color.WHITE);
            textViewWalkThrou.setVisibility(View.VISIBLE);
            buttonGetStarted.setVisibility(View.INVISIBLE);
            break;

            case 3:
            buttonGetStarted.setVisibility(View.VISIBLE);
                buttonGetStarted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {

                        Intent signupIntent = new Intent(getActivity(), Initial.class);
                        startActivity(signupIntent);

                    }
                });
            layout1_1.setBackgroundResource(R.drawable.get_started);
            buttonGetStarted.setVisibility(View.VISIBLE);
            break;

            default:
            textViewWalkThrou.setText("Find Love");
            textViewWalkThrou.setVisibility(View.VISIBLE);
            break;
        }
        return rootView;
    }



    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}
