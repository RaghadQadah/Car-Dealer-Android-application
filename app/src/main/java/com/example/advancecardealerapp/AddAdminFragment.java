package com.example.advancecardealerapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAdminFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddAdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddAdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddAdminFragment newInstance(String param1, String param2) {
        AddAdminFragment fragment = new AddAdminFragment();
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

        View view = inflater.inflate(R.layout.fragment_add_admin, container, false);
        // Inflate the layout for this fragment
        return view;



    }

    @Override
    public void onStart() {
        super.onStart();
        final Context context = getActivity().getApplicationContext();

        final SharedPrefManager sharedPrefManager =SharedPrefManager.getInstance(context);
        final EditText firstName = (EditText)getActivity().findViewById(R.id.first_name);
        final EditText lastName = (EditText)getActivity().findViewById(R.id.last_name);
        final EditText email = (EditText)getActivity().findViewById(R.id.e_mail);
        final EditText password = (EditText)getActivity().findViewById(R.id.password);
        final EditText confirm = (EditText)getActivity().findViewById(R.id.confirm);
        final EditText phone = (EditText)getActivity().findViewById(R.id.phone);
        final Button signUp = getActivity().findViewById(R.id.signup_button);

        String[] options = { "Male", "Female" };
        final Spinner genderSpinner =(Spinner) getActivity().findViewById(R.id.gender_spinner);
        ArrayAdapter<String> objGenderArr = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item, options);
        genderSpinner.setAdapter(objGenderArr);

        TextView area = (TextView)getActivity().findViewById(R.id.areaCode);
        final Spinner city = (Spinner)getActivity().findViewById(R.id.citySpinner);
        final Spinner country = (Spinner)getActivity().findViewById(R.id.countrySpinner);
        final String[] countries = {"Palestine","Jordan","USA","Syria"};
        final HashMap<String,String[]> countryToCityMap = new HashMap<>();
        final HashMap<String , ArrayAdapter> countryToAdapterMap = new HashMap<>();
        final HashMap<String ,String> countryToAreaCode = new HashMap<>();
        countryToAreaCode.put("Palestine","00970");
        countryToAreaCode.put("Jordan","00962");
        countryToAreaCode.put("Syria","00963");
        countryToAreaCode.put("USA","001");

        countryToCityMap.put("Palestine",new String[]{"Ramallah","Jericho","Jenin","Nablus"});
        countryToCityMap.put("Jordan",new String[]{"Amman","Balqaa","Jarash","Zarqa"});
        countryToCityMap.put("USA",new String[]{"Detroit","Los Angeles","NYC","Chicago"});
        countryToCityMap.put("Syria",new String[]{"Damascus","Aleppo","Homs","Hama"});

        ArrayAdapter coutriesAdapter = new ArrayAdapter (context,android.R.layout.simple_spinner_item, countries);
        ArrayAdapter jordanAdapter = new ArrayAdapter (context,android.R.layout.simple_spinner_item, countryToCityMap.get("Jordan"));
        ArrayAdapter stateAdapter = new ArrayAdapter (context,android.R.layout.simple_spinner_item, countryToCityMap.get("USA"));
        ArrayAdapter SyriaAdapter = new ArrayAdapter (context,android.R.layout.simple_spinner_item, countryToCityMap.get("Syria"));
        ArrayAdapter palestinianAdapter = new ArrayAdapter (context,android.R.layout.simple_spinner_item, countryToCityMap.get("Palestine"));

        countryToAdapterMap.put("Palestine",palestinianAdapter);
        countryToAdapterMap.put("Jordan",jordanAdapter);
        countryToAdapterMap.put("USA",stateAdapter);
        countryToAdapterMap.put("Syria",SyriaAdapter);
        country.setAdapter(coutriesAdapter);

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String countryValue = country.getSelectedItem().toString();
                city.setAdapter(countryToAdapterMap.get(countryValue));
                area.setText(countryToAreaCode.get(countryValue));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty() || firstName.getText().toString().isEmpty() ||
                        lastName.getText().toString().isEmpty() || password.getText().toString().isEmpty() ||
                        confirm.getText().toString().isEmpty() || phone.getText().toString().isEmpty()){
                    Toast toast =Toast.makeText(context, "Something's Missing... Check Input Fields",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(!email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                    Toast toast =Toast.makeText(context, "Email is not Valid",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(firstName.getText().toString().length() < 2 && lastName.getText().toString().length() < 2){
                    Toast toast =Toast.makeText(context, "Name can't be less than 2 characters",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(!Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_,.@#$%^&+=])(?=\\S+$).{4,15}$").matcher(password.getText().toString()).matches()){
                    Toast toast =Toast.makeText(context, "password should include capital,small,number,special char",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(!password.getText().toString().equals(confirm.getText().toString())){
                    Toast toast =Toast.makeText(context, "passwords doesn't match",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(phone.getText().toString().length() < 9 && !phone.getText().toString().substring(0,1).equals("5")){
                    Toast toast =Toast.makeText(context, "Phone not valid",Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    CarsDataBaseHelper dataBaseHelper =new CarsDataBaseHelper(context,"User",null,1);

                    dataBaseHelper.insert(email.getText().toString(),firstName.getText().toString(),lastName.getText().toString(),
                            password.getText().toString(),genderSpinner.getSelectedItem().toString(),phone.getText().toString());


                    User user = new User(email.getText().toString(),firstName.getText().toString(),lastName.getText().toString(),
                            password.getText().toString(),genderSpinner.getSelectedItem().toString(),phone.getText().toString(),true);

                    user.allUsers.add(user);

                    System.out.println("*********"+user.allUsers.toString());

                   // Toast toast =Toast.makeText(context, "Successfully Registered",Toast.LENGTH_LONG);
                    //toast.show();

                    sharedPrefManager.writeString("userName",email.getText().toString());
                    sharedPrefManager.writeString("password",password.getText().toString());

                    Toast.makeText(context, "Added Admin Done Successfully", Toast.LENGTH_SHORT).show();

                    // Intent intent = new Intent(context, AdminHomeActivity.class);
                    // context.startActivity(intent);
                    //finish();
                }
            }
        });

















    }



    }