package com.example.libraryapp.DTO;

public class Statics {

    private static final Statics INSTANCE = new Statics();

    public static Statics getInstance() {
        return INSTANCE;
    }
    public static Users loggedInUser = new Users();

}
