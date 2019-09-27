package com.android.mba;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private Button btnGetTeams;
    RecyclerView recyclerView;
    TeamAdapter adapter;
    List<Team> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teams = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        teams.add(
//                new Team(
//                        1,
//                        "Congo Nets",
//                        "Ngere",
//                        "Kongowea",
//                        1,
//                        R.drawable.macbook));
//
//        teams.add(
//                new Team(
//                        2,
//                        "Maweni Hornets",
//                        "Kanai",
//                        "Maweni",
//                        2,
//                        R.drawable.dellinspiron));
//
//        teams.add(
//                new Team(
//                        3,
//                        "Kisauni Kings",
//                        "Jackie",
//                        "Kisauni",
//                        3,
//                        R.drawable.surface));

//        btnGetTeams = findViewById(R.id.btnGetTeams);
//
//        btnGetTeams.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
                //Retrieve the data entered in the edit texts

                getTeams(this);
//            }
//        });
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Signing Up.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void getTeams(final Context con) {
        displayLoader();

        Map<String, String> params = new HashMap<String, String>();
//
//        params.put(KEY_FULL_NAME, fullName);
//        params.put(KEY_PASSWORD, password);


//        JSONObject request = new JSONObject(params);

        String URLString = "http://10.0.2.2:5000/getteams";
        JsonArrayRequest jsArrayRequest = new JsonArrayRequest
                (Request.Method.GET, URLString, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pDialog.dismiss();
                        try {
                            JSONArray jsonarray = response;
                            for(int i=0; i<jsonarray.length(); i++) {
                                JSONObject obj = jsonarray.getJSONObject(i);

                                String name = obj.getString("name");
                                String location = obj.getString("location");
                                String id = obj.getString("id");
                                String coach = obj.getString("coach");
                                String position = obj.getString("position");

//                                teams.add(
//                                        new Team(
//                                                Integer.parseInt(id),
//                                                name,
//                                                coach,
//                                                location,
//                                                Integer.parseInt(position),
//                                                R.drawable.dellinspiron));
                            }

                            adapter = new TeamAdapter(con, teams);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        String message = error.getLocalizedMessage();
                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        jsArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }
}
