package com.example.cs19k_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
//        loadresult();
    }



//    private void loadresult() {
//        String id=new PreManager(ResultActivity.this).get_elec_id();
//        String url="http://10.0.2.2/Cs19kandroid_test/results.php?elec_id="+id;
//
//        JsonArrayRequest ja= new JsonArrayRequest(Request.Method.GET,
//                url,
//                null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//                        try {
//                            JSONObject ob=response.getJSONObject( 0);
//
//                            if(ob.getString("code").equals("yes"))
//                            {
//                                adapter= new ResultAdapter(response, ResultActivity.this);
//                                res_rec.setAdapter(adapter);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ResultActivity.this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        RequestQueue q= Volley.newRequestQueue(ResultActivity.this);
//        q.add(ja);
//
//    }
}
