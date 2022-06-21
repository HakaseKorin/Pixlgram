package com.cognizant.crustaceansfems.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Imgur{

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("data")
    private Data data;

    @JsonProperty("id")
    private String id;


    public static class Data{

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("link")
        private String link;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Data(String link) {
            this.link = link;
        }

        public Data() {
        }

        @Override
        public String toString() {
            return "Data{" +
                    "link='" + link + '\'' +
                    '}';
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Imgur{" +
                "data=" + data +
                ", id='" + id + '\'' +
                '}';
    }
}
