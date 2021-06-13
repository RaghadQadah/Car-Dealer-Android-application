package com.example.advancecardealerapp;
import  android.database.Cursor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

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
 * {@link ViewAllReservesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewAllReservesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewAllReservesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ViewAllReservesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminViewReservations.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewAllReservesFragment newInstance(String param1, String param2) {
        ViewAllReservesFragment fragment = new ViewAllReservesFragment();
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
        View view = inflater.inflate(R.layout.fragment_view_all_reserves, container, false);
        CarsDataBaseHelper dataBaseHelper = new CarsDataBaseHelper(getContext().getApplicationContext(),"tst",null,1);
        CarsDataBaseHelper dataBaseHelper1 = new CarsDataBaseHelper(getContext().getApplicationContext(),"tst",null,1);

        ArrayList<String> reserveInfos = new ArrayList<>();
        Cursor cursor = dataBaseHelper.getAllReserves();
       // System.out.println(cursor.getCount());
        while (cursor.moveToNext()){

            Cursor cursor1 = dataBaseHelper.getReservesByEmail(cursor.getString(0));
            cursor1.moveToNext();
            String string = "";
            if(cursor1.getCount()>0){
                string  = cursor.getString(0)+""+"\n"+ Car.allCars.get(Integer.parseInt(cursor.getString(1))).getMake()+" "+Car.allCars.get(Integer.parseInt(cursor.getString(1))).getModel()+"\n"+cursor.getString(2);
                reserveInfos.add(string);
            }
        }
        RecyclerView recyclerView = view.findViewById(R.id.recyclerAdminRes);
        AdapterResView resAdapter = new AdapterResView(getActivity().getApplicationContext(), reserveInfos);
        recyclerView.setAdapter(resAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

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