package com.example.libraryapp.Requests;

public class requestUrls {

    //    public static String BaseURL = "http://10.0.2.2:8080"; //base url
    public static String BaseURL = "http://10.0.2.2:5000/api"; //base url
//    http://localhost:57819
//    public static String BaseURL = "http://sarapp-prod-v2.api-abplus.com"; //canlı

    public static String SignUpUrl = BaseURL + "/users/SignUp";
    public static String LoginUrl = BaseURL + "/users/SignIn";
    public static String Items = BaseURL + "/items";

//    public static String GetAllProducer = BaseURL + "/WineProducer/GetAllProducers";

}