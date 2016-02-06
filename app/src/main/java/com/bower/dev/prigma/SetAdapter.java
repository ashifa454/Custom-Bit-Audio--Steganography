package com.bower.dev.prigma;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by Ash!f on 11/30/2015.
 */
public class SetAdapter extends RecyclerView.Adapter<SetAdapter.myVisiualHolder> {
    private LayoutInflater inflator;
    ArrayList<HashMap<String,String>> list;
    String mode,name,id;
    double lx,ly;
    Context cn;
    Encrypt enc;
    public SetAdapter(Context context,ArrayList<HashMap<String,String>> h,Encrypt enc){
        inflator=LayoutInflater.from(context);
        this.list=h;
        cn=context;
        this.enc=enc;
    }
    @Override
    public myVisiualHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflator.inflate(R.layout.card_layout,parent,false);
        myVisiualHolder holder=new myVisiualHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final myVisiualHolder holder, int position) {
        final HashMap<String,String> temph=list.get(position);
        holder.tview.setText(temph.get("songTitle"));
        holder.tview2.setText(temph.get("songPath"));
        holder.tview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                enc.radd=temph.get("songPath");
                enc.filename=temph.get("songTitle");
                Snackbar.make(v,"Song selected for Encryption"+ temph.get("songTitle"),Snackbar.LENGTH_LONG).show();
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class myVisiualHolder extends RecyclerView.ViewHolder{
        TextView tview,tview2;
        public myVisiualHolder(View itemView) {
            super(itemView);
            tview=(TextView)itemView.findViewById(R.id.person_name);
            tview2=(TextView)itemView.findViewById(R.id.person_age);
        }
    }
}
