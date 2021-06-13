package com.example.advancecardealerapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class CarJasonParser {

    public static List<Car> getObjectFromJason(String jason) {
        List<Car> cars;
        try {
            JSONArray jsonArray = new JSONArray(jason);
            cars = new ArrayList<>();
            for(int i = 0; i<jsonArray.length(); i++)
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject= (JSONObject) jsonArray.get(i);
                Car car = new Car();
                car.setId(i);
                car.setYear(jsonObject.getInt("year"));
                car.setMake(jsonObject.getString("make"));
                car.setModel(jsonObject.getString("model"));
                car.setDistance(jsonObject.getString("distance"));
                car.setPrice(jsonObject.getInt("price"));
                car.setAccidents(jsonObject.getBoolean("accidents"));
                car.setOffers(jsonObject.getBoolean("offers"));
                cars.add(car);
                Car.allCars.add(car);

                if(car.isOffers()){
                    Car.offerCars.add(car);
                }
            }

        } catch (JSONException e)
        { e.printStackTrace();
            return null;
        }
        return cars;
    }
}