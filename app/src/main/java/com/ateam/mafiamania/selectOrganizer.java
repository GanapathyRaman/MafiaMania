package com.ateam.mafiamania;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by eganama on 21-May-16.
 */
public class selectOrganizer extends AppCompatActivity implements View.OnClickListener {

    private ArrayAdapter<String> adapter;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_organizer);

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtview, GameObjects.playersList);
        // ListView listV = (ListView) findViewById(R.id.list2);
        // listV.setAdapter(adapter);
        //listV.setLongClickable(true);
// Sorting
        Collections.sort(GameObjects.playersList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2)
            {

                return  s1.toString().compareTo(s2.toString());
            }
        });

        addRadioButtons(GameObjects.playersList.size());

        Button btNext=(Button)findViewById(R.id.button_next2);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameObjects.organizerPos  = selectedPosition;
                GameObjects.organizer = GameObjects.playersList.get(selectedPosition).toString();
                Intent intent = new Intent(selectOrganizer.this, Temp.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                Toast.makeText(getApplicationContext(), GameObjects.organizer, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addRadioButtons(int number) {

        final ArrayList <RadioButton> rdbtnList = new ArrayList<RadioButton>();

        for (int row = 0; row < number; row++) {
            final RadioGroup ll = new RadioGroup(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            final RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(row);
            rdbtn.setText(GameObjects.playersList.get(row));
            rdbtn.setTextSize(30);
            rdbtn.setTag(row);
            rdbtnList.add(row, rdbtn);
            ll.addView(rdbtn);


            ((ViewGroup) findViewById(R.id.radioButton)).addView(ll);

            rdbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ll.clearCheck();
                    System.out.println("################### Selected Position: " + selectedPosition);
                    int selectedId = (Integer)view.getId();
                    if(selectedPosition != -1) {
                        RadioButton temRdbtn = (RadioButton) rdbtnList.get(selectedPosition);
                        temRdbtn.setChecked(false);
                    }
                    rdbtn.setChecked(true);
                    selectedPosition = selectedId;
                    System.out.println("################### Selected Position: " + selectedPosition);
                    adapter.notifyDataSetChanged();
                }
            });
        }


    }


    @Override
    public void onClick(View v) {
            /* Don't remove this function. This is part of implements */
    }
}
