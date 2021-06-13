package com.example.advancecardealerapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link reservationsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link reservationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reservationsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public reservationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Reservations.
     */
    // TODO: Rename and change types and number of parameters
    public static reservationsFragment newInstance(String param1, String param2) {
        reservationsFragment fragment = new reservationsFragment();
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
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyData",
                Context.MODE_PRIVATE);
        final String name = sharedPreferences.getString("loggedIn","");
        System.out.println("email--->"+name);

//        SharedPrefManager sharedPrefManager =SharedPrefManager.getInstance(getActivity());
//        final String name = sharedPrefManager.readString("loggedIn","");

        CarsDataBaseHelper dataBaseHelper = new CarsDataBaseHelper(getContext().getApplicationContext(),"tst",null,1);
        ArrayList<Pair<Car,String>> reservedByMe = new ArrayList<>();

        Cursor cursor = dataBaseHelper.getReservesByEmail(name);
        System.out.println(cursor.getCount());
        while (cursor.moveToNext()){
            Pair<Car,String> stringPair = new Pair<>(Car.allCars.get(Integer.parseInt(cursor.getString(1))),cursor.getString(2));
            reservedByMe.add(stringPair);
        }
        View view = inflater.inflate(R.layout.fragment_reservations, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerRes);
        AdapterRes resAdapter = new AdapterRes(getActivity().getApplicationContext(), reservedByMe);
        recyclerView.setAdapter(resAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
        // Inflate the layout for this fragment
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}