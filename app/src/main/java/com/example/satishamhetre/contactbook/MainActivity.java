package com.example.satishamhetre.contactbook;

import android.content.ContentValues;
import android.content.Intent;
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


    private SQLiteDatabase db;

    EditText e1 ;
    EditText e2 ;

    TextView t1;
    TextView t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         e1 =(EditText) findViewById(R.id.enter_name);
         e2 =(EditText) findViewById(R.id.enter_number);

        t1=(TextView)findViewById(R.id.show_name);
        t2=(TextView)findViewById(R.id.show_number);

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



     //   contact_display cd=new contact_display();
       // cd.update();
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

           t1.setText(cursor.getString(1));
            t2.setText(cursor.getString(0));

        }

        cursor.close();

    }

    public void SeeAllContacts(View view){

        Intent intent =new Intent(view.getContext(),contact_display.class);
        startActivity(intent);

    }


}
