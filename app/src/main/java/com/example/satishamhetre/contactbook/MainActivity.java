package com.example.satishamhetre.contactbook;

import android.content.ContentValues;
import android.content.pm.FeatureInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.example.satishamhetre.contactbook.Data.Contact_Adapter;
import com.example.satishamhetre.contactbook.Data.Contract;
import com.example.satishamhetre.contactbook.Data.PhoneBookHelper;
import com.example.satishamhetre.contactbook.Data.data_item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<data_item> list=new ArrayList<>();

    data_item item1=new data_item();



    private Contact_Adapter contact_adapter1;
    private RecyclerView recyclerView1;
    private SQLiteDatabase db;
    public  int c=0;
    EditText e1 ;
    EditText e2 ;

    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

        ArrayList<data_item> list=new ArrayList<>();
        if (cursor.getCount() > 0){
            do {
                c++;
                data_item item1=new data_item();
                item1.name = cursor.getString(0);
                item1.number = cursor.getString(1);

                list.add(item1);
            } while (cursor.moveToNext());

            for(int i=0;i<c;i++){
                item1=list.get(i);
                System.out.println(  item1.name+" "+ item1.number+"      ");
            }
        cursor.close();
        contact_adapter1 = new Contact_Adapter(list, c);
        recyclerView1.setAdapter(contact_adapter1);
        contact_adapter1.notifyDataSetChanged();
        }

         e1 =(EditText) findViewById(R.id.enter_name);
         e2 =(EditText) findViewById(R.id.enter_number);

        t1=(TextView)findViewById(R.id.show_name);

        t1.setText(c+"");



    }


    public void AddContact(View view){

        if(e1.getText().length()==0 ||
                e2.getText().length()==0)
            return;

        String name= e1.getText().toString();
        String number = e2.getText().toString();

        ContentValues cv= new ContentValues();

        cv.put(Contract.Entry.COLUMN_PERSON_NAME,name);
        cv.put(Contract.Entry.COLUMN_PERSON_NUMBER,number);

        PhoneBookHelper helper1 =new PhoneBookHelper(this);

        final SQLiteDatabase db1= helper1.getWritableDatabase();

        long l=db1.insert(Contract.Entry.TABLE_NAME,null,cv);


        e2.clearFocus();
        e1.getText().clear();
        e2.getText().clear();

        String []column =  { Contract.Entry.COLUMN_PERSON_NAME , Contract.Entry.COLUMN_PERSON_NUMBER };
        Cursor cursor = db1.query(Contract.Entry.TABLE_NAME,column,null,null,null,null,null );

        ArrayList<data_item> list=new ArrayList<>();
        cursor.moveToFirst();

        c=0;
       do {
           c++;
            data_item item1=new data_item();
            item1.name = cursor.getString(0);
            item1.number =cursor.getString(1);
            list.add(item1);
        } while(cursor.moveToNext());



        cursor.close();
        contact_adapter1 = new Contact_Adapter(list, c);
        recyclerView1.setAdapter(contact_adapter1);
        contact_adapter1.notifyDataSetChanged();

        t1.setText(c+"");

    }


    public void Search(View view){

        String name= e1.getText().toString();

        String s[]={name};


        PhoneBookHelper helper =new PhoneBookHelper(this);
       final SQLiteDatabase db =helper.getReadableDatabase();
        Cursor cursor;

        cursor = db.rawQuery("SELECT " + Contract.Entry.COLUMN_PERSON_NUMBER + " , " + Contract.Entry.COLUMN_PERSON_NAME + " FROM " + Contract.Entry.TABLE_NAME + " WHERE "+ Contract.Entry.COLUMN_PERSON_NAME + "=?" ,s );

        cursor.moveToFirst();

        if(cursor.getString(0)!=null) {

           t1.setText(cursor.getString(0));

        }

        cursor.close();

    }





}
