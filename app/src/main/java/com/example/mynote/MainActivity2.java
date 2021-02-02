package com.example.mynote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    // infiltration
    ArrayList<String> notes = new ArrayList<>();
    String []listViewA = new String[]{
            "one","two","three","four","five","six","seven","eight","nine","ten"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListView list =(ListView) findViewById(R.id.listView);
        notes.add("BB");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,notes);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String templist = listViewA[position].toString();
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("a",templist);
                startActivity(intent);
            }
        });
    }
}