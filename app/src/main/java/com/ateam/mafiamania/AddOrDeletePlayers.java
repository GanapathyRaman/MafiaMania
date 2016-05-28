package com.ateam.mafiamania;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.util.ArrayList;


public class AddOrDeletePlayers extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private EditText updateText;
    private int itemPosNo = -1;

    public static ArrayAdapter<String> adapter; //To access it anywhere
    private PopupWindow pwindo;

    private final String edit = "Edit";
    private final String remove = "Remove";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_delete_players);
        GameObjects.playersList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtview,GameObjects.playersList);
        ListView listV=(ListView)findViewById(R.id.list1);
        listV.setAdapter(adapter);

        registerForContextMenu(listV);
        listV.setLongClickable(true);

        editText=(EditText)findViewById(R.id.txtPlayerName);
        Button btAdd=(Button)findViewById(R.id.button_addPlayer);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = editText.getText().toString();
                // add new item to arraylist
                GameObjects.playersList.add(newItem);
                // notify listview of data changed
                adapter.notifyDataSetChanged();
                //Clear textbox
                editText.setText("");
            }
        });

        Button btNext=(Button)findViewById(R.id.button_next1);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddOrDeletePlayers.this, selectOrganizer.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        itemPosNo = info.position;
        Object item = adapter.getItem(itemPosNo);
        String name = item.toString();

        menu.setHeaderTitle("Select the action for " + name);
        menu.add(0, v.getId(), 0, edit);//groupId, itemId, order, title
        menu.add(0, v.getId(), 1, remove);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()== edit){
            initiateEditPopupWindow();
        } else if(item.getTitle()== remove){
            removeTheUserFromList();
        } else{
            return false;
        }
        return true;
    }

    private void removeTheUserFromList () {
        if(itemPosNo != -1) {
            GameObjects.playersList.remove(itemPosNo);
            adapter.notifyDataSetChanged();
        }
    }

    private void initiateEditPopupWindow() {
        Button btnUpdatePopup;
        Button btnClosePopup;

        try {
        // We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) AddOrDeletePlayers.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.edit_popup, (ViewGroup) findViewById(R.id.popup_element));

            pwindo = new PopupWindow(layout,
                                     WindowManager.LayoutParams.WRAP_CONTENT,
                                     WindowManager.LayoutParams.WRAP_CONTENT,
                                     true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);


            updateText=(EditText)layout.findViewById(R.id.updateUserTxtBox);
            btnUpdatePopup = (Button) layout.findViewById(R.id.btn_update_popup);
            btnUpdatePopup.setOnClickListener(update_button_click_listener);

            btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);
            btnClosePopup.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private View.OnClickListener update_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            try {
                String updateUserName = updateText.getText().toString();
                // Add new item to arraylist
                GameObjects.playersList.set(itemPosNo, updateUserName);

                // Notify listview of data changed
                adapter.notifyDataSetChanged();

                // Dismiss the popup window
                pwindo.dismiss();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            pwindo.dismiss();
        }
    };

    @Override
    public void onClick(View v) {
            /* Don't remove this function. This is part of implements */
    }
}