package com.example.advancecardealerapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ListViewHolder> {

    private  ArrayList<Pair<Integer,Car>> cars;
    Context context;
    public OffersAdapter(Context context,ArrayList<Pair<Integer,Car>> cars){
        this.context = context;
        this.cars = cars;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.special_offer_list_item,viewGroup,false);
        return new ListViewHolder(view);
//        return null;
    }

    @Override
    public void onBindViewHolder(ListViewHolder listViewHolder, int i) {


        Car car = Car.offerCars.get(cars.get(i).first);
        final int carid = car.getId();

        SharedPreferences sharedPreferences = context.getSharedPreferences("MyData",
                Context.MODE_PRIVATE);

        final String name = sharedPreferences.getString("userName","");


        String details = "";
        details+="Year: "+car.getYear()+"\n";
        details+="Make: "+car.getMake()+"\n";
        details+="Model: "+car.getModel()+"\n";
        details+="Distance: "+car.getDistance()+"\n";
        details+="Price: "+car.getPrice()+"\n";
        //System.out.println(details);

        listViewHolder.textView.setText(details);
        //listViewHolder.textView.setText("AAA");

        listViewHolder.button1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                CarsDataBaseHelper dataBaseHelper = new CarsDataBaseHelper(context,"tst",null,1);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                System.out.println(dtf.format(now));

                dataBaseHelper.insertReserve(name,cars.get(carid).first,dtf.format(now));

                Toast toast = Toast.makeText(context, "Reservation DONE", Toast.LENGTH_LONG);
                toast.show();
                System.out.println("Reservation DONE");

            }
        });
        listViewHolder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarsDataBaseHelper dataBaseHelper = new CarsDataBaseHelper(context,"tst",null,1);
                dataBaseHelper.insertFavorite(name,cars.get(carid).first);
                Toast toast = Toast.makeText(context, "Added to Favorite Successfully", Toast.LENGTH_LONG);
                toast.show();



            }
        });
    }
    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;
        public ImageButton button1;
        public ImageButton button2;
        public ListViewHolder(View itemView){
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.special_offers_text);
            button2 = (ImageButton) itemView.findViewById(R.id.fav_button_menu_offers);
            button1 = (ImageButton) itemView.findViewById(R.id.reserve_button_menu_offers);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
        }
    }
}