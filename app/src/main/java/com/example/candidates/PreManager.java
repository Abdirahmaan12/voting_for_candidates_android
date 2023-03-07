package com.example.cs19k_android;

import android.content.Context;
import android.content.SharedPreferences;

public class PreManager {
    SharedPreferences pr;
    SharedPreferences.Editor editor;

    public  PreManager(Context ctx){
        pr=ctx.getSharedPreferences("cs19k", Context.MODE_PRIVATE);
        editor=pr.edit();



    }

    public  void setV_id(String v_id)
    {
       editor.putString("v_id", v_id);
       editor.commit();
    }

    public  String getv_id(){return pr.getString("v_id", "");}


    public  void set_elec_id(String elec_id)
    {
        editor.putString("elec_id", elec_id);
        editor.commit();
    }

    public  String get_elec_id(){return pr.getString("elec_id", "");}
}

