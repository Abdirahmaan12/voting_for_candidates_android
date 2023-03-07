package com.example.cs19k_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText ed_username=findViewById(R.id.ed_username);
        EditText ed_password=findViewById(R.id.ed_password);
        Button BTNSave=findViewById(R.id.BTNSave);

        BTNSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String user=ed_username.getText().toString();
                String pass=ed_password.getText().toString();


                checkVoiter(user, pass);
            }
        });


    }


    void   checkVoiter(String user, String pass)
    {
        String url="http://10.0.2.2/Cs19kandroid_test/voters.php?username="+user+"&pass="+pass;
        JsonArrayRequest ja= new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONObject ob = response.getJSONObject(0);

                            if (ob.getString("code").equals("yes")) {
                                if (ob.getInt("v_status") == 1) {
                                    // 1.save IDS  to sharepreference

                                    PreManager pr=new PreManager(LoginActivity.this);
                                    pr.set_elec_id(ob.getString("elec_id"));
                                    pr.setV_id(ob.getString("id"));

                                    //  2. next activit(candidates activit)



                                    startActivity(new Intent(LoginActivity.this,
                                            Candidates.class));
                                } else {
                                    Toast.makeText(LoginActivity.this, "Election window is closed",
                                            Toast.LENGTH_LONG).show();

                                }

                            } else {

                                Toast.makeText(LoginActivity.this, "username or password are in correct",
                                        Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "error"+error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue q= Volley.newRequestQueue(LoginActivity.this);
        q.add(ja);


    };



}

