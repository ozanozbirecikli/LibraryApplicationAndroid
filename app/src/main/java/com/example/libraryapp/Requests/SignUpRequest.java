package com.example.libraryapp.Requests;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpRequest {

    public static Activity mActivity;
    private static Listener listener;
    //dialog


    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void sendResponse(Object meta);
    }

    public static void SendSignUpRequest(
            Activity activity,
            String Name,
            String Surname,
            String Email,
            String Password,
            int User_role
    ) {

        mActivity = activity;
        Map<String, Object> params = new HashMap<>();
        params.put("Name", Name);
        params.put("Surname", Surname);
        params.put("Email", Email);
        params.put("Password", Password);
        params.put("User_role", User_role);
        final JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, requestUrls.getInstance().SignUpUrl, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                    listener.sendResponse(response);
//                    gDialogHelper.DismissLoadingMessage();
//                    JSONObject obj = response.getJSONObject("Status");
                    Gson gson = new Gson();
                    Log.wtf("Signup", "" + response.toString());

                    boolean result = response.getBoolean("result");
                    if (result) {
                        Log.wtf("signed", "result true " + response.toString());

                        listener.sendResponse(response);
                    }

//                    JSONArray responseJSONArray = response.getJSONArray("results");

                } catch (Exception e) {

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
