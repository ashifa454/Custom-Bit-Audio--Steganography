package com.bower.dev.prigma;

import android.support.design.widget.Snackbar;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
public class BackWorker {
    String msg,tempData,desname,decrmesg="";
    char a[];
    byte EncrytedSong[];
    int rslt[],counter=0;
    String rst="",raw="";;
    String songdata="";
    char srcdata[];
    int add[];
    int position;
    char tempstore[]=new char[8];
    ArrayList<String> ar,ar2;
    ArrayList<String> Decr;
    Viginer v;
    HashMap<Character, Integer> container;
    Iterator it;
    public BackWorker(int pos){
        this.position=pos;
        container=new HashMap();
        container.put('A',65);
        container.put('B',66);
        container.put('C',67);
        container.put('D',68);
        container.put('E',69);
        container.put('F',70);
        container.put('G',71);
        container.put('H',72);
        container.put('I',73);
        container.put('J',74);
        container.put('K',75);
        container.put('L',76);
        container.put('M',77);
        container.put('N',78);
        container.put('O',79);
        container.put('P',80);
        container.put('Q',81);
        container.put('R',82);
        container.put('S',83);
        container.put('T',84);
        container.put('U',85);
        container.put('V',86);
        container.put('W',87);
        container.put('X',88);
        container.put('Y',89);
        container.put('Z',90);
        container.put('@',40);
    }
    public void stringTobinary(String emsg)
    {
        this.msg=emsg;
        a=new char[emsg.length()+1];
        a=msg.toCharArray();
        rslt=new int[a.length];
        for(int i=0;i<a.length;i++)
        {
               rslt[i]=container.get(a[i]);
               rst=rst+Integer.toBinaryString(rslt[i]);
        }
    }
    public void SongToBinary(byte data[]){
       this.EncrytedSong=new byte[data.length];
        this.EncrytedSong=data;
        ar=new ArrayList();
        add=new int[rst.length()];
        int j=0;
        for(int i=0;i<data.length;i++){
           tempData=Integer.toBinaryString(data[i]);
            if(tempData.length()==7){
               counter++;
               if(counter<=rst.length()){
                   ar.add(j, tempData);
                   add[j]=i;
                   j++;
               }
               else
               {break;}
           }
        }
    }
    public void Stuffing(){
    srcdata=new char[rst.length()];
    srcdata=rst.toCharArray();
    it=ar.iterator();
    ar2=new ArrayList();
    int i=0;
    while(it.hasNext())
    {
       songdata=it.next().toString();
       tempstore=songdata.toCharArray();
       tempstore[position]=srcdata[i];
       ar2.add(String.copyValueOf(tempstore));
       i++;
    }
    ar.clear();
   }
    public void LetsDoSave(){
        byte temp;
        int i=0;
        it=ar2.iterator();
        System.out.println("Saving As:");
        while(it.hasNext()){
        temp=Byte.parseByte(it.next().toString(),2);
        this.EncrytedSong[add[i]]=temp;
        
        i++;
        }
        desname=((Object)counter).toString()+".wav";
    }
    public void DoDecrypt(byte data[],String name){
        Decr=new ArrayList();
        String temp;
        try{
        counter=Integer.parseInt((String) name.subSequence(0, name.indexOf(".wav")));
            int c2=0;
        for(int i=0;i<data.length;i++){
            tempData=Integer.toBinaryString(data[i]);
            if(tempData.length()==7){
               if(c2<counter-1){
                   temp=Integer.toBinaryString(data[i]);
                   Decr.add(temp);  
                    c2++;
               }
               else
               break;
           }
        }
    }
    catch(NumberFormatException e){
        System.out.println("ENCRYPTION FAIL");
    }
   }
    public void bitResolver(){
        it=Decr.iterator();
        String tempStorage;
        while(it.hasNext()){
            tempStorage=it.next().toString();
            decrmesg=decrmesg+tempStorage.charAt(position);
        }
       int beg=0;
       byte consumer;
       for(int i=0;i<((counter-1)/7);i++){
           consumer=Byte.parseByte(decrmesg.substring(beg, beg+7),2);
           raw=raw+(char)consumer;
           beg=beg+7;
       }
    }
}
