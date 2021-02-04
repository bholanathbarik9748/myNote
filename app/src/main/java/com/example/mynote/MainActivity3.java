package com.example.mynote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

public class MainActivity3 extends AppCompatActivity {
    int tempholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

//        Id
        EditText editText = (EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
        tempholder = intent.getIntExtra("a", -1);

        if (tempholder != -1) {
            editText.setText(MainActivity2.notes.get(tempholder));
        } else {
            MainActivity2.notes.add("");
            tempholder = MainActivity2.notes.size() - 1;
            MainActivity2.arrayAdapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity2.notes.set(tempholder, String.valueOf(s));
                MainActivity2.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sp = getApplicationContext().getSharedPreferences("com.example.mynote", Context.MODE_PRIVATE);
                HashSet<String> set  = new HashSet(MainActivity2.notes);
                sp.edit().putString("notes", String.valueOf(set)).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}