package com.example.satishamhetre.contactbook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.satishamhetre.contactbook.Data.Contact_Adapter;
import com.example.satishamhetre.contactbook.Data.Contract;
import com.example.satishamhetre.contactbook.Data.PhoneBookHelper;
import com.example.satishamhetre.contactbook.Data.data_item;

import java.util.ArrayList;

public class contact_display extends AppCompatActivity {

    ArrayList<data_item> list = new ArrayList<>();

    data_item item1 = new data_item();

    public int c = 0;


    private Contact_Adapter contact_adapter1;
    private RecyclerView recyclerView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_display);

        recyclerView1 = (RecyclerView) findViewById(R.id.contact_display);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView1.setLayoutManager(layoutManager);

        recyclerView1.setHasFixedSize(false);

        Cursor cursor;

        PhoneBookHelper helper = new PhoneBookHelper(this);
        final SQLiteDatabase db = helper.getReadableDatabase();

        String[] column = {Contract.Entry.COLUMN_PERSON_NAME, Contract.Entry.COLUMN_PERSON_NUMBER};
        cursor = db.query(Contract.Entry.TABLE_NAME, column, null, null, null, null, null);

        cursor.moveToFirst();
        c = 0;

        ArrayList<data_item> list = new ArrayList<>();
        if (cursor.getCount() > 0) {
            do {
                c++;
                data_item item1 = new data_item();
                item1.name = cursor.getString(0);
                item1.number = cursor.getString(1);

                list.add(item1);
            } while (cursor.moveToNext());


            cursor.close();
            contact_adapter1 = new Contact_Adapter(list, c);
            recyclerView1.setAdapter(contact_adapter1);
            contact_adapter1.notifyDataSetChanged();

            }

        }





}
