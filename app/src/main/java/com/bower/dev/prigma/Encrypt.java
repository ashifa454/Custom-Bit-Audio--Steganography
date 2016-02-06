package com.bower.dev.prigma;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Encrypt extends AppCompatActivity {
    genericSongClass GSC;
    RecyclerView recvie;
    SetAdapter adapter;
    String radd,filename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(radd==null){
                Snackbar.make(view,"Please choose a Song to Encrypt your Message",Snackbar.LENGTH_LONG).show();
            }
            else{
            gotoNext();
            }}
        });
        FloatingActionButton fab2=(FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDecrypt();
            }
        });
        SetRecycler(getPlayList());
    }
    public void gotoNext(){
        Intent i=new Intent(this,Encypt_back.class);
        i.putExtra("FName",radd);
        startActivity(i);
    }
    public void gotoDecrypt(){
        Intent i=new Intent(this,Decrypt_back.class);
        i.putExtra("FName",radd);
        i.putExtra("name",filename);
        startActivity(i);
    }
    private void SetRecycler(ArrayList<HashMap<String, String>> list) {
        recvie = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(Encrypt.this);
        adapter = new SetAdapter(Encrypt.this, list,Encrypt.this);
        recvie.setAdapter(adapter);
        recvie.setLayoutManager(llm);
        recvie.setHasFixedSize(true);
    }
    final String MEDIA_PATH = Environment.getExternalStorageDirectory().getPath() + "/";
    private ArrayList<HashMap<String, String>> songsList;
    private String mp3Pattern = ".wav";
    public ArrayList<HashMap<String, String>> getPlayList() {
        songsList= new ArrayList<HashMap<String, String>>();
        if (MEDIA_PATH != null) {
            File home = new File(MEDIA_PATH);
            File[] listFiles = home.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {
                        addSongToList(file);
                    }
                }
            }
        }
        return songsList;
    }
    private void scanDirectory(File directory) {
        if (directory != null) {
            File[] listFiles = directory.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {
                        addSongToList(file);
                    }

                }
            }
        }
    }
    private void addSongToList(File song) {
        if (song.getName().endsWith(mp3Pattern)) {
            HashMap<String, String> songMap = new HashMap<String, String>();
            songMap.put("songTitle", song.getName().substring(0, (song.getName().length() - 4)));
            songMap.put("songPath", song.getPath());
            // Adding each song to SongList
            songsList.add(songMap);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_encrypt, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
class genericSongClass {
    String songTitle = "";
    String songArtist = "";
    String songData = "";
    String isChecked = "false";
}
