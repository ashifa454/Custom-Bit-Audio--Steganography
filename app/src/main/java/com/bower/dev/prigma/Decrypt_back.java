package com.bower.dev.prigma;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.FileNotFoundException;

public class Decrypt_back extends AppCompatActivity {
    String fname,name;
    BackWorker b;
    wavIO w;
    int bit;
    Spinner s;
    Viginer viginer;
    EditText key,msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt_back);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        key=(EditText)findViewById(R.id.ED2);
        msg=(EditText)findViewById(R.id.ed2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b = new BackWorker(bit);
                w = new wavIO(fname);
                w.read();
                byte data[] = w.myData;
                b.DoDecrypt(data, name+".wav");
                b.bitResolver();
                viginer = new Viginer(key.getText().toString().toUpperCase(), b.raw.toUpperCase());
                viginer.changeKey();
                viginer.allocate();
                msg.setText(viginer.CalculateDec());
                Snackbar.make(view,"Decryption has been Completed",Snackbar.LENGTH_LONG).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        s=(Spinner)findViewById(R.id.spinner_nav);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        bit=1;
                        break;
                    case 1:
                        bit=2;
                        break;
                    case 2:
                        bit=3;
                        break;
                    case 3:
                        bit=4;
                        break;
                    case 4:
                        bit=5;
                        break;
                    case 5:
                        bit=6;
                        break;
                    case 6:
                        bit=7;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Intent intent = getIntent();
        fname = intent.getStringExtra("FName");
        name=intent.getStringExtra("name");
    }
}
