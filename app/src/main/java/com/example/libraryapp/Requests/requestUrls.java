package com.example.libraryapp.Requests;

public class requestUrls {

    //    public static String BaseURL = "http://10.0.2.2:8080"; //base url
    public static String BaseURL = "http://10.0.2.2:5000/api"; //base url

    public static String SignUpUrl = BaseURL + "/users/SignUp";
    public static String LoginUrl = BaseURL + "/users/SignIn";
    public static String Items = BaseURL + "/items";
    public static String AddItems = BaseURL + "/items/AddItem";

}
