package com.example.libraryapp.Requests;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.libraryapp.DTO.Items;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
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



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrls.getInstance().Items, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    if(response.getBoolean("result")) {

                        Gson gson = new Gson();
                        Log.wtf("GetAllItems", response.toString());

                        Type listType = new TypeToken<ArrayList<Items>>(){}.getType();
                        JSONArray responseJSONArray = response.getJSONArray("objects");
                        ArrayList<Items> allItems =gson.fromJson(responseJSONArray.toString(), listType);
                        listener.sendResponse(allItems);
                    }

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
