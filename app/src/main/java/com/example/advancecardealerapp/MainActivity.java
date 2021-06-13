package com.example.advancecardealerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button connectButton;
    ArrayList<Car> carListFromRest = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectButton = findViewById(R.id.connectToRestButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionAsyncTask connectionAsyncTask= new ConnectionAsyncTask(MainActivity.this);
                connectionAsyncTask.execute("http://www.mocky.io/v2/5bfea5963100006300bb4d9a");
            }
        });
    }

    public void fillCarList(ArrayList<Car> list) {
        if (list == null) {
            Toast.makeText(this, "Error While Connecting ",
                    Toast.LENGTH_LONG).show();
        } else {
            carListFromRest = list;
            CarsDataBaseHelper dataBaseHelper =new CarsDataBaseHelper(MainActivity.this,"Car",null,1);
            dataBaseHelper.deleteAllCars();

            for (Car car : carListFromRest)
                dataBaseHelper.insertCar(car.getId(),car.getModel(),car.getMake(),car.getDistance(),car.getYear(),car.getPrice(),car.isAccidents(),car.isOffers());

//            Toast.makeText(this, "DONE Connecting ",
//                    Toast.LENGTH_LONG).show();

             Intent myIntent=new Intent(MainActivity.this,
                    LoginActivity.class);
            MainActivity.this.startActivity(myIntent);




        }
    }
}