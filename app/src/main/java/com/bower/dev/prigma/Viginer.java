/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* Code is Fully Devloped and Maintained by Ashif Ansari 
    And Individual is fully allowed to use and Modify Codes/*
*/
package com.bower.dev.prigma;

/**
 *

 */
class Viginer {
/**
 *
 */
    /**
     * @param args the command line arguments
     */  
    private String key,msg;
    public int match;
    private int keyCode[],msgcode[],keys[],Decrypt[];
    public char DecrpytedMsg[];
    public Viginer(String s,String me)
    {
        this.key=s;
        this.msg=me;
    }
    public void changeKey()
    {
        char keyDevided[]=new char[key.length()];
        char MsgContainer[]=new char[msg.length()];
        keyDevided=key.toCharArray();
        MsgContainer=msg.toCharArray();
        keyCode=new int[key.length()];
        msgcode=new int[msg.length()];
       for(int counter=0;counter<key.length();counter++)
        {
            for(int subcounter=65;subcounter<91;subcounter++)
            {    
                match=keyDevided[counter];
                if(subcounter==match)
                {
                    keyCode[counter]=match-65;
                  }
            }
            
        }
       for(int counter=0;counter<msg.length();counter++)
        {
            for(int subcounter=65;subcounter<91;subcounter++)
            {    
                match=MsgContainer[counter];
                if(subcounter==match)
                {
                    msgcode[counter]=match-65;
                  }
            }
            
        }
    }
    public void allocate()
    {
        keys=new int[msgcode.length];
        for(int i=0,k=0;i<msgcode.length;i++,k++)
        {
            keys[i]=keyCode[k];
            if(k==keyCode.length-1)
                k=-1;
        }
    }
    public String Calculateus()
    {
        int temp;
       String EncryptMsg="";
        for(int i=0;i<msg.length();i++)
       {
           temp=keys[i]+msgcode[i];
           if(temp>25)
           {
               temp=temp-26;
           }
           temp=temp+65;
           EncryptMsg=EncryptMsg+(char)temp;
       }
        return EncryptMsg;
    }
    public String CalculateDec()
    {
        String DecryptMsg="";
        int temp=0;
        for(int i=0;i<msg.length();i++)
        {    temp=msgcode[i]-keys[i];
        if(temp<0)
            temp=temp+26;
        char Al=(char) (temp+65);
        DecryptMsg=DecryptMsg+Al;
        }
        return DecryptMsg;
    }
}
