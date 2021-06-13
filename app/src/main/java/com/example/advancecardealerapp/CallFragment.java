package com.example.advancecardealerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CallFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CallFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CallFragment newInstance(String param1, String param2) {
        CallFragment fragment = new CallFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_call, container, false);
        Button callus= (Button)view.findViewById(R.id.ButtonCall);
        Button findus= (Button)view.findViewById(R.id.ButtonFind);
        Button email= (Button)view.findViewById(R.id.ButtonEmail);

        callus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent();
                newIntent.setAction(Intent.ACTION_DIAL);
                newIntent.setData(Uri.parse("tel:+0599000000"));
                startActivity(newIntent);
            }
        });

        findus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent();
                myIntent.setAction(Intent.ACTION_VIEW);
                myIntent.setData(Uri.parse("geo:19.076,72.8777"));
                startActivity(myIntent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gmailIntent =new Intent();
                gmailIntent.setAction(Intent.ACTION_SENDTO);
                gmailIntent.setType("message/rfc822");
                gmailIntent.setData(Uri.parse("mailto:CarDealer@cars.com"));
                gmailIntent.putExtra(Intent.EXTRA_SUBJECT,"lab android Project");
                gmailIntent.putExtra(Intent.EXTRA_TEXT,"Advance Car Dealer App");
                startActivity(gmailIntent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}