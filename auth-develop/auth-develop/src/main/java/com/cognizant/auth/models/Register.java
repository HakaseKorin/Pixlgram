package com.cognizant.auth.models;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Register {
    String username;
    boolean enabled = true;
    List<Credential> credentials = new LinkedList<>();

    class Credential {
        String type = "password";
        String value;
        boolean temporary = false;

        public Credential() {
        }

        public Credential(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isTemporary() {
            return temporary;
        }

        public void setTemporary(boolean temporary) {
            this.temporary = temporary;
        }
    }

    public Register() {
    }

    public Register(String username, List<Credential> credentials) {
        this.username = username;
        this.credentials = credentials;
    }

    public void setValue(String value){
        credentials.add( new Credential(value));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Credential> getCredentials() {
        return credentials;
    }

    public void setCredentials(List<Credential> credentials) {
        this.credentials = credentials;
    }
}
