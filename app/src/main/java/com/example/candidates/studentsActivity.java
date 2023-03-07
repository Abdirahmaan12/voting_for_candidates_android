package com.example.cs19k_android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class studentsActivity extends AppCompatActivity {

    EditText ed_std_name, ed_std_phone;
    Button btnsave;

    RecyclerView std_rec;
    RecyclerView.LayoutManager layoutmanage;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students2);

         ed_std_name= findViewById(R.id.ed_std_name);
         ed_std_phone= findViewById(R.id.ed_std_phone);
         btnsave= findViewById(R.id.btnsave);

         btnsave.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String stdname= ed_std_name.getText().toString();
                 String stdphone= ed_std_phone.getText().toString();


                 saveStudents(stdname, stdphone);
             }
         });


         std_rec=findViewById(R.id.std_rec);
         layoutmanage=new LinearLayoutManager(studentsActivity.this);
         std_rec.setLayoutManager(layoutmanage);

         loadAllStudents();





    }

    private void loadAllStudents() {
        String url="http://10.0.2.2/Cs19kandroid_test/students.php";
        JsonArrayRequest ja=new JsonArrayRequest(Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        adapter=new MyStudentsAdapter(response, studentsActivity.this);
                        std_rec.setAdapter(adapter);


                    }
                },     new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(studentsActivity.this, "error"+error.getMessage(),
                        Toast.LENGTH_LONG).show();

            }
        }


        );

        RequestQueue q=Volley.newRequestQueue(studentsActivity.this);
        q.add(ja);

    }


    void saveStudents(String stdname,String stdphone)
    {

        String url="http://10.0.2.2/Cs19kandroid_test/std_register.php";
        StringRequest sr=new StringRequest(Request.Method.POST,

                url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(studentsActivity.this,response,

                                Toast.LENGTH_LONG).show();
                        loadAllStudents();




                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(studentsActivity.this,"error"+error.getMessage(),

                                Toast.LENGTH_LONG).show();

                    }
                }


        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params=new HashMap<>();
                params.put("stdname",stdname);
                params.put("stdphone",stdphone);
                return params;
            }
        };


        RequestQueue queue= Volley.newRequestQueue(studentsActivity.this);
        queue.add(sr);
    }

}