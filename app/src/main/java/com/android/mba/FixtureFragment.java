package com.android.mba;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment; import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class FixtureFragment extends Fragment {

    RecyclerView recyclerView;
    FixtureAdapter adapter;
    List<Fixture> fixtures;
    private ProgressDialog pDialog;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        fixtures = new ArrayList<>();
        view = inflater.inflate(R.layout.fixturelayout, viewGroup, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        getTeams(view.getContext());
        return view;
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(view.getContext());
        pDialog.setMessage("Listing fixtures Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private String getFormattedTime(String dbTime)
    {
        StringTokenizer items = new StringTokenizer(dbTime,":");
        String formattedTime = "";
        String hour = "";
        String minutes = "";

        hour = items.nextToken();
        minutes = items.nextToken();

        formattedTime = hour + ":" + minutes;

        return formattedTime;
    }

    private String getFormattedDate(String dbDate)
    {
        StringTokenizer items = new StringTokenizer(dbDate,"-");
        String formattedDate = "";
        String day = "";
        String month = "";
        String year = "";
        year = items.nextToken();
        month = items.nextToken();
        day = items.nextToken();

        formattedDate = day + "-" + month + "-" + year;

        return formattedDate;
    }

    private void getTeams(final Context con) {
        displayLoader();

        Map<String, String> params = new HashMap<String, String>();
//
//        params.put(KEY_FULL_NAME, fullName);
//        params.put(KEY_PASSWORD, password);


//        JSONObject request = new JSONObject(params);

        String URLString = "http://10.0.2.2:5000/getfixtures";
        JsonArrayRequest jsArrayRequest = new JsonArrayRequest
                (Request.Method.GET, URLString, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pDialog.dismiss();
                        try {
                            JSONArray jsonarray = response;
                            for(int i=0; i<jsonarray.length(); i++) {
                                JSONObject obj = jsonarray.getJSONObject(i);

                                String teamA = obj.getString("teamAId");
                                String teamB = obj.getString("teamBId");
                                String gameDate = obj.getString("matchDate");
                                String gameTime = obj.getString("matchTime");

                                String formattedDate = getFormattedDate(gameDate);
                                String formattedTime = getFormattedTime(gameTime);

                                fixtures.add(
                                        new Fixture(teamA,
                                                    teamB,
                                                    formattedDate,
                                                    formattedTime));
                            }

                            adapter = new FixtureAdapter(con, fixtures);
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
                        Toast.makeText(view.getContext().getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        jsArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(view.getContext()).addToRequestQueue(jsArrayRequest);
    }
}