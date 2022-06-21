package com.cognizant.auth.models;

import org.springframework.stereotype.Component;

@Component
public class Logout {

    String client_id;
    String access_token;
    String refresh_token;

    public String getClient_id() { return client_id; }

    public void setClient_id(String client_id) { this.client_id = client_id; }

    public String getAccess_token() { return access_token; }

    public void setAccess_token(String access_token) { this.access_token = access_token; }

    public String getRefresh_token() { return refresh_token; }

    public void setRefresh_token(String refresh_token) { this.refresh_token = refresh_token; }
}
