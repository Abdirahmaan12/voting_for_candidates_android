package com.example.cs19k_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyStudentsAdapter extends RecyclerView.Adapter<MyStudentsAdapter.MyViewHolder> {
    JSONArray res;
    Context ctx;

    public MyStudentsAdapter(JSONArray response, Context context) {
        res= response;
        ctx= context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v= LayoutInflater.from(ctx).inflate(R.layout.studentssinglerow,
                parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        try {
            JSONObject object = res.getJSONObject(position);
            holder.txtstdname.setText(object.getString("std_name"));
            holder.txtstdphone.setText(object.getString("std_phone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return res.length();
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtstdname, txtstdphone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtstdname=itemView.findViewById(R.id.txtstdname);
            txtstdphone=itemView.findViewById(R.id.txtstdphone);
        }
    }
}
