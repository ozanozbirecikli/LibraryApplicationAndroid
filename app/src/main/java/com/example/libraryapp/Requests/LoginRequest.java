package com.example.libraryapp.Requests;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.libraryapp.DTO.Statics;
import com.example.libraryapp.DTO.Users;
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


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, requestUrls.getInstance().LoginUrl, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    Gson gson = new Gson();
                    Log.wtf("logined", "" + response.toString());
                    boolean result = response.getBoolean("result");
                    if (result) {
//                    gDialogHelper.DismissLoadingMessage();
                        Log.wtf("logined", "result is true " + response.toString());

                        JSONObject obj = response.getJSONObject("user");
                        Users logedInUser = gson.fromJson(obj.toString(), Users.class);
                        Statics.getInstance().loggedInUser = logedInUser;

                    }

                    listener.sendResponse(response);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                gDialogHelper.DismissLoadingMessage();
                listener.sendResponse(null);
            }
        }) {

            /*   @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Email", Email);
                    params.put("Password", Password);

                    return params;
                }*/
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                return headers;
            }

        };

        //gDialogHelper.ShowLoadingMessage();
        RequestHandler.getInstance(mActivity).addToRequestQueue(jsonObjectRequest);
    }
}
