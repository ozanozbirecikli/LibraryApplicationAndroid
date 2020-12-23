package com.example.libraryapp.Requests;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
            final Activity activity,
            String Name,
            String Surname,
            String Email,
            String Password
    ) {

        mActivity = activity;
        Map<String, String> params = new HashMap<>();
        params.put("Name", Name);
        params.put("Surname", Surname);
        params.put("Email", Email);
        params.put("Password", Password);
        final JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, requestUrls.SignUpUrl, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                    listener.sendResponse(response);
//                    gDialogHelper.DismissLoadingMessage();

                    JSONObject obj = response.getJSONObject("Status");
                    Gson gson = new Gson();


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
        });

        //gDialogHelper.ShowLoadingMessage();
        RequestHandler.getInstance(mActivity).addToRequestQueue(jsonObjectRequest);
    }
}
