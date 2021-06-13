package com.example.advancecardealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefManager =SharedPrefManager.getInstance(this);
        setContentView(R.layout.activity_login);
        final Button signUp = findViewById(R.id.signup);
        final Button signIn = findViewById(R.id.signin);
        final EditText email = (EditText)findViewById(R.id.email);
        final EditText password = (EditText)findViewById(R.id.pass);
        final CheckBox checkBox = findViewById(R.id.checkBox);

        SharedPreferences sharedPreferences = getSharedPreferences("MyData",
                Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("email","");
        String pass = sharedPreferences.getString("password","");
        email.setText(name);
        password.setText(pass);
        if(email.getText().toString().length()!=0)
            checkBox.setChecked(true);



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                LoginActivity.this.startActivity(intent);
                finish();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   DataBaseHelper dataBaseHelper1 = new DataBaseHelper(LoginActivity.this, "ADMIN", null, 1);
              //  boolean isAdmin = dataBaseHelper1.LogInAsAdmin(email.getText().toString(), password.getText().toString());

//                boolean asAdmin = dataBaseHelper1.LogInAsAdmin(email.getText().toString(), password.getText().toString());
//
//                if ( asAdmin==true) {
//                    //System.out.println("ADMIN");
//                    Intent myIntent = new Intent(LoginActivity.this, AdminHomeActivity.class);
//                    LoginActivity.this.startActivity(myIntent);

                //}else
                    if((email.getText().toString().equals("admin") && password.getText().toString().equals("admin")) ){
                    Intent myIntent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                    LoginActivity.this.startActivity(myIntent);


                } else {


                    if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                        Toast toast = Toast.makeText(LoginActivity.this, "Empty Email or Password... Try Again", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        CarsDataBaseHelper dataBaseHelper = new CarsDataBaseHelper(LoginActivity.this, "User", null, 1);

                        boolean isValid = dataBaseHelper.LogIn(email.getText().toString(), password.getText().toString());

                        if (isValid) {


                            User user = new User();
                            System.out.println("*********"+user.allUsers.toString());

                            int adminFlage=0;
                            for (User User : user.allUsers) {
                                if(User.getEmail().equals(email.getText().toString())){
                                    if(User.isAdmin){
                                        adminFlage=1;
                                    }
                                }

                            }

                            System.out.println("*****55****"+adminFlage);

                            if(adminFlage==0) {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                LoginActivity.this.startActivity(intent);
                                finish();
                            }else{

                                    Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                                    LoginActivity.this.startActivity(intent);
                                    finish();
                                }


                            Toast toast = Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG);
                            toast.show();




                            SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            if(checkBox.isChecked()){
                                editor.putString("email",email.getText().toString());
                                editor.commit();
                                editor.putString("password",password.getText().toString());
                                editor.commit();
                            }
                            editor.putString("loggedIn",email.getText().toString());
                            editor.commit();

//                            Cursor cursor = dataBaseHelper.getUserByEmail(email.getText().toString());
//                            System.out.println("*****33****"+cursor.getString(0));




                          //  Cursor cursor=dataBaseHelper.getUserByEmail(email.getText().toString());
                           /// System.out.println("*****33****"+cursor.getString(0));



                        } else {
                            Toast toast = Toast.makeText(LoginActivity.this, "Check Email or Password", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                }
            }
        });
    }

    public Boolean isInAdmin(String Email) {
        //DatabaseHelper dataBaseHelper =new DatabaseHelper(this,"Project",null,1);
        CarsDataBaseHelper dataBaseHelper =new CarsDataBaseHelper(LoginActivity.this,"ADMIN",null,1);
        String ed = "";
        final Cursor allAdmineEmails = dataBaseHelper.getAllAdmins();
        while (allAdmineEmails.moveToNext()) {
            ed = allAdmineEmails.getString(0);
            if(ed.equals(Email)) {
                return true;
            }
        }
        return false;
    }
}

//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.database.Cursor;
//import android.os.Bundle;
//
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//public class LoginActivity extends AppCompatActivity {
//    Button login, signup;
//    EditText email, password;
//    CheckBox rememberMe;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        login = (Button) findViewById(R.id.signin);
//        signup = (Button) findViewById(R.id.signup);
//        email = (EditText) findViewById(R.id.email);
//        password = (EditText) findViewById(R.id.pass);
//        rememberMe = (CheckBox) findViewById(R.id.checkBox);
//        SharedPreferences sharedPreferences = getSharedPreferences("MyData",
//                Context.MODE_PRIVATE);
//        String name = sharedPreferences.getString("email", "");
//        email.setText(name);
//
//        if (email.getText().toString().length() != 0) rememberMe.setChecked(true);
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent myIntent = new Intent(LoginActivity.this,
//                        SignupActivity.class);
//                LoginActivity.this.startActivity(myIntent);
//            }
//        });
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (email.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
//                    System.out.println("ADMIN");
//                    Intent myIntent = new Intent(LoginActivity.this,
//                            AdminHomeActivity.class);
//                    LoginActivity.this.startActivity(myIntent);
//                } else {
//                    CarsDataBaseHelper dataBaseHelper = new CarsDataBaseHelper(LoginActivity.this, "tst", null, 1);
//                    Cursor cursor = dataBaseHelper.getUserByEmail(email.getText().toString());
//                   // CarsDataBaseHelper dataBaseHelper = new CarsDataBaseHelper(LoginActivity.this, "User", null, 1);
//
//                    boolean isValid = dataBaseHelper.LogIn(email.getText().toString(), password.getText().toString());
//                    String errorText = "";
//                   // if (cursor.getCount() == 0) errorText += "E-mail doesn't Exist \n";
//                    if (isValid == false) errorText += "E-mail doesn't Exist \n";
//                    else {
//                        cursor.moveToNext();
//                        String hashedInputPassword = getHashedPassword(password.getText().toString());
//                        String hashedFromDB = cursor.getString(5);
//                        System.out.println("FROM DB -> " + hashedFromDB);
//                        if (!hashedInputPassword.equals(hashedFromDB))
//                            errorText += "Incorrect Password!";
//                        else {
//                            SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            if (rememberMe.isChecked()) {
//                                editor.putString("email", email.getText().toString());
//                                editor.commit();
//                            }
//                            editor.putString("loggedIn", email.getText().toString());
//                            editor.commit();
//                            System.out.println("remembering ->>>> " + sharedPreferences.getString("email", "none"));
//                            System.out.println("logged in ->>>> " + sharedPreferences.getString("loggedIn", "none"));
//
//
//                        }
//                    }
//                    if (errorText.length() != 0)
//                        Toast.makeText(LoginActivity.this, errorText, Toast.LENGTH_LONG).show();
//                    else {
//                        if (cursor.getString(2).equals("1")) {
//                            Intent myIntent = new Intent(LoginActivity.this,
//                                    AdminHomeActivity.class);
//                            LoginActivity.this.startActivity(myIntent);
//                        } else {
//                            Intent myIntent = new Intent(LoginActivity.this,
//                                    HomeActivity.class);
//                            LoginActivity.this.startActivity(myIntent);
//                        }
//
//                    }
//                }
//
//
//            }
//        });
//    }
//
//    public  String getHashedPassword(String pw){
////        String passwordToHash = pw;
////        String generatedPassword = null;
////        try {
////            // Create MessageDigest instance for MD5
////            MessageDigest md = MessageDigest.getInstance("MD5");
////            //Add password bytes to digest
////            md.update(passwordToHash.getBytes());
////            //Get the hash's bytes
////            byte[] bytes = md.digest();
////            //This bytes[] has bytes in decimal format;
////            //Convert it to hexadecimal format
////            StringBuilder sb = new StringBuilder();
////            for(int i=0; i< bytes.length ;i++)
////            {
////                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
////            }
////            //Get complete hashed password in hex format
////            generatedPassword = sb.toString();
////        }
////        catch (NoSuchAlgorithmException e)
////        {
////            e.printStackTrace();
////        }
////        return generatedPassword;
//        return pw;
//    }
//}




