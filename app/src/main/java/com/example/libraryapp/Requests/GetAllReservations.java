package com.example.libraryapp.Requests;

import android.app.Activity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.libraryapp.DTO.Items;
import com.example.libraryapp.DTO.Reservation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetAllReservations {



    public static Activity mActivity;
    private static Listener listener;
    //dialog


    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void sendResponse(Object meta);
    }

    public static void getAllReservations(
            Activity activity,
            int User_Id
    ) {

        mActivity = activity;
        Map<String, Object> params = new HashMap<>();


        final JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrls.getInstance().MyReservedItems+User_Id, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    boolean result = response.getBoolean("result");
                    if (result) {
                        Gson gson = new Gson();
                        Log.wtf("GetAllReservations", response.toString());

                        Type listType = new TypeToken<ArrayList<Items>>(){}.getType();
                        JSONArray responseJSONArray = response.getJSONArray("items");
                        ArrayList<Items> allItems =gson.fromJson(responseJSONArray.toString(), listType);
                        listener.sendResponse(allItems);
                    }

                } catch (Exception e) {
                    e.printStackTrace();;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                gDialogHelper.DismissLoadingMessage();
                listener.sendResponse(null);
            }
        }) {

/*
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Name", Name);
                params.put("Surname", Surname);
                params.put("Email", Email);
                params.put("Password", Password);

                return params;
            }*/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
//                 headers.put("Content-Type", "application/json; charset=utf-8");
//                headers.put("Authorization", "[My SAS Key]");
//                headers.put("Cache-Control", "no-cache");
//                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };

        //gDialogHelper.ShowLoadingMessage();
        RequestHandler.getInstance(mActivity).addToRequestQueue(jsonObjectRequest);
    }



}
