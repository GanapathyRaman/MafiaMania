package com.ateam.mafiamania;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;


public class AddOrDeletePlayers extends AppCompatActivity implements View.OnClickListener {
    ArrayAdapter<String> adapter;
    EditText editText;
    ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_delete_players);
        //String[] items={"Apple","Banana","Clementine"};
        itemList=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtview,itemList);
        ListView listV=(ListView)findViewById(R.id.list);
        listV.setAdapter(adapter);
        editText=(EditText)findViewById(R.id.txtPlayerName);
        Button btAdd=(Button)findViewById(R.id.button_addPlayer);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem=editText.getText().toString();
                // add new item to arraylist
                itemList.add(newItem);
                // notify listview of data changed
                adapter.notifyDataSetChanged();
                //Clear textbox
                editText.setText("");
            }

        });

        Button btNext=(Button)findViewById(R.id.button_next);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddOrDeletePlayers.this, Temp.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });


    }

    @Override
    public void onClick(View v) {
            /* This logic has been taken care */
    }


}