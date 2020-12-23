package com.example.libraryapp.Requests;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetAllItems {



    public static Activity mActivity;
    private static Listener listener;
    //dialog


    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void sendResponse(Object meta);
    }

    public static void GetItems(
            final Activity activity
    ) {

        mActivity = activity;



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrls.Items, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                    listener.sendResponse(response);
//                    gDialogHelper.DismissLoadingMessage();

//                    JSONObject obj = response.getJSONObject("Status");
//                    Gson gson = new Gson();
                    Log.wtf("logging", response.toString());

//                    JSONArray responseJSONArray = response.getJSONArray("results");

                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                gDialogHelper.DismissLoadingMessage();
                error.printStackTrace();
                listener.sendResponse(null);
            }
        });

        //gDialogHelper.ShowLoadingMessage();
        RequestHandler.getInstance(mActivity).addToRequestQueue(jsonObjectRequest);
    }
}
