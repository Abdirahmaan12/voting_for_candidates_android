package com.example.cs19k_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CanAdapter extends RecyclerView.Adapter<CanAdapter.MyViewHolder> {
    JSONArray res;
    Context ctx;
    public CanAdapter(JSONArray response, Candidates candidates) {
        res=response;
        ctx=candidates;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.canrowsingle,
                parent, false);
        return new MyViewHolder(v);
    }
 
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        try {
            JSONObject ob=res.getJSONObject(position);

            holder.txt_can_name.setText(ob.getString("name"));
            holder.txt_can_memo.setText(ob.getString("memo"));
            holder.Txt_can_po_name.setText(ob.getString("p_name"));


            String imagurl="http://10.0.2.2/Cs19kandroid_test/"+ob.getString("img");


            Glide.with(ctx).load(imagurl).into(holder.can_img);


            holder.btnvote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Register voite

                    try {
                        PreManager pr=new PreManager(ctx);
                        String p_id=ob.getString("p_id");
                        String c_id=ob.getString("id");
                        String e_id=pr.get_elec_id();
                        String v_id=pr.getv_id();
                        saveVoite(e_id,c_id,v_id,p_id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveVoite(String e_id, String c_id, String v_id, String p_id) {
        String url="http://10.0.2.2/Cs19kandroid_test/voiting.php?elec_id="+e_id+"&v_id="+v_id+
                "&c_id="+c_id+"&p_id="+p_id;


        StringRequest sr=new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(ctx, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ctx, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

       Volley.newRequestQueue(ctx).add(sr);


    }

    @Override
    public int getItemCount() {
        return res.length();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder
    {

        ImageView can_img;
        TextView txt_can_name, txt_can_memo, Txt_can_po_name;
        Button btnvote;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            can_img=itemView.findViewById(R.id.can_img);
            txt_can_name=itemView.findViewById(R.id.txt_can_name);
            txt_can_memo=itemView.findViewById(R.id.txt_can_memo);
            Txt_can_po_name=itemView.findViewById(R.id.txt_can_position_name);

            btnvote= itemView.findViewById(R.id.btnvote);
        }
    }
}
