package com.example.satishamhetre.contactbook.Data;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.satishamhetre.contactbook.MainActivity;
import com.example.satishamhetre.contactbook.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Satish A. Mhetre on 17-06-2017.
 */

public class Contact_Adapter extends RecyclerView.Adapter <Contact_Adapter.contactHolder  >{

    ArrayList<data_item> list=new ArrayList<>();

    public int NumberOfItems;


    public  Contact_Adapter(ArrayList<data_item> list1,int number){
        NumberOfItems =number;
        list=list1;
    }


    @Override
    public contactHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.contacts;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem,parent, shouldAttachToParentImmediately);


        contactHolder contactHolder1 =new contactHolder(view);

        return contactHolder1;
    }

    @Override
    public void onBindViewHolder(contactHolder holder, int position) {

        data_item item;
        System.out.println(list.size()+"  "+ position);
        item=list.get(position);
        holder.name.setText(item.name);
        holder.number.setText(item.number);

    }

    @Override
    public int getItemCount(){
        return NumberOfItems;
    }


    class contactHolder extends RecyclerView.ViewHolder{

        TextView name ;
        TextView number;


        public contactHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            number= (TextView) itemView.findViewById(R.id.number);


        }


    }


}
