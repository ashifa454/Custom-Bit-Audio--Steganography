package com.bower.dev.prigma;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Encypt_back extends AppCompatActivity {
    Spinner s;
    String enmsg;
    int bit;
    BackWorker b;
    wavIO w;
    Viginer viginer;
    String fname;
    EditText msg,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encypt_back);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        fname = intent.getStringExtra("FName");
        msg=(EditText)findViewById(R.id.EditText02);
        pass=(EditText)findViewById(R.id.editText2);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(msg.getText().equals("")||pass.getText().equals("")){
                    Snackbar.make(view,"Please Ensure You all the Entries are Filled",Snackbar.LENGTH_LONG).show();
                }
                else{
                    viginer=new Viginer(pass.getText().toString().toUpperCase(),msg.getText().toString().toUpperCase());
                    viginer.changeKey();
                    viginer.allocate();
                    enmsg= viginer.Calculateus();
                    System.out.println("MSG"+enmsg);
                    b=new BackWorker(bit);
                    b.stringTobinary(enmsg);
                    w=new wavIO(fname);
                    w.read();
                    byte data[]=w.myData;
                    b.SongToBinary(data);
                    b.Stuffing();
                    Snackbar s=Snackbar.make(view,"Encryption has been Completed",Snackbar.LENGTH_INDEFINITE);
                    s.setAction("Save",new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            b.LetsDoSave();
                            w.save(b.desname,b.EncrytedSong);
                            Snackbar s=Snackbar.make(v,"Saved Successfully",Snackbar.LENGTH_INDEFINITE);
                            s.show();
                        }
                    });
                    s.show();
                }
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
    }

}
