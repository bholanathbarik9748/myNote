package com.example.mynote;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

//        Id
        EditText editText = (EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
        final int tempholder = intent.getIntExtra("a", -1);

        if (tempholder != -1) {
            editText.setText(MainActivity2.notes.get(tempholder));
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity2.notes.set(tempholder, String.valueOf(s));
                MainActivity2.arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}