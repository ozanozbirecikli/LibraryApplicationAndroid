package com.example.libraryapp.Requests;

import android.app.Activity;
import android.util.Log;

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

public class LoginRequest {

    public static Activity mActivity;
    private static Listener listener;
    //dialog


    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void sendResponse(Object meta);
    }

    public static void SendLogInRequest(
            final Activity activity,
            final String Email,
            final String Password
    ) {


        mActivity = activity;
        Map<String, String> params = new HashMap<>();

        params.put("Email", Email);
        params.put("Password", Password);
        JSONObject parameters = new JSONObject(params);
       /* try {

            parameters.put("Email", Email);
            parameters.put("Password", Password);
        }catch (Exception e){
            e.printStackTrace();
        }*/


        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, requestUrls.LoginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
//                    listener.sendResponse(response);
//                    gDialogHelper.DismissLoadingMessage();
                    Log.wtf("logined", "" + response.toString());

//                    JSONObject obj = response.getJSONObject("Status");
//                    String obj = response.getString("message");
                    Log.wtf("logined", "" + response.toString());
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
        }){

           @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Email", Email);
                params.put("Password", Password);

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String,String>();
                 headers.put("Content-Type", "application/json; charset=utf-8");
//                headers.put("Authorization", "[My SAS Key]");
//                headers.put("Cache-Control", "no-cache");
//                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
//            @Override
//            public String getBodyContentType() {
//                return "application/json";
//            }

        };

        //gDialogHelper.ShowLoadingMessage();
        RequestHandler.getInstance(mActivity).addToRequestQueue(jsonObjectRequest);
    }
}
