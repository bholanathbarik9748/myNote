package com.example.mynote;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity2 extends AppCompatActivity {

    // infiltration
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    //    Set menu option
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.add_note) {
            Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
            startActivity(intent);

            return true;
        }
        return false;
    }

    //    auto topic selector
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListView list = (ListView) findViewById(R.id.listView);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("com.example.mynote", Context.MODE_PRIVATE);
        HashSet<String> set  = (HashSet<String>) sp.getStringSet("Note",null);
        if(set == null){
            notes.add("Empty!!!!!");
        }else{
            notes = new ArrayList(set);
        }



        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra("a", position);
                startActivity(intent);
            }
        });

//        Delete Button
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(MainActivity2.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you Sure?")
                        .setMessage("Do you really want do delete this Note ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                                SharedPreferences sp = getApplicationContext().getSharedPreferences("com.example.mynote", Context.MODE_PRIVATE);
                                HashSet<String> set  = new HashSet(MainActivity2.notes);
                                sp.edit().putString("notes", String.valueOf(set)).apply();
                            }
                        }).setNegativeButton("No", null).show();
                return true;
            }
        });
    }
}