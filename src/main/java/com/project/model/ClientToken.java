package com.project.model;

/**
 * Created by muhamd gomaa on 2/13/2018.
 */
public class ClientToken {

    private String token;
    public ClientToken(String token)
    {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
