package com.example.cs19k_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Candidates extends AppCompatActivity {

    RecyclerView can_rec;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates);

        can_rec= findViewById(R.id.can_rec);
        can_rec.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(Candidates.this);
        can_rec.setLayoutManager(layoutManager);

        loadAllcandidates();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.votingmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.v_result)
        {

            startActivity(new Intent(Candidates.this, ResultActivity.class));
        }

        if(item.getItemId()==R.id.v_refresh)
        {

            loadAllcandidates();
        }
        if(item.getItemId()==R.id.logout)
        {

            startActivity(new Intent(Candidates.this, LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadAllcandidates() {
        String id=new PreManager(Candidates.this).get_elec_id();
        String url="http://10.0.2.2/Cs19kandroid_test/candidates.php?elec_id="+id;

        JsonArrayRequest ja= new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONObject ob=response.getJSONObject( 0);

                            if(ob.getString("code").equals("yes"))
                            {
                                adapter= new CanAdapter(response, Candidates.this);
                                can_rec.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Candidates.this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue q= Volley.newRequestQueue(Candidates.this);
        q.add(ja);

    }
}