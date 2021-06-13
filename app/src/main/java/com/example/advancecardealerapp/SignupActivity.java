package com.example.advancecardealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sharedPrefManager =SharedPrefManager.getInstance(this);
        final EditText firstName = (EditText)findViewById(R.id.first_name);
        final EditText lastName = (EditText)findViewById(R.id.last_name);
        final EditText email = (EditText)findViewById(R.id.e_mail);
        final EditText password = (EditText)findViewById(R.id.password);
        final EditText confirm = (EditText)findViewById(R.id.confirm);
        final EditText phone = (EditText)findViewById(R.id.phone);
        final Button signUp = findViewById(R.id.signup_button);

        String[] options = { "Male", "Female" };
        final Spinner genderSpinner =(Spinner) findViewById(R.id.gender_spinner);
        ArrayAdapter<String> objGenderArr = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, options);
        genderSpinner.setAdapter(objGenderArr);

        TextView area = (TextView)findViewById(R.id.areaCode);
        final Spinner city = (Spinner)findViewById(R.id.citySpinner);
        final Spinner country = (Spinner)findViewById(R.id.countrySpinner);
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
        ArrayAdapter coutriesAdapter = new ArrayAdapter (this,android.R.layout.simple_spinner_item, countries);
        ArrayAdapter jordanAdapter = new ArrayAdapter (this,android.R.layout.simple_spinner_item, countryToCityMap.get("Jordan"));
        ArrayAdapter stateAdapter = new ArrayAdapter (this,android.R.layout.simple_spinner_item, countryToCityMap.get("USA"));
        ArrayAdapter SyriaAdapter = new ArrayAdapter (this,android.R.layout.simple_spinner_item, countryToCityMap.get("Syria"));
        ArrayAdapter palestinianAdapter = new ArrayAdapter (this,android.R.layout.simple_spinner_item, countryToCityMap.get("Palestine"));
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
                    Toast toast =Toast.makeText(SignupActivity.this, "Something's Missing... Check Input Fields",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(!email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                    Toast toast =Toast.makeText(SignupActivity.this, "Email is not Valid",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(firstName.getText().toString().length() < 2 && lastName.getText().toString().length() < 2){
                    Toast toast =Toast.makeText(SignupActivity.this, "Name can't be less than 2 characters",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(!Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_,.@#$%^&+=])(?=\\S+$).{4,15}$").matcher(password.getText().toString()).matches()){
                    Toast toast =Toast.makeText(SignupActivity.this, "password should include capital,small,number,special char",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(!password.getText().toString().equals(confirm.getText().toString())){
                    Toast toast =Toast.makeText(SignupActivity.this, "passwords doesn't match",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(phone.getText().toString().length() < 9 && !phone.getText().toString().substring(0,1).equals("5")){
                    Toast toast =Toast.makeText(SignupActivity.this, "Phone not valid",Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    CarsDataBaseHelper dataBaseHelper =new CarsDataBaseHelper(SignupActivity.this,"User",null,1);

                    dataBaseHelper.insert(email.getText().toString(),firstName.getText().toString(),lastName.getText().toString(),
                            password.getText().toString(),genderSpinner.getSelectedItem().toString(),phone.getText().toString());

                    User user = new User(email.getText().toString(),firstName.getText().toString(),lastName.getText().toString(),
                            password.getText().toString(),genderSpinner.getSelectedItem().toString(),phone.getText().toString(),false);

                    user.allUsers.add(user);

                    System.out.println("*********"+user.allUsers.toString());



                    Toast toast =Toast.makeText(SignupActivity.this, "Successfully Registered",Toast.LENGTH_LONG);
                    toast.show();

                    sharedPrefManager.writeString("userName",email.getText().toString());
                    sharedPrefManager.writeString("password",password.getText().toString());

                    sharedPrefManager.writeString("firstName",firstName.getText().toString());
                    sharedPrefManager.writeString("lastName",lastName.getText().toString());
                    sharedPrefManager.writeString("gender",genderSpinner.getSelectedItem().toString());
                    sharedPrefManager.writeString("phone",phone.getText().toString());


                    SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //editor.putString("email",email.getText().toString());
                    //editor.commit();
                    editor.putString("loggedIn",email.getText().toString());
                    editor.commit();

                    Toast.makeText(SignupActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                    SignupActivity.this.startActivity(intent);
                    finish();
                }
            }
        });
    }
}