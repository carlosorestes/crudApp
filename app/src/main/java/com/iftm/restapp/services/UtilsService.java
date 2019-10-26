package com.iftm.restapp.services;

public class UtilsService {

    private static UtilsService instance;
    private String token;
    private static  String BEARER = "Bearer ";

    private UtilsService(){}

    public static synchronized UtilsService getInstance(){
        if (instance == null){
            instance = new UtilsService();
        }
        return instance;
    }

    public String getToken() {
        return BEARER+token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
