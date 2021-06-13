package com.example.advancecardealerapp;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class DeleteCustomerAdapter extends RecyclerView.Adapter<DeleteCustomerAdapter.ListViewHolder> {

    private  ArrayList<String > emails;
    Context context;

    public DeleteCustomerAdapter(Context context,ArrayList<String> emails){
        this.context = context;
        this.emails = emails;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_delete_item_list,viewGroup,false);
        return new ListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ListViewHolder listViewHolder, int i) {

        final CarsDataBaseHelper dataBaseHelper = new CarsDataBaseHelper(context,"tst",null,1);
        //final Cursor cursor = dataBaseHelper.getUserByEmail(emails.get(i));
        //cursor.moveToNext();
        listViewHolder.textView.setText(emails.get(i));

        listViewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.deleteByEmail(emails.get(i));
                User user = new User();

                for (User User : user.allUsers) {
                    if (User.getEmail()==emails.get(i))
                        user.allUsers.remove(User);
                   // emails.add(User.getEmail());
                   // System.out.println("***emails"+User.getEmail());
                    listViewHolder.textView.setText(emails.get(i));
                }


                Toast.makeText(context,"Deleted!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return emails.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;
        ImageButton imageButton;
        public ListViewHolder(View itemView){
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.text_in_delete_customer_list_item);
            imageButton = (ImageButton)itemView.findViewById(R.id.delete_customer_button_in_list);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

        }
    }
}