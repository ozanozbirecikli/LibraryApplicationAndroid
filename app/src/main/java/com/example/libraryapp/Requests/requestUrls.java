package com.example.libraryapp.Requests;

public class requestUrls {

    private static requestUrls INSTANCE = null;

    public static requestUrls getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new requestUrls();
        }
        return(INSTANCE);
    }

    //    public static String BaseURL = "http://10.0.2.2:8080"; //base url
    public static String BaseURL = "http://10.0.2.2:5000/api"; //base url

    public static String SignUpUrl = BaseURL + "/users/SignUp";
    public static String LoginUrl = BaseURL + "/users/SignIn";
    public static String Items = BaseURL + "/items";
    public static String AddItems = BaseURL + "/items/AddItem";
    public static String ReserveItem = BaseURL + "/rezervations/Reserve";
    public static String MyReservedItems = BaseURL + "/rezervations/ShowReservations?USER_ID=";
    public static String ReturnItem = BaseURL + "/rezervations/Return";

}
