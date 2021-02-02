package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    TextView editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

//        Id
        editText =  (TextView) findViewById(R.id.editText);
        String tempholder = getIntent().getStringExtra("a");
        editText.setText(tempholder);
    }
}